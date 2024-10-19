/**
 * Copyright (c) 2023 Mindstix Software Labs
 * All rights reserved.
 */

package com.mindstix.onboarding.navigation

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.navigation.NavController
import com.mindstix.capabilities.presentation.navigation.Destinations
import com.mindstix.onboarding.intents.LoginIntent
import com.mindstix.onboarding.intents.LoginNavEffect
import com.mindstix.onboarding.intents.LoginViewStates
import com.mindstix.onboarding.view.screens.LoginScreenApp
import com.mindstix.onboarding.view.screens.Question
import com.mindstix.onboarding.view.screens.QuestionType
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
//    floatingBubbleViewModel: FloatingBubbleViewModel
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
                navController.navigate(Destinations.HomeDestination.route)
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
                Question(1, "What is your name?", QuestionType.TEXT),
                Question(3, "How old are you?", QuestionType.TEXT),  // Can be improved with age picker (if available)
                Question(2, "What is your gender?", QuestionType.RADIO, listOf("Male", "Female", "Non-binary", "Prefer not to say")),
                Question(4, "What is your skin tone?", QuestionType.SKIN_TONE_PICKER),
                Question(5, "What is your skin type?", QuestionType.RADIO, listOf("Oily", "Dry", "Combination", "Sensitive", "Normal")),
                Question(6, "What are your main skin concerns?", QuestionType.CHECKBOX, listOf("Acne", "Wrinkles", "Dark Spots", "Dryness", "Redness", "Pigmentation", "Enlarged Pores")),
                Question(7, "Do you have any allergies or sensitivities?", QuestionType.CHECKBOX, listOf("Fragrance", "Alcohol", "Parabens", "Sulfates", "None")),
                Question(8, "Is it important for you to use eco-friendly products?", QuestionType.RADIO, listOf("Yes", "No"))
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

    val context = LocalContext.current
    BackHandler {
        (context as? Activity)?.finish()
    }
}
