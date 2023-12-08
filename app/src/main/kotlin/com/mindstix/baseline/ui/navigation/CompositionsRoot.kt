package com.mindstix.baseline.ui.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.mindstix.capabilities.navigation.Destinations

@Composable
fun CompositionRoot(navController: NavHostController, modifier: Modifier) {
    NavHost(
        navController = navController,
        startDestination = Destinations.LoginDestination.route,
        modifier = modifier,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
    ) {
        splashNavigationGraph(
            navController = navController,
        )
        loginNavigationGraph(
            navController = navController,
        )
        // Bottom Tabs
        homeNavigationGraph(
            navController = navController,
        )
        profileNavigationGraph(
            navController = navController,
        )
        settingsNavigationGraph(
            navController = navController,
        )
    }
}
