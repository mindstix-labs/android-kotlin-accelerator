/**
 * Copyright (c) 2023 Mindstix Software Labs
 * All rights reserved.
 */

package com.mindstix.capabilities.presentation.navigation

import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Data class representing an item in the Bottom Navigation Bar.
 *
 * @param name The name or label of the item.
 * @param route The destination route associated with the item.
 * @param icon The vector icon representing the item.
 * @param badgeCount The badge count to display on the item (default is 0).
 *
 * @author Abhijeet Kokane
 */
data class BottomNavItem(
    val name: String,
    val route: String,
    val icon: ImageVector,
    val badgeCount: Int = 0,
)
