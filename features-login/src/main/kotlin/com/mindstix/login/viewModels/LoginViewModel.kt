package com.mindstix.login.viewModels

import com.mindstix.core.base.BaseViewModel
import com.mindstix.core.utils.DataStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    // Inject dependencies here
    private val loginUseCase: LoginUseCase,
) : BaseViewModel<LoginIntent, LoginViewState, LoginNavEffect>() {

    // ViewModel code here

    /**
     * InitialState of the Login Screen.
     */
    override fun createInitialState(): LoginViewState {
        return LoginViewState(LoginViewStateClass.UnInitialized)
    }

    /**
     * Handle Intents of the Login Screen.
     */
    override fun handleIntent(intent: LoginIntent) {
        when (intent) {
            is LoginIntent.FetchLoginData -> {
                // Fetch Login related information
                loginUseCase.getLoginScreenContent()

                val loginDataModel = LoginDataModel(
                    DataStatus.Success,
                    LoginScreenDataModel.defaultValue,
                )

                // On success display data on Login Screen
                renderLoginScreenDetails(loginDataModel)
            }

            LoginIntent.CloseLogin -> {
                // Close Login Screen
            }
        }
    }

    private fun renderLoginScreenDetails(loginDataModel: LoginDataModel) {
        // TODO: Render Login Screen [loginDataModel]
    }
}
