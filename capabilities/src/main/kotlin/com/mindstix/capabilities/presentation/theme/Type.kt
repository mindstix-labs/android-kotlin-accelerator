/**
 * Copyright (c) 2023 Mindstix Software Labs
 * All rights reserved.
 */

package com.mindstix.capabilities.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

/**
 * Typography for defining text styles.
 *
 * @author Abhijeet Kokane
 */

val Typography =
    Typography(
        displayLarge =
            TextStyle(
                // Default font family
                fontFamily = FontFamily.Default,
                // Normal font weight
                fontWeight = FontWeight.Normal,
                // Font size of 16sp
                fontSize = 16.sp,
            ),
    )
