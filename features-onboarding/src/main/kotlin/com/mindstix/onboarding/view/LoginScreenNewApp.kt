/** Copyright (c) 2023 Mindstix Software Labs All rights reserved. */

/** Copyright (c) 2023 Mindstix Software Labs All rights reserved. */

package com.mindstix.onboarding.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import ColorSlider
import SkinToneSlider
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.staggeredgrid.LazyHorizontalStaggeredGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.mindstix.core.logger.Logger
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
            modifier = Modifier.padding(bottom = 24.dp)
        )
        Divider(modifier = Modifier.padding(20.dp))
        questions.forEach { question ->
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                question.questionText,
                style = MaterialTheme.typography.headlineSmall,
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
                        modifier = Modifier
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp) // Adjust the corner radius as needed
                    )

                }

                QuestionType.RADIO -> {
                    var selectedOption by remember { mutableStateOf("") }
                    Column {
                        LazyVerticalGrid(
                            state = rememberLazyGridState(),
                            columns = GridCells.Adaptive(160.dp),
                            modifier = Modifier.height(
                                height = (((question.options.size + 1) / 2) * 50).dp
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
                                            style = MaterialTheme.typography.bodyLarge,
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
                                height = (((question.options.size + 1) / 2) * 50).dp
                            )
                        ) {
                            question.options.forEach { option ->
                                item {
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
                                                answers[question.questionText] =
                                                    selectedOptions.toList()
                                            }
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
                                            }
                                        )
                                        Text(
                                            text = option,
                                            style = MaterialTheme.typography.bodyLarge,
                                        )
                                    }
                                }
                            }
                        }

                    }
                }

                QuestionType.COLOR_PICKER -> {
                    ColorSlider(
                    ) { selectedColorName ->
                        answers[question.questionText] = selectedColorName
                    }
                }

                QuestionType.SKIN_TONE_PICKER -> {
                    SkinToneSlider(
                    ) { selectedColorName ->
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
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
        ) {
            Text("Submit")
        }
        Spacer(modifier = Modifier.height(24.dp))
    }
}
