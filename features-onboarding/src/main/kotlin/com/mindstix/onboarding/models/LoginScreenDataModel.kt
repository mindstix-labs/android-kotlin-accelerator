/**
 * Copyright (c) 2023 Mindstix Software Labs
 * All rights reserved.
 */

package com.mindstix.onboarding.models

import com.mindstix.core.utils.DataStatus

/**
 * Data class representing the model used to render data on the Login Screen.
 *
 * @param status The data status (e.g., Empty, Offline, Error, Success, Loading).
 * @param screenTitle The title of the login screen.
 * @param screenDescription The description of the login screen.
 * @param backgroundImageUrl The URL of the background image.
 * @param emailPlaceHolder The placeholder for the email input.
 * @param passwordPlaceHolder The placeholder for the password input.
 * @param emailValue The default value for the email input.
 * @param passwordValue The default value for the password input.
 * @param loginCtaLabel The label for the login call-to-action button.
 *
 * @author Abhijeet Kokane
 */
data class LoginScreenDataModel(
    val status: DataStatus = DataStatus.Empty,
    var screenTitle: String = "Login",
    var screenDescription: String = "This is the login screen",
    var backgroundImageUrl: String = "",
    var emailPlaceHolder: String = "Enter phone or email",
    var passwordPlaceHolder: String = "Enter password",
    var emailValue: String = "abc@xyz.com",
    var passwordValue: String = "1234567",
    var loginCtaLabel: String = "Sign In",
) {
    companion object {
        val defaultValue = LoginScreenDataModel()
    }
}
