/**
 * Copyright (c) 2023 Mindstix Software Labs
 * All rights reserved.
 */

package com.mindstix.core.sharedpref.accessToken

import android.content.Context
import com.mindstix.core.models.SharedPrefType
import com.mindstix.core.sharedpref.SharedPref
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/**
 * @author Pranav Hadawale
 */
class UserDataStorageContractImpl @Inject constructor(
    @ApplicationContext val context: Context,
) : UserDataStorageContract {

    private val sharedPref =
        SharedPref.getInstanceOfSharePref(context, SharedPrefType.SecuredSharedPref, "USERDATA")

    override fun saveUserData(accessToken: String) {
        sharedPref.saveStringInPrefs("USER_INFO", accessToken)
    }

    override fun getUserData(): String? {
        return sharedPref.readStringFromPrefs("USER_INFO")
    }

    override fun removeSaveUserData() {
        sharedPref.removeFromPrefs("USER_INFO")
    }
}
