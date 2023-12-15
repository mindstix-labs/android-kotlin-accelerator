/**
 * Copyright (c) 2023 Mindstix Software Labs
 * All rights reserved.
 */

package com.mindstix.onboarding.usecases

import com.mindstix.onboarding.models.LoginScreenDataModel
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Test

/**
 * This unit test exercises the LoginUseCaseImpl class.
 *
 * @author Abhishek Singh
 */
class LoginUseCaseImplTest {
    private lateinit var loginUseCaseImpl: LoginUseCaseImpl

    /**
     * This function executes before every test.
     */
    @Before
    fun setUp() {
        loginUseCaseImpl = LoginUseCaseImpl()
    }

    /**
     * This test ensures that the getLoginScreenContent function of LoginUseCaseImpl
     * helps to retrieve data for the login screen, and when it is called,
     * it should return an instance of LoginScreenDataModel.
     */
    @Test
    fun `given getLoginScreenContent(), when getLoginScreenContent() is called, then it should return LoginScreenDataModel`() {
        // When
        val result = loginUseCaseImpl.getLoginScreenContent()
        // Then
        assertTrue(result is LoginScreenDataModel)
    }
}
