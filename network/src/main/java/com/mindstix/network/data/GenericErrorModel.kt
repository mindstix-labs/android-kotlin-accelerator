package com.mindstix.network.data

/**
 * This class contains the generic error model.
 */
data class GenericErrorModel(
    val code: Int = -1,
    val message: String? = null,
    val status: String = "",
    val errorCode: String = "",
)