package com.mindstix.capabilities.presentation.navigation.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import com.mindstix.capabilities.presentation.navigation.BottomNavItem
import com.mindstix.capabilities.presentation.navigation.Destinations

object BottomItems {
    // List of Bottom Navigation items, each representing a screen in the app.
    val items: List<BottomNavItem>
        get() = listOf(
            BottomNavItem(
                name = "Home",
                route = Destinations.HomeDestination.route,
                icon = Icons.Default.Home,
            ),
            BottomNavItem(
                name = "Profile",
                route = Destinations.ProfileDestination.route,
                icon = Icons.Default.Person,
            ),
            BottomNavItem(
                name = "Settings",
                route = Destinations.SettingsDestination.route,
                icon = Icons.Default.Settings,
            ),
        )
}
