package com.mindstix.capabilities.navigation

/**
 * Define Navigation Destinations.
 * Define the destinations for login and the three tabs.
 */
sealed class Destinations(val route: String) {
    data object SplashDestination : Destinations("splash")
    data object LoginDestination : Destinations("login")
    data class HomeDestination(val additionalArgument: String = "market") : Destinations("home") {
        companion object {
            val route: String = "home"
        }
    }

    data object ProfileDestination : Destinations("profile")
    data object SettingsDestination : Destinations("settings")
}
