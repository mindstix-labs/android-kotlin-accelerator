/**
 * Copyright (c) 2023 Mindstix Software Labs
 * All rights reserved.
 */

package com.mindstix.onboarding.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.navigation.NavController
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
            val questions =
                listOf(
                    Question(1, "What do people call you?", QuestionType.TEXT),
                    Question(2, "How many trips around the sun have you made?", QuestionType.TEXT),
                    Question(3, "What's your vibe?", QuestionType.RADIO, listOf("Male", "Female")),
                    Question(4, "How tall are you in centimeters?", QuestionType.TEXT),
                    Question(5, "What’s your weight in kilograms?", QuestionType.TEXT),
                    Question(6, "What shade are you rocking?", QuestionType.SKIN_TONE_PICKER),
                    Question(7, "What’s your color crush?", QuestionType.COLOR_PICKER),
                    Question(8, "Which color is a no-go for you?", QuestionType.COLOR_PICKER),
                    Question(9, "Which fashion vibes do you dig?", QuestionType.CHECKBOX, listOf("Casual", "Formal", "Sporty", "Streetwear", "Bohemian")),
                    Question(10, "Which seasons get you excited?", QuestionType.CHECKBOX, listOf("Rainy", "Winter", "Summer")),
                    Question(11, "How do you like your clothes to fit?", QuestionType.RADIO, listOf("Loose", "Regular", "Fitted")),
                    Question(12, "What occasions do you love to dress up for?", QuestionType.CHECKBOX, listOf("Work", "Casual Outings", "Parties", "Special Events")),
                    Question(13, "What are your brand obsessions?", QuestionType.TEXT),
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
