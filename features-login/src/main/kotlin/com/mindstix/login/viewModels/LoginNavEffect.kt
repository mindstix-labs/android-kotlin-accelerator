package com.mindstix.login.viewModels

import com.mindstix.core.base.NavEffect

/**
 * These are the Navigation Effects from Login Screen.
 */
sealed class LoginNavEffect : NavEffect {
    object CloseLoginScreen : LoginNavEffect()
    object NavigateToHomeScreenAfterSuccess : LoginNavEffect()
}
