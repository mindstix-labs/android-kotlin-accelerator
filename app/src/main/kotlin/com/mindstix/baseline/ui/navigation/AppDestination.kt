/**
 * Copyright (c) 2023 Mindstix Software Labs
 * All rights reserved.
 */
package com.mindstix.baseline.ui.navigation

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.rememberNavController
import com.mindstix.capabilities.presentation.navigation.BaseComponentState
import com.mindstix.capabilities.presentation.navigation.ui.BottomNavigationBar

/**
 * Main destination composable function.
 *
 * This composable function represents the main content structure of the application.
 * It includes the navigation host, bottom app bar, and floating action button based on
 * the state provided by the BaseComponentState.
 *
 * @author Abhijeet Kokane
 */
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainDestination() {
    // MutableState
    val navController = rememberNavController()

    // Create and remember the mutable state for the base component state
    val baseComponentState by remember { mutableStateOf(BaseComponentState()) }

    // Scaffold is used as the top-level container for the main content of the app
    Scaffold(
        bottomBar = {
            // Display the BottomAppBar with BottomNavigationBar if specified by the state
            if (baseComponentState.displayBottomNavigationBar.value) {
                BottomNavigationBar(navController = navController)
            }
        },
        floatingActionButton = {
            // Display the FloatingActionButton with an "Add" icon if specified by the state
            if (baseComponentState.displayFloatingActionButton.value) {
                FloatingActionButton(onClick = {}) {
                    Icon(Icons.Filled.Add, "Add")
                }
            }
        },
    ) { _ ->
        // Include the NavigationHost composable within the Box
        NavigationHost(
            navController = navController,
            baseComponentState = baseComponentState,
        )
    }
}
