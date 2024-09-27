import android.app.ActivityManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.mindstix.floatingview.service.FloatingBubbleServiceImpl

@Composable
fun WelcomeScreen(
    userAnswers: Map<String, Any>,
    onTermsClicked: () -> Unit,
) {
    Box(
        modifier =
        Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp),
    ) {
        Column(
            modifier =
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            FormElements(
                text = "Welcome!",
                fontSize = 36.sp,
                modifier = Modifier.padding(bottom = 24.dp),
            )
            StartService()

            if (userAnswers.isNotEmpty()) {
                FormElements(
                    text = "Here’s a summary of your input:",
                    fontSize = 20.sp,
                    modifier = Modifier.padding(bottom = 16.dp),
                )
            }

            userAnswers.forEach { (question, answer) ->
                AnswerRow(question, answer.toString())
                Spacer(modifier = Modifier.height(20.dp))
            }

            Spacer(modifier = Modifier.height(24.dp))

            TextButton(
                onClick = onTermsClicked,
                modifier =
                Modifier
                    .padding(top = 16.dp)
                    .shadow(10.dp), // Shadow effect
            ) {
                FormElements(
                    text = "Read Terms and Conditions",
                    fontSize = 18.sp,
                )
            }
        }
    }
}

@Composable
fun AnswerRow(
    question: String,
    answer: String,
) {
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 4.dp),
        ) {
            Icon(
                imageVector = Icons.Filled.List, // Placeholder icon, replace with actual icon id
                contentDescription = null,
                tint = Color.White,
            )
            Spacer(modifier = Modifier.width(8.dp))
            FormElements(
                text = question,
                fontSize = 18.sp,
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 4.dp),
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowForward, // Placeholder icon, replace with actual icon id
                contentDescription = null,
                tint = Color.White,
            )
            Spacer(modifier = Modifier.width(8.dp))
            FormElements(
                text = answer,
                fontSize = 18.sp,
            )
        }
    }
}

@Composable
fun FormElements(
    text: String,
    fontSize: TextUnit,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            // Future enhancement: draw a glow effect for neon text
        }
        Text(
            text = text,
            fontSize = fontSize,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = Color.White,
            modifier = modifier,
        )
    }
}

@Composable
fun StartService() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val context = LocalContext.current
        val isServiceRunning =
            remember {
                mutableStateOf(
                    isServiceRunning(
                        context,
                        FloatingBubbleServiceImpl::class.java,
                    ),
                )
            }
        val hasOverlayPermission = remember { mutableStateOf(Settings.canDrawOverlays(context)) }

        // Get the current lifecycle owner
        val lifecycleOwner = LocalLifecycleOwner.current

        // Update permission state when the lifecycle state changes
        DisposableEffect(lifecycleOwner) {
            val observer =
                LifecycleEventObserver { _, event ->
                    if (event == Lifecycle.Event.ON_RESUME) {
                        // Check permission when the app resumes
                        hasOverlayPermission.value = Settings.canDrawOverlays(context)
                    }
                }
            lifecycleOwner.lifecycle.addObserver(observer)

            // Clean up the observer when this Composable leaves the composition
            onDispose {
                lifecycleOwner.lifecycle.removeObserver(observer)
            }
        }

        Button(
            modifier =
            Modifier
                .height(50.dp)
                .width(150.dp),
            onClick = {
                if (!isServiceRunning.value) {
                    // request display over app permission
                    if (hasOverlayPermission.value) {
                        val intent = Intent(context, FloatingBubbleServiceImpl::class.java)
                        intent.putExtra("size", 60)
                        intent.putExtra("noti_message", "HELLO FROM MAIN ACT")
                        ContextCompat.startForegroundService(context, intent)
                    } else {
                        requestOverlayPermission(context)
                    }
                } else {
                    val intent = Intent(context, FloatingBubbleServiceImpl::class.java)
                    context.stopService(intent)
                }

                isServiceRunning.value =
                    isServiceRunning(context, FloatingBubbleServiceImpl::class.java)
            },
        ) {
            if (isServiceRunning.value) {
                Text("Stop Service")
            } else {
                Text("Start Service")
            }
        }
        Spacer(modifier = Modifier.height(100.dp))
    }
}

fun isServiceRunning(
    context: Context,
    serviceClass: Class<out Service>,
): Boolean {
    val manager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
    for (service in manager.getRunningServices(Int.MAX_VALUE)) {
        if (serviceClass.name == service.service.className) {
            return true
        }
    }
    return false
}

fun requestOverlayPermission(context: Context) {
    if (!Settings.canDrawOverlays(context)) {
        val intent =
            Intent(
                Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                Uri.parse("package:${context.packageName}"),
            )
        context.startActivity(intent)
    }
}
