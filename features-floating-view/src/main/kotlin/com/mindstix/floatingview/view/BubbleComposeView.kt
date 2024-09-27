package com.mindstix.floatingview.view

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.mindstix.capabilities.presentation.theme.card_background
import com.mindstix.core.logger.Logger
import com.mindstix.features.floatingview.R
import kotlinx.coroutines.runBlocking

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun BubbleComposeView(
    step: MutableState<Int>,
    good: MutableState<Boolean>,
    expand: () -> Unit = {},
    text: MutableState<String>,
    popBack: () -> Unit = {},
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    Column(modifier = Modifier.wrapContentSize(), horizontalAlignment = Alignment.Start) {
        GlideImage(
            model = when (step.value) {
                0 -> {
                    isDone.value = false
                    R.drawable.boy_think
                }

                1 -> {
                    R.drawable.loading_circle
                }

                2 -> {
                    if (good.value) {
                        R.drawable.yes
                    } else {
                        R.drawable.no_response
                    }
                }

                else -> {
                    R.drawable.boy_think
                }
            },
            contentDescription = "",
            modifier =
            Modifier
                .size(
                    100.dp,
                )
                .clickable {
                    when (step.value) {
                        0 -> {
                            val intent = Intent(context, ScreenshotActivity::class.java).apply {
                                flags = Intent.FLAG_ACTIVITY_NEW_TASK
                            }
                            context.startActivity(intent)
                            runBlocking {
                                expand()
                            }
                            isDone
//                                popBack.invoke()
//                                coroutineScope.launch {
//                                    delay(750)
// //                                    expand.invoke()
//                                }
                            Logger.d { "at 0" }
//                                runBlocking {

//                                    bitmapState.value.let {
//                                        Logger.d { "at 1 $it" }
//                                        it?.let { it1 ->
//                                            Logger.d { "at 1.1" }
//                                            expand()
//                                        }
//                                    }
                        }

                        2 -> {
                            popBack()
                        }

                        else -> {}
                    }
                },
        )
        if (step.value == 2) {
            Card(text)
        }
    }

//    if (step.value == 0) {
//        GlideImage(
//            model = R.drawable.boy_think,
//            contentDescription = "",
//            modifier =
//            Modifier
//                .size(
//                    120.dp,
//                )
//                .clickable {
//                    runBlocking {
//                        expand()
//                    }
//                },
//        )
//    } else {
//        if (text.value == "LOADING") {
//            Column(
//                modifier =
//                Modifier
//                    .alpha(0.5f),
//                verticalArrangement = Arrangement.Center,
//                horizontalAlignment = Alignment.CenterHorizontally,
//            ) {
//                GlideImage(
//                    model = R.drawable.loading_circle,
//                    contentDescription = "",
//                    modifier =
//                    Modifier.size(
//                        80.dp,
//                    ),
//                )
//
//            }
//        } else {
//            Column(modifier = Modifier) {
//                Column(
//                    modifier =
//                    Modifier
//                        .padding(10.dp)
//                        .size(200.dp)
//                        .background(Color.LightGray),
//                ) {
//                    IconButton(modifier = Modifier.align(Alignment.End), onClick = {
//                        popBack()
//                    }) {
//                        Icon(
//                            imageVector = Icons.Default.Close,
//                            contentDescription = "play arrow",
//                            tint = Color.Black.copy(0.8f),
//                        )
//                    }
//                    IconButton(modifier = Modifier.align(Alignment.CenterHorizontally), onClick = {
//                        popBack()
//                    }) {
//                        Icon(
//                            modifier =
//                            Modifier
//                                .size(100.dp)
//                                .let {
//                                    if (text.value.contains(
//                                            "TRY_NO",
//                                            ignoreCase = true,
//                                        )
//                                    ) {
//                                        it.rotate(180f)
//                                    } else {
//                                        it
//                                    }
//                                },
//                            imageVector = Icons.Default.ThumbUp,
//                            contentDescription = "play arrow",
//                            tint =
//                            if (text.value.contains(
//                                    "TRY_NO",
//                                    ignoreCase = true,
//                                )
//                            ) {
//                                md_theme_dark_errorContainer
//                            } else {
//                                green
//                            },
//                        )
//                    }
//                    Text(
//                        text = text.value,
//                        modifier = Modifier.padding(10.dp),
//                    )
//                }
//            }
//        }
}

@Composable
fun Card(text: MutableState<String>) {
    Icon(
        modifier = Modifier
            .wrapContentSize()
            .padding(horizontal = 40.dp)
            .rotate(180F),
        painter = painterResource(id = R.drawable.card_arrow),
        contentDescription = "",
        tint = card_background,
    )
    Box(
        modifier = Modifier
            .padding(horizontal = 12.dp)
            .background(card_background, RoundedCornerShape(8.dp)),
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = text.value,
                modifier = Modifier.padding(8.dp),
            )
        }
    }
}
