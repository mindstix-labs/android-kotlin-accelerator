import android.app.Activity
import android.app.ActivityManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.mindstix.capabilities.presentation.theme.complementary
import com.mindstix.capabilities.presentation.theme.secondary_main
import com.mindstix.capabilities.presentation.theme.tertiary_2
import com.mindstix.features.login.R
import com.mindstix.floatingview.service.FloatingBubbleServiceImpl
import com.mindstix.floatingview.view.Product
import com.mindstix.floatingview.view.getProductsList

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun WelcomeScreen(
    userAnswers: Map<String, Any>,
    onDeleteClick: () -> Unit,
) {
    val context = LocalContext.current
    BackHandler {
        (context as? Activity)?.finish()
    }

    Column(
        modifier = Modifier
            .background(tertiary_2)
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp, top = 16.dp, start = 16.dp, end = 16.dp)
                .verticalScroll(rememberScrollState()),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = Modifier
                    .size(68.dp)
                    .padding(horizontal = 4.dp),
                painter = painterResource(id = com.mindstix.capabilities.R.drawable.a_logo),
                contentDescription = "Google Login",
            )
            Spacer(Modifier.width(12.dp))
            Column {
                val name =
                    if (userAnswers["What is your name?"] != null) userAnswers["What is your name?"] else ""
                FormElements(
                    text = "Welcome $name !",
                    fontSize = 24.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.ExtraBold,
                )
                if (userAnswers.isNotEmpty()) {
                    FormElements(
                        text = "Here’s your profile summary",
                        fontSize = 14.sp,
                        color = Color.Black,
                        modifier = Modifier.padding(bottom = 4.dp),
                    )
                }
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Image(
                painterResource(R.drawable.screen2),
                contentDescription = null,
                modifier = Modifier.size(300.dp),
            )
            Spacer(Modifier.height(12.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()) // Enable horizontal scrolling
            ) {
                val product: List<Product> = getProductsList()
                product.forEach { productItem ->
                    Column(
                        modifier = Modifier
                            .size(150.dp)
                            .padding(8.dp)
                            .background(Color.LightGray),
                    ) {
                        GlideImage(
                            model = productItem.productImageUrl,
                            contentDescription = productItem.name,
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color.LightGray),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(
                            text = productItem.name,
                            style = MaterialTheme.typography.headlineMedium,
                            modifier = Modifier.padding(16.dp),
                            color = Color.Black
                        )
                    }
                }
            }
            Spacer(Modifier.height(12.dp))
            FormElements(
                text = "Add Profile",
                fontSize = 20.sp,
                color = Color.Black,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier.padding(bottom = 4.dp),
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .height(150.dp)
                    .background(
                        color = Color.LightGray,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .clickable {
                        // Handle click
                    },
                verticalArrangement = Arrangement.Center
            ) {
                Button(
                    contentPadding = PaddingValues(0.dp),
                    modifier = Modifier
                        .size(50.dp)
                        .align(Alignment.CenterHorizontally),
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.DarkGray.copy(0.3F)
                    ),
                    onClick = {
                        // Handle click
                    },
                ) {
                    Icon(
                        Icons.Filled.Add,
                        modifier = Modifier.size(40.dp),
                        tint = Color.Black,
                        contentDescription = null,
                    )
                }
            }


            /*userAnswers.forEach { (question, answer) ->
                if (question != "What do people call you?" && question != "What's your Gender?") {
                    AnswerRow(question, answer.toString())
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }

            Spacer(modifier = Modifier.height(30.dp))*/
        }

        Spacer(modifier = Modifier.height(8.dp))

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .height(0.5.dp),
            color = MaterialTheme.colorScheme.primary,
        )
        StartService(onDeleteClick)


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
            modifier = Modifier.padding(vertical = 2.dp),
        ) {
            Icon(
                imageVector = Icons.Filled.CheckCircle,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
            )
            Spacer(modifier = Modifier.width(8.dp))
            FormElements(
                text = question, fontSize = 14.sp, fontWeight = FontWeight.ExtraBold
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 2.dp),
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowForward,
                contentDescription = null,
                tint = Color.Transparent,
                modifier = Modifier.align(Alignment.Top)
            )
            Spacer(modifier = Modifier.width(4.dp))
            FormElements(
                text = "  $answer",
                fontSize = 16.sp,
            )
        }
    }
}

@Composable
fun FormElements(
    text: String,
    fontSize: TextUnit,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.onPrimary,
    fontWeight: FontWeight = FontWeight.Bold
) {
    Box(modifier = modifier) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            // Future enhancement: draw a glow effect for neon text
        }
        Text(
            text = text,
            color = color,
            fontSize = fontSize,
            fontWeight = fontWeight,
            textAlign = TextAlign.Start,
            modifier = modifier,
        )
    }
}

@Composable
fun StartService(
    onDeleteClick: () -> Unit,
) {
    val context = LocalContext.current
    val isServiceRunning = remember {
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
        val observer = LifecycleEventObserver { _, event ->
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

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp),
        horizontalArrangement = Arrangement.End
    ) {
        Button(modifier = Modifier
            .wrapContentHeight(),
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
            onClick = {
                onDeleteClick.invoke()
            }) {
            Icon(
                imageVector = Icons.Filled.Delete,
                contentDescription = null,
                tint = Color.LightGray,
                modifier = Modifier.align(Alignment.Top)
            )
        }
        Spacer(modifier = Modifier.width(10.dp))
        Button(
            modifier = Modifier
                .wrapContentHeight()
                .weight(1f)
                .align(Alignment.CenterVertically),
            colors = if (isServiceRunning.value) {
                ButtonDefaults.buttonColors(Color.LightGray)
            } else {
                ButtonDefaults.buttonColors(secondary_main)
            },

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
                Text(
                    "Stop", style = MaterialTheme.typography.titleMedium.copy(
                        fontSize = 14.sp, fontWeight = FontWeight.ExtraBold,
                    ), color = MaterialTheme.colorScheme.primary
                )
            } else {
                Text(
                    "Hey Alvin..", style = MaterialTheme.typography.titleMedium.copy(
                        fontSize = 14.sp, fontWeight = FontWeight.ExtraBold,
                    ), color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }

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
        val intent = Intent(
            Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
            Uri.parse("package:${context.packageName}"),
        )
        context.startActivity(intent)
    }
}
