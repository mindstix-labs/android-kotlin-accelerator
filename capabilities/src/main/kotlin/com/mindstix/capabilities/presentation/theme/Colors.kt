/**
 * Copyright (c) 2023 Mindstix Software Labs
 * All rights reserved.
 */

package com.mindstix.capabilities.presentation.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

// Color constants for light theme
val md_theme_light_primary = Color(0xFF825500)
val md_theme_light_onPrimary = Color(0xFFFFFFFF)
val md_theme_light_secondary = Color(0xFF6F5B40)
val md_theme_light_onSecondary = Color(0xFFFFFFFF)
val md_theme_light_background = Color(0xFFFFFBFF)
val md_theme_light_error = Color(0xFFBA1A1A)
val md_theme_light_errorContainer = Color(0xFFFFDAD6)

// Color constants for dark theme
val md_theme_dark_primary = Color(0xFFFFB951)
val md_theme_dark_onPrimary = Color(0xFF452B00)
val md_theme_dark_secondary = Color(0xFFDDC2A1)
val md_theme_dark_onSecondary = Color(0xFF3E2D16)
val md_theme_dark_background = Color(0xFF1F1B16)
val md_theme_dark_error = Color(0xFFFFB4AB)
val md_theme_dark_errorContainer = Color(0xFF93000A)

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
