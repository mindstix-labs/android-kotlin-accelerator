/**
 * Copyright (c) 2023 Mindstix Software Labs
 * All rights reserved.
 */

package com.mindstix.capabilities.presentation.navigation.ui

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
    // State variables to track the selected item and current route.
    var selectedItem by remember { mutableIntStateOf(0) }
    var currentRoute by remember { mutableStateOf(Destinations.HomeDestination.route) }

    // Update the selected item based on the current route.
    BottomItems.items.forEachIndexed { index, navigationItem ->
        if (navigationItem.route == currentRoute) {
            selectedItem = index
        }
    }

    // Build the Bottom Navigation Bar using Jetpack Compose.
    NavigationBar {
        BottomItems.items.forEachIndexed { index, item ->
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
