/**
 * Copyright (c) 2023 Mindstix Software Labs
 * All rights reserved.
 */
package com.mindstix.onboarding.viewModels

import androidx.lifecycle.viewModelScope
import com.mindstix.capabilities.presentation.reusableComponents.commonscreens.OfflineScreenDataModel
import com.mindstix.core.base.BaseViewModel
import com.mindstix.onboarding.intents.LoginIntent
import com.mindstix.onboarding.intents.LoginNavEffect
import com.mindstix.onboarding.intents.LoginViewState
import com.mindstix.onboarding.intents.LoginViewStates
import com.mindstix.onboarding.intents.LoginViewStates.LoadedData
import com.mindstix.onboarding.models.LoginScreenDataModel
import com.mindstix.onboarding.usecases.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel responsible for handling the business logic and state of the Login Screen.
 *
 * @param loginUseCase The use case responsible for providing login-related data.
 *
 * @author Abhijeet Kokane
 */
@HiltViewModel
class LoginViewModel
@Inject
constructor(
    private val loginUseCase: LoginUseCase,
) : BaseViewModel<LoginIntent, LoginViewState, LoginNavEffect>() {
    /**
     * Initial state of the Login Screen.
     */
    override fun createInitialState(): LoginViewState {
        return LoginViewState(LoadedData(LoginScreenDataModel.defaultValue))
    }

    /**
     * Handle user intents for the Login Screen.
     *
     * @param intent The user intent triggering an action.
     */
    override fun handleIntent(intent: LoginIntent) {
        when (intent) {
            is LoginIntent.FetchLoginData -> {
                // Fetch login-related information and execute API calls
                fetchLoginData()

                // Update the UI with the fetched data
                // renderLoginScreenDetails(loginDataModel)
            }

            LoginIntent.NavigateToHomeScreen -> {
                // Trigger navigation to the Home Screen
                sendNavEffect {
                    LoginNavEffect.OpenHomeScreen
                }
            }
        }
    }

    private fun fetchLoginData() {
        emitViewState {
            copy(loginViewState = LoginViewStates.InitialLoading)
        }

        try {
            val loginDataModel = loginUseCase.getLoginScreenContent()
            // Update the UI with fetched data
            renderLoginScreenDetails(loginDataModel)
        } catch (e: Exception) {
            // Handle offline or error state
            emitViewState {
                copy(
                    loginViewState = LoginViewStates.Offline(
                        offlineContentModel = OfflineScreenDataModel.emptyValue,
                    )
                )
            }
        }
    }

    fun retryFetchingData() {
        val currentState = currentState.loginViewState
        if (currentState is LoginViewStates.Offline) {
            emitViewState {
                copy(loginViewState = LoadedData(LoginScreenDataModel.defaultValue))
            }
        }

        viewModelScope.launch {
            delay(3000) // Simulate a retry delay

            try {
                val loginDataModel = loginUseCase.getLoginScreenContent()
                renderLoginScreenDetails(loginDataModel)
            } catch (e: Exception) {
                emitViewState {
                    copy(
                        loginViewState = LoginViewStates.Offline(
                            offlineContentModel = OfflineScreenDataModel.emptyValue
                        )
                    )
                }
            }
        }
    }


    /**
     * Update the ViewState with the fetched login screen details.
     *
     * @param loginDataModel The data model containing login screen details.
     */
    private fun renderLoginScreenDetails(loginDataModel: LoginScreenDataModel) {
        emitViewState {
            copy(
                loginViewState =
                LoadedData(
                    loginDataModel,
                ),
            )
        }
    }
}
