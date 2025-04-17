/**
 * Copyright (c) 2023 Mindstix Software Labs
 * All rights reserved.
 */

package com.mindstix.onboarding.view


/**
 * Composable function representing the Login Screen.
 *
 * @param state The current state of the Login Screen loaded with data.
 * @param keyboardController The software keyboard controller.
 * @param userIntent A function to handle user intents related to the Login Screen.
 *
 * @author Abhijeet Kokane, Asim Shah
 */

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mindstix.capabilities.presentation.theme.textFieldLabel
import com.mindstix.capabilities.presentation.theme.text_field_bg_color
import com.mindstix.capabilities.presentation.theme.text_field_label_color
import com.mindstix.features.login.R
import com.mindstix.onboarding.intents.LoginIntent
import com.mindstix.onboarding.intents.LoginViewStates

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LoginAccountScreen(
    state: LoginViewStates.LoadedData,
    keyboardController: SoftwareKeyboardController?,
    userIntent: (LoginIntent) -> Unit,
) {
    var selectedTab by remember { mutableStateOf("Phone Number") }
    var phoneNumber by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    var expanded by remember { mutableStateOf(false) }
    val countryCodes = listOf("+91", "+44", "+1")
    var selectedCode by remember { mutableStateOf(countryCodes.first()) }

    val isPhoneValid = when (selectedCode) {
        "+91" -> phoneNumber.length == 10
        "+44" -> phoneNumber.length == 7
        "+1" -> phoneNumber.length == 4
        else -> false
    }

    val isEmailValid = android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()

    val isFormValid =
        (selectedTab == "Phone Number" && isPhoneValid) || (selectedTab == "Email" && isEmailValid)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1C1C1E))
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.height(60.dp))

        Column(
            horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.loginscreen_logo),
                contentDescription = "Login Illustration",
                modifier = Modifier
                    .height(250.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                contentScale = ContentScale.FillWidth
            )

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Login to Your Account",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Tabs
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFF2C2C2E))
                    .padding(4.dp)
            ) {
                TabButton("Email", selectedTab == "Email") { selectedTab = "Email" }
                TabButton("Phone Number", selectedTab == "Phone Number") {
                    selectedTab = "Phone Number"
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            if (selectedTab == "Phone Number") {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color(0xFF2C2C2E))
                        .border(1.dp, Color.Gray, RoundedCornerShape(10.dp))
                        .padding(horizontal = 12.dp), verticalAlignment = Alignment.CenterVertically
                ) {
                    // Dropdown for country code
                    Box(modifier = Modifier
                        .wrapContentWidth()
                        .clickable { expanded = true }
                        .padding(end = 8.dp)) {
                        Text(text = selectedCode, color = Color.White)
                        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                            countryCodes.forEach { code ->
                                DropdownMenuItem(text = { Text(text = code) }, onClick = {
                                    selectedCode = code
                                    expanded = false
                                    phoneNumber = ""
                                })
                            }
                        }

                    }

                    Box(
                        modifier = Modifier
                            .width(1.dp)
                            .height(30.dp)
                            .background(Color.Gray)
                    )
                    Spacer(modifier = Modifier.width(8.dp))

                    TextField(
                        value = phoneNumber,
                        onValueChange = {
                            val maxLength = when (selectedCode) {
                                "+91" -> 10
                                "+44" -> 7
                                "+1" -> 4
                                else -> 10 // default fallback
                            }

                            val digitsOnly = it.filter { char -> char.isDigit() }
                            if (digitsOnly.length <= maxLength) {
                                phoneNumber = digitsOnly
                            }
                        },
                        colors = TextFieldDefaults.colors(
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
                        modifier = Modifier.fillMaxWidth(),
                        label = {
                            Text("Phone Number", color = Color.Gray)
                        },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number, imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
                    )

                }

                Spacer(modifier = Modifier.height(24.dp))
            }

            if (selectedTab == "Email") {
                TextField(
                    value = email,
                    onValueChange = { email = it },
                    placeholder = { Text("Email", color = Color.Gray) },
                    colors = TextFieldDefaults.colors(
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
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Email
                    )
                )

                Spacer(modifier = Modifier.height(24.dp))
            }

            Button(
                onClick = { /* send OTP logic */ },
                enabled = isFormValid,
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isFormValid) Color(0xFFFF8900) else Color(0xFFFFCC80),
                    disabledContainerColor = Color(0xFFFFCC80)
                ),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text(
                    "Send OTP",
                    color = if (isFormValid) Color.White else Color.DarkGray,
                    fontWeight = FontWeight.Bold
                )
            }

        }

        // Bottom Signup Prompt
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text("Don’t have account? ", color = Color.White)
            Text(text = "Create Account",
                color = Color(0xFFFF8900),
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.clickable {
                    // handle create account
                })
        }
    }
}

@Composable
fun RowScope.TabButton(
    label: String, isSelected: Boolean, onClick: () -> Unit
) {
    val background = if (isSelected) Color.White else Color.Transparent
    val contentColor = if (isSelected) Color.Black else Color.White

    Box(
        modifier = Modifier
            .weight(1f)
            .height(48.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(background)
            .clickable { onClick() }, contentAlignment = Alignment.Center
    ) {
        Text(text = label, color = contentColor, fontWeight = FontWeight.SemiBold)
    }
}








