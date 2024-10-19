/**
 * Copyright (c) 2023 Mindstix Software Labs
 * All rights reserved.
 */

package com.mindstix.capabilities.presentation.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

// Color constants for light theme
val md_theme_light_primary = Color(0xFF6C79F0)  // Muted purple
val md_theme_light_onPrimary = Color(0xFFFFFFFF)  // Vibrant pink
val md_theme_light_secondary = Color(0xFFBC85A3)  // Soft lavender
val md_theme_light_onSecondary = Color(0xFFF9E1E0)  // Soft peach
val md_theme_light_background = Color(0xFFE7ECF0)  // Soft light grayish color
val md_theme_light_error = Color(0xFFFFB4AB)  // Light red for error states
val md_theme_light_errorContainer = Color(0xFF93000A) // Darker red for error container


// Color constants for dark theme
val md_theme_dark_primary = Color(0xFF6C79F0)       // Darker pink, maintaining the primary vibe from the light theme
val md_theme_dark_onPrimary = Color(0xFFFFFFFF)     // Deep maroon for text on primary color
val md_theme_dark_secondary = Color(0xFF9BA6FA)     // Purple as a secondary color, derived from image's hues
val md_theme_dark_onSecondary = Color(0xFF000000)   // Dark purple for text on secondary color
val md_theme_dark_background = Color(0xFF000000)    // Very dark desaturated purple, almost black, for background
val md_theme_dark_error = Color(0xFFFF647C)         // Soft red for error messages
val md_theme_dark_errorContainer = Color(0xFF8D021F) // Dark red for error containers


val primary = Color(0xFF2E1A47)
val secondary_main = Color(0xFF493266)
val tertiary_1 = Color(0xFF332347)
val tertiary_2 = Color(0xFFDED8EB)
val complementary = Color(0xFFB9975B)

val green = Color(0xFF0D6B10)
val card_background = Color(0xFF6C79F0)

// Light color scheme
val LightColors =
    lightColorScheme(
        primary = md_theme_light_primary,
        onPrimary = md_theme_light_onPrimary,
        secondary = md_theme_light_secondary,
        onSecondary = md_theme_light_onSecondary,
        background = md_theme_light_background,
        error = md_theme_light_error,
        errorContainer = md_theme_light_errorContainer,
    )

// Dark color scheme
val DarkColors =
    darkColorScheme(
        primary = md_theme_dark_primary,
        onPrimary = md_theme_dark_onPrimary,
        secondary = md_theme_dark_secondary,
        onSecondary = md_theme_dark_onSecondary,
        background = md_theme_dark_background,
        error = md_theme_dark_error,
        errorContainer = md_theme_dark_errorContainer,
    )

// Login
val text_field_bg_color = Color(0xFF1F1F1F)
val login_in_button_bg_color = Color(0xFFF2F2F2)
val text_field_label_color = Color(0xFFBDBDBD)
val divider_text_color = Color(0xFF3C3C3C)
