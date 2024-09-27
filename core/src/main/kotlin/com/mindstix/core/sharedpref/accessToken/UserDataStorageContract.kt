/**
 * Copyright (c) 2023 Mindstix Software Labs
 * All rights reserved.
 */

package com.mindstix.core.sharedpref.accessToken

/**
 * @author Pranav Hadawale
 */
interface UserDataStorageContract {

    fun saveUserData(accessToken: String)

    fun getUserData(): String?

    fun removeSaveUserData()
}
