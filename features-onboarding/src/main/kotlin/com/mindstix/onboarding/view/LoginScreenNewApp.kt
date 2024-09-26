/**
 * Copyright (c) 2023 Mindstix Software Labs
 * All rights reserved.
 */

package com.mindstix.onboarding.view

import RainbowColorSlider
import SkinToneSlider
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.mindstix.onboarding.intents.LoginIntent
import com.mindstix.onboarding.intents.LoginViewStates

data class Question(
    val questionText: String,
    val questionType: QuestionType,
    val options: List<String> = emptyList(),
    val isMultiSelect: Boolean = false
)

enum class QuestionType {
    TEXT, RADIO, CHECKBOX, COLOR_PICKER, SKIN_TONE_PICKER
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LoginScreenApp(
    state: LoginViewStates.LoadedData,
    keyboardController: SoftwareKeyboardController?,
    userIntent: (LoginIntent) -> Unit,
    questions: List<Question>,
) {
    val answers = remember { mutableStateMapOf<String, Any>() }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = Color.Black // Light gray background color
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                "Welcome to the future of shopping!",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(bottom = 24.dp)
            )

            questions.forEach { question ->
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    question.questionText,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
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
                            modifier = Modifier.fillMaxWidth()
                        )
                    }

                    QuestionType.RADIO -> {
                        var selectedOption by remember { mutableStateOf("") }
                        Column {
                            question.options.forEach { option ->
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable {
                                            selectedOption = option
                                            answers[question.questionText] = option
                                        }
                                        .padding(vertical = 8.dp)
                                ) {
                                    RadioButton(
                                        selected = selectedOption == option,
                                        onClick = {
                                            selectedOption = option
                                            answers[question.questionText] = option
                                        }
                                    )
                                    Text(
                                        text = option,
                                        style = MaterialTheme.typography.bodyMedium,
                                        modifier = Modifier.padding(start = 16.dp)
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
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable {
                                            val isChecked = !selectedOptions.contains(option)
                                            if (isChecked) {
                                                selectedOptions.add(option)
                                            } else {
                                                selectedOptions.remove(option)
                                            }
                                            answers[question.questionText] = selectedOptions.toList()
                                        }
                                        .padding(vertical = 8.dp)
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
                                            answers[question.questionText] = selectedOptions.toList()
                                        }
                                    )
                                    Text(
                                        text = option,
                                        style = MaterialTheme.typography.bodyMedium,
                                        modifier = Modifier.padding(start = 16.dp)
                                    )
                                }
                            }
                        }
                    }

                    QuestionType.COLOR_PICKER -> {
                        RainbowColorSlider(
                        ) { selectedColor ->
                            answers[question.questionText] = selectedColor
                        }
                    }

                    QuestionType.SKIN_TONE_PICKER -> {
                        SkinToneSlider(
                        ) { selectedColor ->
                            answers[question.questionText] = selectedColor
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    // Perform submit action here
                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            ) {
                Text("Submit")
            }
        }
    }
}
