/**
 * Copyright (c) 2023 Mindstix Software Labs
 * All rights reserved.
 */

package com.mindstix.onboarding.view

import android.app.ActivityManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.mindstix.core.logger.Logger
import com.mindstix.floatingview.service.FloatingBubbleServiceImpl
import com.mindstix.onboarding.intents.LoginIntent
import com.mindstix.onboarding.intents.LoginViewStates

/**
 * Composable function representing the Login Screen.
 *
 * @param state The current state of the Login Screen loaded with data.
 * @param keyboardController The software keyboard controller.
 * @param userIntent A function to handle user intents related to the Login Screen.
 *
 * @author Abhijeet Kokane, Asim Shah
 */

data class Question(
    val questionText: String,
    val questionType: QuestionType,
    val options: List<String> = emptyList(),
    val isMultiSelect: Boolean = false, // For checkboxes
)

enum class QuestionType {
    TEXT,
    RADIO,
    CHECKBOX,
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LoginScreenApp(
    state: LoginViewStates.LoadedData,
    keyboardController: SoftwareKeyboardController?,
    userIntent: (LoginIntent) -> Unit,
    questions: List<Question>,
) {
    Logger.d { "hasOverlayPermission" }
    val answers = remember { mutableStateMapOf<String, Any>() }

    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
    ) {
        Text("Welcome to Fashion Suggester!", style = MaterialTheme.typography.bodyMedium)

        questions.forEach { question ->
            Spacer(modifier = Modifier.height(16.dp))
            Text(question.questionText, style = MaterialTheme.typography.bodyMedium)

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
                    )
                }

                QuestionType.RADIO -> {
                    var selectedOption by remember { mutableStateOf("") }
                    question.options.forEach { option ->
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            RadioButton(
                                selected = selectedOption == option,
                                onClick = {
                                    selectedOption = option
                                    answers[question.questionText] = option
                                },
                            )
                            Text(option)
                        }
                    }
                }

                QuestionType.CHECKBOX -> {
                    val selectedOptions = remember { mutableStateListOf<String>() }
                    question.options.forEach { option ->
                        var isChecked by remember { mutableStateOf(false) }
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Checkbox(
                                checked = isChecked,
                                onCheckedChange = {
                                    isChecked = it
                                    if (it) {
                                        selectedOptions.add(option)
                                    } else {
                                        selectedOptions.remove(option)
                                    }
                                    answers[question.questionText] = selectedOptions.toList()
                                },
                            )
                            Text(option)
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
//            userIntent.invoke(LoginIntent.NavigateToHomeScreen(answers))
        }) {
            Text("Submit")
        }

        val context = LocalContext.current
        val isServiceRunning = remember { mutableStateOf(isServiceRunning(context, FloatingBubbleServiceImpl::class.java)) }
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

                isServiceRunning.value = isServiceRunning(context, FloatingBubbleServiceImpl::class.java)
            },
        ) {
            if (isServiceRunning.value) {
                Text("Stop Service")
            } else {
                Text("Start Service")
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
        val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:${context.packageName}"))
        context.startActivity(intent)
    }
}
