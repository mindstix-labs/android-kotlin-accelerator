package com.mindstix.login.viewModels

import com.mindstix.core.base.ViewState

sealed class LoginViewStateClass {

    data class LoadedData(
        val data: LoginDataModel,
    ) : LoginViewStateClass()

    data class Offline(
        var offlineContentModel: OfflineErrorModel,
    ) : LoginViewStateClass()

    object InitialLoading : LoginViewStateClass()

    object UnInitialized : LoginViewStateClass()
}

data class LoginViewState(
    var loginViewState: LoginViewStateClass,
) : ViewState
