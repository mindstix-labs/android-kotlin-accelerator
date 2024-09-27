/** Copyright (c) 2023 Mindstix Software Labs All rights reserved. */

/** Copyright (c) 2023 Mindstix Software Labs All rights reserved. */

package com.mindstix.onboarding.view

import RainbowColorSlider
import SkinToneSlider
import android.provider.Settings
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
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

@OptIn(ExperimentalComposeUiApi::class)
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

    Box(
        modifier =
        Modifier
            .fillMaxSize()
            .background(
                color = Color.Black, // Light gray background color
            ),
    ) {
        Column(
            modifier =
            Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
        ) {
            Text(
                "Welcome to the future of shopping!",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier =
                Modifier
                    .padding(bottom = 24.dp),
            )

            questions.sortedBy { it.order }.forEach { question ->
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    question.questionText,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp),
                )

                when (question.questionType) {
                    QuestionType.TEXT -> {
                        var textAnswer by remember { mutableStateOf("") }
                        OutlinedTextField(
                            value = textAnswer,
                            onValueChange = {
                                textAnswer = it
                                answers[question.questionText] = it
                            },
                            label = { Text("Enter your answer") },
                            modifier = Modifier.fillMaxWidth(),
                        )
                    }

                    QuestionType.RADIO -> {
                        var selectedOption by remember { mutableStateOf("") }
                        Column {
                            question.options.forEach { option ->
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier =
                                    Modifier
                                        .fillMaxWidth()
                                        .clickable {
                                            selectedOption = option
                                            answers[question.questionText] = option
                                        }
                                        .padding(vertical = 8.dp),
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
                                        style = MaterialTheme.typography.headlineSmall,
                                        modifier = Modifier.padding(start = 16.dp),
                                    )
                                }
                            }
                        }
                    }

                    QuestionType.CHECKBOX -> {
                        val selectedOptions = remember { mutableStateListOf<String>() }
                        Column {
                            question.options.forEach { option ->
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier =
                                    Modifier
                                        .fillMaxWidth()
                                        .clickable {
                                            val isChecked = !selectedOptions.contains(option)
                                            if (isChecked) {
                                                selectedOptions.add(option)
                                            } else {
                                                selectedOptions.remove(option)
                                            }
                                            answers[question.questionText] =
                                                selectedOptions.toList()
                                        }
                                        .padding(vertical = 8.dp),
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
                                        style = MaterialTheme.typography.headlineSmall,
                                        modifier = Modifier.padding(start = 16.dp),
                                    )
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

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    userIntent.invoke(LoginIntent.NavigateToHomeScreen(answers))
                },
                modifier =
                Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
            ) {
                Text("Submit")
            }
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}
