/**
 * Copyright (c) 2023 Mindstix Software Labs
 * All rights reserved.
 */

package com.mindstix.capabilities.presentation.navigation.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import com.mindstix.capabilities.presentation.navigation.BottomNavItem
import com.mindstix.capabilities.presentation.navigation.Destinations

/**
 * Composable function representing the Bottom Navigation Bar in the app.
 *
 * This Bottom Navigation Bar provides navigation between Home, Profile, and Settings screens.
 *
 * @param navController The NavController used for navigation within the app.
 *
 * @author Abhijeet Kokane
 */
@Composable
fun BottomNavigationBar(navController: NavController) {
    // List of Bottom Navigation items, each representing a screen in the app.
    val items =
        listOf(
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

    // State variables to track the selected item and current route.
    var selectedItem by remember { mutableIntStateOf(0) }
    var currentRoute by remember { mutableStateOf(Destinations.SplashDestination.route) }

    // Update the selected item based on the current route.
    items.forEachIndexed { index, navigationItem ->
        if (navigationItem.route == currentRoute) {
            selectedItem = index
        }
    }

    // Build the Bottom Navigation Bar using Jetpack Compose.
    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                alwaysShowLabel = true,
                icon = { Icon(item.icon, contentDescription = item.name) },
                label = { Text(item.name) },
                selected = selectedItem == index,
                onClick = {
                    selectedItem = index
                    currentRoute = item.route
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
            )
        }
    }
}
