/** Copyright (c) 2023 Mindstix Software Labs All rights reserved. */

package com.mindstix.onboarding.intents

import com.mindstix.core.base.NavEffect
import com.mindstix.core.base.UserIntent
import com.mindstix.core.base.ViewState
import com.mindstix.onboarding.models.LoginScreenDataModel
import com.mindstix.onboarding.models.OfflineScreenDataModel

/**
 * Sealed class representing user intents for the login feature.
 *
 * @author Abhijeet Kokane
 */
sealed class LoginIntent : UserIntent {
    data object FetchLoginData : LoginIntent()
    data class NavigateToHomeScreen(
        val answers: SnapshotStateMap<String, Any>
    ) : LoginIntent()
}

/**
 * Sealed class representing navigation effects for the login feature.
 *
 * @author Abhijeet Kokane
 */
sealed class LoginNavEffect : NavEffect {
    data object CloseLoginScreen : LoginNavEffect()
    data class OpenHomeScreen(
        val answers: Map<String, Any>
    ) : LoginNavEffect()
    data object OpenExternalLink : LoginNavEffect()
}

/**
 * Sealed class representing different view states for the login feature.
 *
 * @author Abhijeet Kokane
 */
sealed class LoginViewStates {
    /**
     * Data class representing the loaded data state.
     *
     * @param data The login screen data model.
     */
    data class LoadedData(
        val data: LoginScreenDataModel,
    ) : LoginViewStates()

    /**
     * Data class representing the offline state.
     *
     * @param offlineContentModel The offline screen data model.
     */
    data class Offline(
        var offlineContentModel: OfflineScreenDataModel,
    ) : LoginViewStates()

    /** Object representing the initial loading state. */
    object InitialLoading : LoginViewStates()

    /** Object representing the uninitialized state. */
    object UnInitialized : LoginViewStates()
}

/**
 * Data class representing the overall view state for the login feature.
 *
 * @param loginViewState The specific login view state.
 */
data class LoginViewState(
    var loginViewState: LoginViewStates,
) : ViewState
