/**
 * Copyright (c) 2023 Mindstix Software Labs
 * All rights reserved.
 */

package com.mindstix.capabilities.presentation.navigation

/**
 * Sealed class representing different destinations in the app.
 *
 * @author Abhijeet Kokane
 */
sealed class Destinations(val route: String) {
    data object SplashDestination : Destinations("splash")
    data object LoginDestination : Destinations("login")
    data object HomeDestination : Destinations("home")
    data object ProfileDestination : Destinations("profile")
    data object SettingsDestination : Destinations("settings")
}
