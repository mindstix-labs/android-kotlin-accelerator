/**
 * Copyright (c) 2023 Mindstix Software Labs
 * All rights reserved.
 */

package com.mindstix.login.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.SoftwareKeyboardController
import com.mindstix.login.intents.LoginIntent
import com.mindstix.login.intents.LoginViewStates

/**
 * Composable function representing the Login Screen.
 *
 * @param state The current state of the Login Screen loaded with data.
 * @param keyboardController The software keyboard controller.
 * @param userIntent A function to handle user intents related to the Login Screen.
 *
 * @author Abhijeet Kokane
 */
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LoginScreen(
    state: LoginViewStates.LoadedData,
    keyboardController: SoftwareKeyboardController?,
    userIntent: (LoginIntent) -> Unit,
) {
    Column(
        modifier =
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // Display the screen title
        Text(state.data.screenTitle)

        // Button to navigate to the Home Screen
        Button(onClick = {
            keyboardController?.hide()
            // Invoke the user intent to navigate to the Home Screen
            userIntent.invoke(LoginIntent.NavigateToHomeScreen)
        }) {
            Text(state.data.loginCtaLabel)
        }
    }
}
