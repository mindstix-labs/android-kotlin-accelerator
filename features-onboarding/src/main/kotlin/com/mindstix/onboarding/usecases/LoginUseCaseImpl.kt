/**
 * Copyright (c) 2023 Mindstix Software Labs
 * All rights reserved.
 */

package com.mindstix.onboarding.usecases

import com.mindstix.core.utils.DataStatus
import com.mindstix.onboarding.models.LoginScreenDataModel
import javax.inject.Inject

/**
 * Implementation of [LoginUseCase] responsible for providing data for the Login Screen.
 * @author Abhijeet Kokane
 */
class LoginUseCaseImpl
@Inject
constructor(
    // private val downloadRequestDao: DownloadRequestDao,
) : LoginUseCase {
    /**
     * Retrieves the content for the Login Screen.
     *
     * @return An instance of [LoginScreenDataModel] containing data for rendering the Login Screen.
     */
    override fun getLoginScreenContent(): LoginScreenDataModel {
        return LoginScreenDataModel(
            status = DataStatus.Success,
            screenTitle = "Login",
            screenDescription = "This is the login screen",
            backgroundImageUrl = "",
            emailPlaceHolder = "Enter phone or email",
            passwordPlaceHolder = "Enter password",
            emailValue = "abc@xyz.com",
            passwordValue = "1234567",
            loginCtaLabel = "Sign In",
        )
    }
}
