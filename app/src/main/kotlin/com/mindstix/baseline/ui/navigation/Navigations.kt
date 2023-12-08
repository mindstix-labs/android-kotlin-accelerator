package com.mindstix.baseline.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.mindstix.capabilities.navigation.Destinations
import com.mindstix.login.view.HomeScreen
import com.mindstix.login.view.LoginScreen
import com.mindstix.login.view.ProfileScreen
import com.mindstix.login.view.SettingsScreen
import com.mindstix.login.view.SplashScreen

fun NavGraphBuilder.splashNavigationGraph(
    navController: NavHostController,
) {
    composable(Destinations.SplashDestination.route) { entry ->
        SplashScreen()
    }
}

fun NavGraphBuilder.loginNavigationGraph(
    navController: NavHostController,
) {
    composable(Destinations.LoginDestination.route) { entry ->
        LoginScreen()
    }
}

fun NavGraphBuilder.homeNavigationGraph(
    navController: NavHostController,
) {
    composable(Destinations.HomeDestination.route) { entry ->
        HomeScreen()
    }
}

fun NavGraphBuilder.profileNavigationGraph(
    navController: NavHostController,
) {
    composable(Destinations.ProfileDestination.route) { entry ->
        ProfileScreen()
    }
}

fun NavGraphBuilder.settingsNavigationGraph(
    navController: NavHostController,
) {
    composable(Destinations.SettingsDestination.route) { entry ->
        SettingsScreen()
    }
}
