/**
 * Copyright (c) 2023 Mindstix Software Labs
 * All rights reserved.
 */

package com.mindstix.onboarding.viewModels

import com.mindstix.onboarding.intents.LoginIntent
import com.mindstix.onboarding.intents.LoginViewState
import com.mindstix.onboarding.models.LoginScreenDataModel
import com.mindstix.onboarding.usecases.LoginUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.spyk
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

/**
 * This unit test exercises the LoginViewModel class.
 *
 * @author Abhishek Singh
 */
class LoginViewModelTest {
    private lateinit var loginViewModel: LoginViewModel

    private val loginUseCase: LoginUseCase = spyk()

    /**
     * This function executes before every test.
     */
    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(Dispatchers.Unconfined)
        loginViewModel = LoginViewModel(loginUseCase)
    }

    /**
     * This function executes after every test.
     */
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    /**
     * This test ensures that the createInitialState function returns an instance of LoginViewState.
     */
    @Test
    fun `creates the createInitialState, when createInitialState is called, then return instance of LoginViewState`() {
        runBlocking {
            // When
            val result = loginViewModel.createInitialState()
            // Then
            assertTrue(result is LoginViewState)
        }
    }

    /**
     * This test verifies that when the handleIntent function is called with LoginIntent.FetchLoginData,
     * the FetchLoginData block should be called.
     */
    @Test
    fun `given LoginIntent FetchLoginData, when handleIntent is called, then FetchLoginData block should be called`() {
        runBlocking {
            // Given
            val loginDataModel = mockk<LoginScreenDataModel>()

            coEvery {
                loginUseCase.getLoginScreenContent()
            } returns loginDataModel

            // When
            loginViewModel.handleIntent(LoginIntent.FetchLoginData)

            // Then
            coVerify {
                loginUseCase.getLoginScreenContent()
            }
        }
    }
}
