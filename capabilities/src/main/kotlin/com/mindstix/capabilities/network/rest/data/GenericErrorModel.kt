/**
 * Copyright (c) 2023 Mindstix Software Labs
 * All rights reserved.
 */
package com.mindstix.capabilities.network.rest.data

/**
 * This class contains the generic error model.
 *
 * @property code The error code.
 * @property message The error message.
 * @property status The error status.
 * @property errorCode The specific error code.
 *
 * @author Abhijeet Kokane
 */
data class GenericErrorModel(
    val code: Int = -1,
    val message: String? = null,
    val status: String = "",
    val errorCode: String = "",
)
