/**
 * Copyright (c) 2023 Mindstix Software Labs
 * All rights reserved.
 */
package com.mindstix.baseline.ui.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.mindstix.capabilities.presentation.navigation.BaseComponentState
import com.mindstix.capabilities.presentation.navigation.Destinations
import com.mindstix.core.logger.Logger
import com.mindstix.core.sharedpref.accessToken.UserDataStorageContract

/**
 * NavigationHost composable function.
 *
 * This composable function represents the navigation host for the Mindstix Android application.
 * It is responsible for defining the navigation graph using Jetpack Compose Navigation.
 *
 * @param navController The NavHostController responsible for navigating between destinations.
 * @param baseComponentState The base component state that affects the app component's visibility behavior.
 *
 * @author Abhijeet Kokane
 */
@Composable
fun NavigationHost(
    navController: NavHostController,
    baseComponentState: BaseComponentState,
    userDataStorageContract: UserDataStorageContract,
    isUserAdded: MutableState<Boolean>,
) {
    Logger.d { "##### isEmpty $isUserAdded" }
    // NavHost is used to define the navigation graph with various destination composable functions.
    NavHost(
        navController = navController,
        startDestination = if (isUserAdded.value) Destinations.LoginDestination.route else Destinations.HomeDestination.route,
        modifier = Modifier.fillMaxSize(),
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
    ) {
        // Define the navigation graph using destination composable functions for each screen.
        splashNavigationGraph(
            navController = navController,
            baseComponentState = baseComponentState,
        )
        loginNavigationGraph(
            navController = navController,
            baseComponentState = baseComponentState,
        )
        homeNavigationGraph(
            navController = navController,
            baseComponentState = baseComponentState,
            userDataStorageContract = userDataStorageContract,
        )
        profileNavigationGraph(
            navController = navController,
            baseComponentState = baseComponentState,
        )
        settingsNavigationGraph(
            navController = navController,
            baseComponentState = baseComponentState,
        )
    }
}
