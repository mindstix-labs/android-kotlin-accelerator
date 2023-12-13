package com.mindstix.login.viewModels

import com.mindstix.login.intents.LoginIntent
import com.mindstix.login.intents.LoginViewState
import com.mindstix.login.models.LoginScreenDataModel
import com.mindstix.login.usecases.LoginUseCase
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

class LoginViewModelTest {

    private lateinit var loginViewModel: LoginViewModel

    private val loginUseCase: LoginUseCase = spyk()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(Dispatchers.Unconfined)
        loginViewModel = LoginViewModel(loginUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `given createInitialState creates the state, when createInitialState is called, then it should return instance of LoginViewState`() {
        runBlocking {
            // When
            val result = loginViewModel.createInitialState()
            // Then
            assertTrue(result is LoginViewState)
        }
    }

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
