/** Copyright (c) 2023 Mindstix Software Labs All rights reserved. */

/** Copyright (c) 2023 Mindstix Software Labs All rights reserved. */

package com.mindstix.onboarding.view

import RainbowColorSlider
import SkinToneSlider
import android.provider.Settings
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.mindstix.features.floatingview.R
import com.mindstix.floatingview.view.isDone
import com.mindstix.onboarding.intents.LoginIntent
import com.mindstix.onboarding.intents.LoginViewStates

data class Question(
    val order: Int,
    val questionText: String,
    val questionType: QuestionType,
    val options: List<String> = emptyList(),
    val isMultiSelect: Boolean = false,
)

enum class QuestionType {
    TEXT,
    RADIO,
    CHECKBOX,
    COLOR_PICKER,
    SKIN_TONE_PICKER,
}

@OptIn(ExperimentalComposeUiApi::class, ExperimentalGlideComposeApi::class)
@Composable
fun LoginScreenApp(
    state: LoginViewStates.LoadedData,
    keyboardController: SoftwareKeyboardController?,
    userIntent: (LoginIntent) -> Unit,
    questions: List<Question>,
) {
    val context = LocalContext.current
    val hasOverlayPermission =
        remember { mutableStateOf(Settings.canDrawOverlays(context)) }
    val lifecycleOwner = LocalLifecycleOwner.current
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
    val answers = remember { mutableStateMapOf<String, Any>() }
    val isFirstScreen = remember { mutableStateOf(true) }
    if (isFirstScreen.value) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.error,
                            MaterialTheme.colorScheme.primary,
                            MaterialTheme.colorScheme.primary,
                            MaterialTheme.colorScheme.secondary,
                        )
                    )
                )
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                modifier = Modifier
                    .size(120.dp)
                    .padding(horizontal = 4.dp),
                painter = painterResource(id = com.mindstix.capabilities.R.drawable.a_logo),
                contentDescription = "Google Login",
            )
            Spacer(Modifier.height(18.dp))
            Text(
                "Meet ALVIN !",
                style = MaterialTheme.typography.headlineLarge.copy(fontSize = 32.sp),
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimary,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                "The future of shopping, your personalized assistant..",
                style = MaterialTheme.typography.headlineMedium.copy(fontSize = 16.sp),
                fontWeight = FontWeight.Thin,
                color = MaterialTheme.colorScheme.onSecondary,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                lineHeight = 20.sp
            )
            Spacer(Modifier.height(42.dp))
            Button(
                onClick = {
                    isFirstScreen.value = false
                },
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.CenterHorizontally),
                border = BorderStroke(1.dp, Color.White)
            ) {
                Text(
                    "Let's Start",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Thin,
                        letterSpacing = 1.sp
                    ),
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                MaterialTheme.colorScheme.primary,
                                MaterialTheme.colorScheme.primary,
                                MaterialTheme.colorScheme.error,
                            )
                        )
                    )
                    .padding(14.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
//                Image(
//                    modifier = Modifier
//                        .size(68.dp)
//                        .padding(horizontal = 4.dp),
//                    painter = painterResource(id = com.mindstix.capabilities.R.drawable.a_logo),
//                    contentDescription = "Google Login",
//                )

                GlideImage(
                    model = R.drawable.alvin_ai_1,
                    contentDescription = "",
                    modifier =
                    Modifier
                        .size(60.dp)
                        .clip(RoundedCornerShape(50.dp))
                )
                Text(
                    "Let Elvin know more about yourself !",
                    style = MaterialTheme.typography.headlineMedium.copy(fontSize = 16.sp),
                    lineHeight = 22.sp,
                    fontWeight = FontWeight.Thin,
                    color = MaterialTheme.colorScheme.onPrimary,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.height(10.dp))
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                questions.forEachIndexed { index, question ->

                    if (index != 0)
                        Spacer(modifier = Modifier.height(24.dp))
                    Text(
                        question.questionText,
                        style = MaterialTheme.typography.headlineSmall.copy(fontSize = 14.sp),
                        fontWeight = FontWeight.Thin,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    when (question.questionType) {
                        QuestionType.TEXT -> {
                            var textAnswer by remember { mutableStateOf("") }
                            OutlinedTextField(
                                value = textAnswer,
                                textStyle = MaterialTheme.typography.headlineSmall.copy(fontSize = 14.sp),
                                onValueChange = {
                                    textAnswer = it
                                    answers[question.questionText] = it
                                },
                                label = {
                                    Text(
                                        "Enter your answer",
                                        style = MaterialTheme.typography.headlineSmall.copy(fontSize = 12.sp),
                                    )
                                },
                                modifier = Modifier.fillMaxWidth(),
                            )
                        }

                        QuestionType.RADIO -> {
                            var selectedOption by remember { mutableStateOf("") }
                            Column {
                                LazyVerticalGrid(
                                    state = rememberLazyGridState(),
                                    columns = GridCells.Adaptive(120.dp),
                                    modifier = Modifier.height(
                                        height = (((question.options.size + 1) / 2) * 45).dp
                                    )
                                ) {
                                    question.options.forEach { option ->
                                        item {
                                            Row(
                                                verticalAlignment = Alignment.CenterVertically,
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .clickable {
                                                        selectedOption = option
                                                        answers[question.questionText] = option
                                                    }
                                                    .padding(vertical = 2.dp),
                                            ) {
                                                RadioButton(
                                                    selected = selectedOption == option,
                                                    onClick = {
                                                        selectedOption = option
                                                        answers[question.questionText] = option
                                                    },
                                                )
                                                Text(
                                                    text = option,
                                                    style = MaterialTheme.typography.headlineSmall.copy(
                                                        fontSize = 14.sp
                                                    ),
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }

                        QuestionType.CHECKBOX -> {
                            val selectedOptions = remember { mutableStateListOf<String>() }
                            Column {
                                LazyVerticalGrid(
                                    state = rememberLazyGridState(),
                                    columns = GridCells.Adaptive(160.dp),
                                    modifier = Modifier.height(
                                        height = (((question.options.size + 1) / 2) * 55).dp
                                    )
                                ) {
                                    question.options.forEach { option ->
                                        item {
                                            Row(
                                                verticalAlignment = Alignment.CenterVertically,
                                                modifier =
                                                Modifier
                                                    .fillMaxWidth()
                                                    .clickable {
                                                        val isChecked =
                                                            !selectedOptions.contains(option)
                                                        if (isChecked) {
                                                            selectedOptions.add(option)
                                                        } else {
                                                            selectedOptions.remove(option)
                                                        }
                                                        answers[question.questionText] =
                                                            selectedOptions.toList()
                                                    }
                                                    .padding(vertical = 2.dp),
                                            ) {
                                                Checkbox(
                                                    checked = selectedOptions.contains(option),
                                                    onCheckedChange = {
                                                        val isChecked = it
                                                        if (isChecked) {
                                                            selectedOptions.add(option)
                                                        } else {
                                                            selectedOptions.remove(option)
                                                        }
                                                        answers[question.questionText] =
                                                            selectedOptions.toList()
                                                    },
                                                )
                                                Text(
                                                    text = option,
                                                    style = MaterialTheme.typography.headlineSmall.copy(
                                                        fontSize = 14.sp
                                                    ),
                                                )
                                            }
                                        }
                                    }
                                }

                            }
                        }

                        QuestionType.COLOR_PICKER -> {
                            RainbowColorSlider { selectedColorName ->
                                answers[question.questionText] = selectedColorName
                            }
                        }

                        QuestionType.SKIN_TONE_PICKER -> {
                            SkinToneSlider { selectedColorName ->
                                answers[question.questionText] = selectedColorName
                            }
                        }
                    }
                }


                Spacer(modifier = Modifier.height(1.dp))

                Button(
                    onClick = {
                        userIntent.invoke(LoginIntent.NavigateToHomeScreen(answers))
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                ) {
                    Text(
                        "Submit",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Thin,
                            letterSpacing = 1.sp
                        ),
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}
