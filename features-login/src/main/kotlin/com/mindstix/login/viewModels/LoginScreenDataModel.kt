package com.mindstix.login.viewModels

/**
 * This class used to render the data on Login Screen.
 */
data class LoginScreenDataModel(
    var screenTitle: String = "Login",
    var screenDescription: String = "This is login screen",
    var backgroundImageUrl: String = "",
    var emailPlaceHolder: String = "Email",
    var passwordPlaceHolder: String = "Password",
    var emailValue: String = "abc@xyz.com",
    var passwordValue: String = "1234567",
    var loginCtaLabel: String = "Login",
) {
    companion object {
        val defaultValue = LoginScreenDataModel()
    }
}
