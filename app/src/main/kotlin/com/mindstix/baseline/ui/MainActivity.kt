/**
 * Copyright (c) 2023 Mindstix Software Labs
 * All rights reserved.
 */
package com.mindstix.baseline.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.mindstix.baseline.ui.navigation.MainDestination
import com.mindstix.capabilities.presentation.theme.AppTheme
import com.mindstix.core.logger.Logger
import com.mindstix.core.sharedpref.accessToken.UserDataStorageContract
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * MainActivity serves as the main entry point for the Mindstix Android application UI.
 * It extends ComponentActivity, which is part of the AndroidX Activity library.
 * The @AndroidEntryPoint annotation is used to indicate that Hilt should be used for
 * dependency injection in this Activity.
 *
 * @author Abhijeet Kokane
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    /**
     * Called when the activity is first created.
     *
     * This method is overridden to provide the main content of the activity.
     * The setContent block is used to set the content of the activity using Jetpack
     * Compose. The AppTheme composable is applied to the entire UI, providing a
     * consistent theme.
     *
     */
    @Inject
    lateinit var userDataStorageContract: UserDataStorageContract

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            AppTheme {
                // MainDestination is a composable that represents the main content
                // of the app. It is included within the Row composable.
//               val userData =  sharedPref.removeFromPrefs("UserData").toString()
//                val gson = Gson()
//                val mapType = object : TypeToken<Map<String, Any>>() {}.type
//                val answers: Map<String, Any> = gson.fromJson(userData, mapType)
                val data = userDataStorageContract.getUserData()
                Logger.d { "##### userDataStorageContract MainActivity $data and  ${data.isNullOrBlank()}" }
                MainDestination(userDataStorageContract = userDataStorageContract, data.isNullOrBlank())
            }
        }
    }
}
