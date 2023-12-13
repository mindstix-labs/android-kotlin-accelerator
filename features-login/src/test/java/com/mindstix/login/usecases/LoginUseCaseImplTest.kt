package com.mindstix.login.usecases

import com.mindstix.login.models.LoginScreenDataModel
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Test

/**
 * This unit test exercises the LoginUseCaseImpl class.
 */
class LoginUseCaseImplTest {

    private lateinit var loginUseCaseImpl: LoginUseCaseImpl

    @Before
    fun setUp() {
        loginUseCaseImpl = LoginUseCaseImpl()
    }

    @Test
    fun `given getLoginScreenContent() helps to get data for login screen, when getLoginScreenContent() is called, then it should return LoginScreenDataModel`() {
        // When
        val result = loginUseCaseImpl.getLoginScreenContent()
        // Then
        assertTrue(result is LoginScreenDataModel)
    }
}
