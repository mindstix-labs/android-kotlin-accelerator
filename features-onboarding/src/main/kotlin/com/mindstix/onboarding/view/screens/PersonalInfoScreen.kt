package com.mindstix.onboarding.view.screens

import RainbowColorSlider
import SkinToneSlider
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mindstix.onboarding.intents.LoginIntent
import com.mindstix.onboarding.intents.LoginViewStates

@Composable
fun PersonalInfoScreen(
    questions: List<Question>,
    answers: SnapshotStateMap<String, Any>,
    setIsFirstScreen: (Int) -> Unit
) {
    BackHandler {
        setIsFirstScreen(0)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            "Enter Personal Details",
            style = MaterialTheme.typography.headlineLarge,
            lineHeight = 22.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color.Black,
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(10.dp))
        Divider(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .width(300.dp)
                .height(2.dp)
        )
        Image(
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)
                .size(400.dp)
                .padding(horizontal = 4.dp),
            painter = painterResource(id = com.mindstix.capabilities.R.drawable.imgpi),
            contentDescription = "Google Login",
        )
        Spacer(modifier = Modifier.height(10.dp))
        questions.take(3).forEachIndexed { index, question ->

            if (index != 0)
                Spacer(modifier = Modifier.height(24.dp))
            Text(
                question.questionText,
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontSize = 20.sp
                ),
                fontWeight = FontWeight.Thin,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            when (question.questionType) {
                QuestionType.TEXT -> {
                    var textAnswer by remember { mutableStateOf("") }
                    OutlinedTextField(
                        value = textAnswer,
                        maxLines = 1,
                        textStyle = MaterialTheme.typography.headlineSmall.copy(fontSize = 20.sp),
                        onValueChange = {
                            textAnswer = it
                            answers[question.questionText] = it
                        },
                        label = {
                            Text(
                                "Enter your answer",
                                style = MaterialTheme.typography.headlineSmall.copy(fontSize = 20.sp),
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
                            columns = GridCells.Adaptive(180.dp),
                            modifier = Modifier.height(
                                height = (((question.options.size + 1) / 2) * 60).dp
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
                                                fontSize = 20.sp
                                            )
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
                                                fontSize = 20.sp
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
                setIsFirstScreen(2)
            },
            modifier = Modifier
                .padding(top = 30.dp)
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
        ) {
            Text(
                "Next",
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
