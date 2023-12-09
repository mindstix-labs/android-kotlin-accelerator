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
    object SplashDestination : Destinations("splash")
    object LoginDestination : Destinations("login")
    object HomeDestination : Destinations("home")
    object ProfileDestination : Destinations("profile")
    object SettingsDestination : Destinations("settings")
}
