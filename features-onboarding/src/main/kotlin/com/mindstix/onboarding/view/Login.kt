/**
 * Copyright (c) 2023 Mindstix Software Labs
 * All rights reserved.
 */
package com.mindstix.onboarding.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import com.mindstix.capabilities.presentation.theme.buttonTextBold
import com.mindstix.capabilities.presentation.theme.login_in_button_bg_color
import com.mindstix.capabilities.presentation.theme.textFieldLabel
import com.mindstix.capabilities.presentation.theme.textStyle1
import com.mindstix.capabilities.presentation.theme.text_field_bg_color
import com.mindstix.capabilities.presentation.theme.text_field_label_color
import com.mindstix.features.login.R
import com.mindstix.onboarding.intents.LoginIntent
import com.mindstix.onboarding.intents.LoginViewStates

/**
 * @author Asim Shah
 */

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Login(
    state: LoginViewStates.LoadedData,
    keyboardController: SoftwareKeyboardController?,
    userIntent: (LoginIntent) -> Unit,
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        // Email TextField
        TextField(
            value = email,
            onValueChange = { email = it },
            colors =
            TextFieldDefaults.colors(
                focusedLabelColor = Color.Gray,
                unfocusedLabelColor = text_field_label_color,
                focusedTextColor = text_field_label_color,
                focusedContainerColor = text_field_bg_color,
                unfocusedContainerColor = text_field_bg_color,
                disabledContainerColor = text_field_bg_color,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
            ),
            shape = RoundedCornerShape(4.dp),
            singleLine = true,
            modifier =
            Modifier
                .fillMaxWidth(),
            label = {
                Text(
                    text = state.data.emailPlaceHolder,
                    style = textFieldLabel,
                )
            },
            keyboardOptions =
            KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next,
            ),
            keyboardActions =
            KeyboardActions(
                onNext = { /* Handle next action */ },
            ),
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Password TextField
        TextField(
            value = password,
            onValueChange = { password = it },
            colors =
            TextFieldDefaults.colors(
                focusedLabelColor = Color.Gray,
                unfocusedLabelColor = text_field_label_color,
                focusedTextColor = text_field_label_color,
                focusedContainerColor = text_field_bg_color,
                unfocusedContainerColor = text_field_bg_color,
                disabledContainerColor = text_field_bg_color,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
            ),
            shape = RoundedCornerShape(4.dp),
            singleLine = true,
            modifier =
            Modifier
                .fillMaxWidth(),
            label = {
                Text(
                    text = state.data.passwordPlaceHolder,
                    style = textFieldLabel,
                )
            },
            trailingIcon = {
                // Password visibility toggle
                IconButton(
                    onClick = { isPasswordVisible = !isPasswordVisible },
                ) {
                    Image(
                        painter =
                        if (isPasswordVisible) {
                            painterResource(id = R.drawable.visibility_off)
                        } else {
                            painterResource(
                                id = R.drawable.visibility_on,
                            )
                        },
                        contentDescription = if (isPasswordVisible) "Hide Password" else "Show Password",
                    )
                }
            },
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions =
            KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done,
            ),
            keyboardActions =
            KeyboardActions(
                onDone = { /* Handle login action */ },
            ),
        )

        Spacer(modifier = Modifier.height(25.dp))

        // Login Button to navigate to the Home Screen
        Button(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(4.dp),
            colors =
            ButtonDefaults.buttonColors(
                containerColor = text_field_label_color,
                contentColor = text_field_bg_color,
                disabledContainerColor = login_in_button_bg_color,
                disabledContentColor = login_in_button_bg_color,
            ),
            onClick = {
                keyboardController?.hide()
                // Invoke the user intent to navigate to the Home Screen
                userIntent.invoke(LoginIntent.NavigateToHomeScreen)
            },
        ) {
            Text(
                style = buttonTextBold,
                text = state.data.loginCtaLabel.toUpperCase(Locale.current), // S I G N    I N
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            modifier = Modifier.fillMaxWidth(),
            style = textStyle1,
            color = login_in_button_bg_color,
            textAlign = TextAlign.End,
            text = "Forgot Password",
        )
    }
}
