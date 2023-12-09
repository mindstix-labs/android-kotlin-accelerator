/**
 * Copyright (c) 2023 Mindstix Software Labs
 * All rights reserved.
 */

package com.mindstix.capabilities.presentation.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

/**
 * Shapes for defining corner shapes
 *
 * @author Abhijeet Kokane
 */

val Shapes = Shapes(
    small = RoundedCornerShape(4.dp), // Small rounded corner shape with 4.dp radius
    medium = RoundedCornerShape(4.dp), // Medium rounded corner shape with 4.dp radius
    large = RoundedCornerShape(0.dp), // Large rounded corner shape with 0.dp radius (no rounding)
)
