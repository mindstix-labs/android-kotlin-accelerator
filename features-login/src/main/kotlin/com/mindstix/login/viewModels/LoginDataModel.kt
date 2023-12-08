package com.mindstix.login.viewModels

import com.mindstix.core.utils.DataStatus

/**
 * This model class holds the status and Login screen data.
 */
data class LoginDataModel(
    val status: DataStatus,
    val loginScreenDataModel: LoginScreenDataModel,
)
