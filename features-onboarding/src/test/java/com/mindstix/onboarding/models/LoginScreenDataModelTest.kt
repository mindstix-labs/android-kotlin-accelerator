/**
 * Copyright (c) 2023 Mindstix Software Labs
 * All rights reserved.
 */

package com.mindstix.onboarding.models

import com.mindstix.core.utils.DataStatus
import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * This unit test exercises the LoginScreenDataModel class.
 *
 * @author Abhishek Singh
 */
class LoginScreenDataModelTest {
    /**
     * This test verifies that creating a LoginScreenDataModel using the defaultValue companion object
     * results in a model with default values for various properties such as status, screenTitle, screenDescription, etc.
     */
    @Test
    fun `create LoginScreenDataModel with default values`() {
        // When
        val loginScreenDataModel = LoginScreenDataModel.defaultValue

        // Then
        assertEquals(DataStatus.Empty, loginScreenDataModel.status)
        assertEquals("Login", loginScreenDataModel.screenTitle)
        assertEquals("This is the login screen", loginScreenDataModel.screenDescription)
        assertEquals("", loginScreenDataModel.backgroundImageUrl)
        assertEquals("Email", loginScreenDataModel.emailPlaceHolder)
        assertEquals("Password", loginScreenDataModel.passwordPlaceHolder)
        assertEquals("abc@xyz.com", loginScreenDataModel.emailValue)
        assertEquals("1234567", loginScreenDataModel.passwordValue)
        assertEquals("Login", loginScreenDataModel.loginCtaLabel)
    }

    /**
     * This test verifies that creating a LoginScreenDataModel with custom values for properties such as status, screenTitle, screenDescription, etc.
     * results in a model with the specified values.
     */
    @Test
    fun `create LoginScreenDataModel with custom values`() {
        // Given
        val status = DataStatus.Success
        val screenTitle = "Title"
        val screenDescription = "Description"
        val backgroundImageUrl = "custom/image.jpg"
        val emailPlaceHolder = "Email"
        val passwordPlaceHolder = "Password"
        val emailValue = "mock@xyz.com"
        val passwordValue = "custom123"
        val loginCtaLabel = "cta lable"

        // When
        val loginScreenDataModel =
            LoginScreenDataModel(
                status = status,
                screenTitle = screenTitle,
                screenDescription = screenDescription,
                backgroundImageUrl = backgroundImageUrl,
                emailPlaceHolder = emailPlaceHolder,
                passwordPlaceHolder = passwordPlaceHolder,
                emailValue = emailValue,
                passwordValue = passwordValue,
                loginCtaLabel = loginCtaLabel,
            )

        // Then
        assertEquals(status, loginScreenDataModel.status)
        assertEquals(screenTitle, loginScreenDataModel.screenTitle)
        assertEquals(screenDescription, loginScreenDataModel.screenDescription)
        assertEquals(backgroundImageUrl, loginScreenDataModel.backgroundImageUrl)
        assertEquals(emailPlaceHolder, loginScreenDataModel.emailPlaceHolder)
        assertEquals(passwordPlaceHolder, loginScreenDataModel.passwordPlaceHolder)
        assertEquals(emailValue, loginScreenDataModel.emailValue)
        assertEquals(passwordValue, loginScreenDataModel.passwordValue)
        assertEquals(loginCtaLabel, loginScreenDataModel.loginCtaLabel)
    }
}
