/**
 * Copyright (c) 2023 Mindstix Software Labs
 * All rights reserved.
 */
package com.mindstix.capabilities.network.rest.data

/**
 * This class contains app-level error codes (not HTTP-related).
 *
 * @author Abhijeet Kokane
 */
object ErrorCode {
    const val NETWORK_NOT_AVAILABLE = 1001
    const val NETWORK_CONNECTION_FAILED = 1002
    const val DATABASE_ERROR = 1003
    const val FORCE_UPDATE = 1004
}
