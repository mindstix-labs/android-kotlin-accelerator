/**
 * Copyright (c) 2023 Mindstix Software Labs
 * All rights reserved.
 */

package com.mindstix.capabilities.presentation.navigation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

/**
 * Data class representing the base state of components in the app.
 *
 * @param displayToolBar Determines whether the toolbar should be displayed.
 * @param displayBottomNavigationBar Determines whether the bottom navigation bar should be displayed.
 * @param displayFloatingActionButton Determines whether the floating action button should be displayed.
 *
 * @author Abhijeet Kokane
 */
data class BaseComponentState(
    var displayToolBar: MutableState<Boolean> = mutableStateOf(false),
    var displayBottomNavigationBar: MutableState<Boolean> = mutableStateOf(false),
    var displayFloatingActionButton: MutableState<Boolean> = mutableStateOf(false),
)
