package com.mindstix.login.viewModels

import com.mindstix.core.base.UserIntent

/**
 * This is the Login Module Intent.
 */
sealed class LoginIntent : UserIntent {
    object FetchLoginData : LoginIntent()
    object CloseLogin : LoginIntent()
}
