package com.mindstix.floatingview.service

import android.app.Activity
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.PixelFormat
import android.hardware.display.DisplayManager
import android.hardware.display.VirtualDisplay
import android.media.ImageReader
import android.media.projection.MediaProjection
import android.media.projection.MediaProjectionManager
import android.os.IBinder
import android.util.DisplayMetrics
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import androidx.core.app.NotificationCompat

/**
 * @author Abhijeet Kokane
 */
class ScreenCaptureService : Service() {

    private var mediaProjection: MediaProjection? = null
    private var virtualDisplay: VirtualDisplay? = null
    private lateinit var imageReader: ImageReader

    companion object {
        const val CHANNEL_ID = "ScreenCaptureServiceChannel"
        const val SCREEN_CAPTURE_RESULT_CODE = "resultCode"
        const val SCREEN_CAPTURE_INTENT_DATA = "data"
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        val resultCode = intent.getIntExtra(SCREEN_CAPTURE_RESULT_CODE, Activity.RESULT_CANCELED)
        val data: Intent? = intent.getParcelableExtra(SCREEN_CAPTURE_INTENT_DATA)

        if (resultCode == 1001 && data != null) {
            startMediaProjection(resultCode, data)
        } else {
            Toast.makeText(this, "Screen capture permission denied. Service", Toast.LENGTH_SHORT).show()
            stopSelf()
        }

        // Create a foreground notification
        createNotificationChannel()
        val notification = createNotification()
        startForeground(1, notification)

        return START_NOT_STICKY
    }

    private fun startMediaProjection(resultCode: Int, data: Intent) {
        mediaProjection = (getSystemService(Context.MEDIA_PROJECTION_SERVICE) as MediaProjectionManager).getMediaProjection(resultCode, data)

        // Set up the display metrics
        val metrics = DisplayMetrics()
        (getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay.getMetrics(metrics)

        // Create an ImageReader for capturing the screenshot
        imageReader = ImageReader.newInstance(metrics.widthPixels, metrics.heightPixels, PixelFormat.RGBA_8888, 2)

        // Create a virtual display
        virtualDisplay = mediaProjection?.createVirtualDisplay(
            "ScreenCapture", metrics.widthPixels, metrics.heightPixels, metrics.densityDpi,
            DisplayManager.VIRTUAL_DISPLAY_FLAG_AUTO_MIRROR,
            imageReader.surface, null, null,
        )

        // Capture image from ImageReader
        captureImage()
    }

    private fun captureImage() {
        val image = imageReader.acquireLatestImage()
        if (image != null) {
            val planes = image.planes
            val buffer = planes[0].buffer
            val width = image.width
            val height = image.height
            val pixelStride = planes[0].pixelStride
            val rowStride = planes[0].rowStride
            val rowPadding = rowStride - pixelStride * width
            val bitmap = Bitmap.createBitmap(width + rowPadding / pixelStride, height, Bitmap.Config.ARGB_8888)
            bitmap.copyPixelsFromBuffer(buffer)
            image.close()

            // Here you can save or display the bitmap
            Log.d("ScreenCaptureService", "Screenshot captured!")
        }
    }

    private fun createNotification(): Notification {
        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Screen Capture")
            .setContentText("Screen capture in progress")
            .setSmallIcon(android.R.drawable.ic_menu_camera)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .build()
    }

    private fun createNotificationChannel() {
        val serviceChannel = NotificationChannel(
            CHANNEL_ID,
            "Screen Capture Service Channel",
            NotificationManager.IMPORTANCE_DEFAULT,
        )
        val manager = getSystemService(NotificationManager::class.java)
        manager.createNotificationChannel(serviceChannel)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaProjection?.stop()
        virtualDisplay?.release()
    }
}
