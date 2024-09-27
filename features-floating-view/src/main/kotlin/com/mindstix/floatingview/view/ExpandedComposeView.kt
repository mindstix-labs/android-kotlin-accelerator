package com.mindstix.floatingview.view

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * @author Apoorv Gupta
 */

@Composable
fun ExpandedComposeView(
    popBack: () -> Unit = {},
    expand: () -> Unit = {}
) {

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    Column(modifier = Modifier) {
        Column(
            modifier =
            Modifier
                .width(400.dp)
                .height(100.dp)
                .background(Color.LightGray),
        ) {
            Row {
                Button(onClick = { popBack() }) {
                    Text(text = "pop back!")
                }

                Button(
                    onClick = {
                        val intent = Intent(context, ScreenshotActivity::class.java).apply {
                            flags = Intent.FLAG_ACTIVITY_NEW_TASK
                        }
                        context.startActivity(intent)

                        popBack.invoke()
                        coroutineScope.launch {
                            delay(750)
                            expand.invoke()
                        }
                    }
                ) {
                    Text("Start Screen Capture")
                }
            }
        }
    }
}
