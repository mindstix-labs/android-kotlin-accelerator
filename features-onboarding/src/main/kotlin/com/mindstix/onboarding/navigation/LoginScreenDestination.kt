/**
 * Copyright (c) 2023 Mindstix Software Labs
 * All rights reserved.
 */

package com.mindstix.onboarding.navigation

import android.content.Context
import android.net.Uri
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.navigation.NavController
import com.google.gson.Gson
import com.mindstix.capabilities.presentation.navigation.Destinations
import com.mindstix.onboarding.intents.LoginIntent
import com.mindstix.onboarding.intents.LoginNavEffect
import com.mindstix.onboarding.intents.LoginViewStates
import com.mindstix.onboarding.view.LoginScreenApp
import com.mindstix.onboarding.view.Question
import com.mindstix.onboarding.view.QuestionType
import com.mindstix.onboarding.viewModels.LoginViewModel
import kotlinx.coroutines.flow.Flow

/**
 * Composable function for the Login Screen destination.
 *
 * @param context The Android application context.
 * @param loginViewModel The ViewModel for the Login Screen.
 * @param loginViewState The current state of the Login Screen.
 * @param navEffect Flow representing navigation effects from the Login Screen.
 * @param navController The navigation controller for handling navigation actions.
 *
 * @author Abhijeet Kokane
 */
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LoginScreenDestination(
    loginViewModel: LoginViewModel,
    loginViewState: LoginViewStates,
    navEffect: Flow<LoginNavEffect>,
    navController: NavController,
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    /**
     * Handles navigation based on [LoginNavEffect].
     *
     * @param navEvent The navigation event to handle.
     */
    fun handleNavigation(navEvent: LoginNavEffect) {
        when (navEvent) {
            is LoginNavEffect.CloseLoginScreen -> {
                // Close the Login Screen by popping back from the navigation stack.
                navController.popBackStack()
                return
            }

            is LoginNavEffect.OpenExternalLink -> {
                // Example: OpenExternalLinkCommonUtils.openChromeCustomTabs(navEvent.externalLink, context)
            }

            is LoginNavEffect.OpenHomeScreen -> {
                // Navigate to the Home Screen destination.
                val gson = Gson()
                val answersJson = Uri.encode(gson.toJson(navEvent.answers))  // Serialize the Map<String, Any> to JSON
                navController.navigate("${Destinations.HomeDestination.route}/${answersJson}")
            }
        }
    }

    // LaunchedEffect to trigger actions when the composable is launched.
    LaunchedEffect(Unit) {
        // Perform the FetchLoginData action when the composable is launched.
        loginViewModel.performAction(LoginIntent.FetchLoginData)
        // Collect and handle navigation effects.
        navEffect.collect { handleNavigation(it) }
    }

    /**
     * Provides a function to handle user actions.
     *
     * @return A function that takes [LoginIntent] as a parameter.
     */
    fun onUserAction(): (LoginIntent) -> Unit =
        {
            // Perform the specified user action using the ViewModel.
            loginViewModel.performAction(it)
        }

    // Main content of the Login Screen Destination.
    // Choose the appropriate content based on the current state of the Login Screen.
    when (loginViewState) {
        is LoginViewStates.LoadedData -> {
            val questions = listOf(
                Question("Name", QuestionType.TEXT),
                Question("Age", QuestionType.TEXT),
                Question("Gender", QuestionType.RADIO, listOf("Male", "Female")),
                Question("Height (cm)", QuestionType.TEXT),
                Question("Weight (kg)", QuestionType.TEXT),
                Question("Skin Tone", QuestionType.SKIN_TONE_PICKER),
                Question("Favorite Color", QuestionType.COLOR_PICKER),
                Question("Preferred Clothing Style", QuestionType.CHECKBOX, listOf("Casual", "Formal", "Sporty", "Streetwear", "Bohemian")),
                Question("Season", QuestionType.CHECKBOX, listOf("Rainy","Winter","Summer")),
                Question("Fit Preference", QuestionType.RADIO, listOf("Loose", "Regular", "Fitted")),
                Question("Typical Occasions", QuestionType.CHECKBOX, listOf("Work", "Casual Outings", "Parties", "Special Events")),
                Question("Favorite Brands", QuestionType.TEXT)
            )
            // Display the Login Screen with loaded data.
            LoginScreenApp(
                state = loginViewState,
                keyboardController = keyboardController,
                userIntent = onUserAction(),
                questions = questions,
            )
        }

        is LoginViewStates.InitialLoading -> {
            // Display a loading indicator for the initial loading state.
            Text("InitialLoading")
        }

        is LoginViewStates.Offline -> {
            // Display content for the offline state.
            Text("Offline")
        }

        is LoginViewStates.UnInitialized -> {
            // Display content for the uninitialized state.
            Text("UnInitialized")
        }
    }
}
