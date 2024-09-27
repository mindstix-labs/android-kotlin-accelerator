package com.mindstix.floatingview.view

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.PixelFormat
import android.hardware.display.DisplayManager
import android.hardware.display.VirtualDisplay
import android.media.ImageReader
import android.media.projection.MediaProjection
import android.media.projection.MediaProjectionManager
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.util.DisplayMetrics
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import com.mindstix.core.logger.Logger
import com.mindstix.floatingview.service.FloatingBubbleServiceImpl
import com.mindstix.floatingview.service.ScreenCaptureService
import com.mindstix.floatingview.service.isGoodChoice
import com.mindstix.floatingview.service.response
import com.mindstix.floatingview.service.step
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.DelicateCoroutinesApi
import javax.inject.Inject

/**
 * @author Apoorv Gupta
 */

val bitmapState = mutableStateOf<Bitmap?>(null)
val isDone = mutableStateOf(true)

@AndroidEntryPoint
class ScreenshotActivity : ComponentActivity() {

    private lateinit var mediaProjectionManager: MediaProjectionManager
    private var mediaProjection: MediaProjection? = null
    private var virtualDisplay: VirtualDisplay? = null
    private lateinit var imageReader: ImageReader
    private val screenCaptureCode = 1001

    @Inject
    lateinit var floatingBubbleServiceImpl: FloatingBubbleServiceImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mediaProjectionManager =
            getSystemService(Context.MEDIA_PROJECTION_SERVICE) as MediaProjectionManager
        startScreenCaptureService(screenCaptureCode, Intent())
        startScreenCapture()
    }

    private fun startScreenCapture() {
        val captureIntent = mediaProjectionManager.createScreenCaptureIntent()
        screenCaptureLauncher.launch(captureIntent)
    }

    private fun startScreenCaptureService(resultCode: Int, data: Intent) {
        val serviceIntent = Intent(this, ScreenCaptureService::class.java).apply {
            putExtra(ScreenCaptureService.SCREEN_CAPTURE_RESULT_CODE, resultCode)
            putExtra(ScreenCaptureService.SCREEN_CAPTURE_INTENT_DATA, data)
        }
        startForegroundService(serviceIntent)
    }

    private var screenCaptureLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK && result.data != null) {
                startMediaProjection(result.resultCode, result.data!!)
            } else {
//                Toast.makeText(
//                    this,
//                    "Screen capture permission denied. Activity",
//                    Toast.LENGTH_SHORT
//                ).show()
            }
        }

    private fun startMediaProjection(resultCode: Int, data: Intent) {
        mediaProjection = mediaProjectionManager.getMediaProjection(resultCode, data)

        // Register a callback to handle MediaProjection lifecycle
        mediaProjection?.registerCallback(
            object : MediaProjection.Callback() {
                override fun onStop() {
                    super.onStop()
                    Logger.d { "ScreenCapture MediaProjection stopped" }
                    virtualDisplay?.release()
                    mediaProjection = null
                }
            },
            null,
        ) // Null handler means callback will run on the main thread

        val metrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(metrics)

        imageReader = ImageReader.newInstance(
            metrics.widthPixels,
            metrics.heightPixels,
            PixelFormat.RGBA_8888,
            2,
        )

        virtualDisplay = mediaProjection?.createVirtualDisplay(
            "ScreenCapture",
            metrics.widthPixels,
            metrics.heightPixels,
            metrics.densityDpi,
            DisplayManager.VIRTUAL_DISPLAY_FLAG_AUTO_MIRROR,
            imageReader.surface,
            null,
            null,
        )

        captureImage()
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun captureImage() {
        val handlerThread = HandlerThread("ImageCaptureThread")
        handlerThread.start()
        val handler = Handler(handlerThread.looper)
        imageReader.setOnImageAvailableListener({ reader ->
            val image = reader.acquireNextImage()
            if (image != null) {
                // Process the image
                val planes = image.planes
                val buffer = planes[0].buffer
                val width = image.width
                val height = image.height
                val pixelStride = planes[0].pixelStride
                val rowStride = planes[0].rowStride
                val rowPadding = rowStride - pixelStride * width

                val bitmap = Bitmap.createBitmap(
                    width + rowPadding / pixelStride,
                    height,
                    Bitmap.Config.ARGB_8888,
                )
                bitmap.copyPixelsFromBuffer(buffer)

                // Update UI in Jetpack Compose
                bitmapState.value = bitmap

                // Save the bitmap to a file (optional)
                saveBitmapToFile(bitmap)
                if (!isDone.value) {
                    isDone.value = true
                    floatingBubbleServiceImpl.test(
                        bitmap,
                        applicationContext,
                    ) { isGood, responseData ->
                        Logger.d { "at 8 result ${step.intValue}" }
                        isGoodChoice.value = isGood
                        response.value = responseData
                        step.intValue = 2
                    }
                }

                Logger.d { "ScreenshotActivity Screenshot captured!" }

                image.close() // Don't forget to close the image!

                // close activity after taking screenshot
                finish()
            } else {
                Logger.d { "ScreenshotActivity No image available" }
            }
        }, handler) // Make sure to run this on a handler if needed
    }

    private fun saveBitmapToFile(bitmap: Bitmap) {
        try {
            val fileOutputStream = openFileOutput("screenshot.png", Context.MODE_PRIVATE)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream)
            fileOutputStream.close()
//            Toast.makeText(this, "Screenshot saved successfully", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaProjection?.stop()
        virtualDisplay?.release()
    }

    companion object {
        val bitmapState1 = mutableStateOf<Bitmap?>(null)
    }
}

@Composable
fun ScreenCaptureApp(onCaptureClick: () -> Unit) {
    val bitmap by remember { ScreenshotActivity.bitmapState1 }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(onClick = { onCaptureClick() }) {
            Text("Capture Screenshot")
        }

        Spacer(modifier = Modifier.height(16.dp))

        bitmap?.let {
            Image(
                bitmap = it.asImageBitmap(),
                contentDescription = "Captured Screenshot",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp),
            )
        }
    }
}
