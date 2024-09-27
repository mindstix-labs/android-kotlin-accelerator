/**
 * Copyright (c) 2023 Mindstix Software Labs
 * All rights reserved.
 */

package com.mindstix.capabilities.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.mindstix.capabilities.R

/**
 * Typography for defining text styles.
 *
 * @author Abhijeet Kokane
 */


private val appFontFamily = FontFamily(
//    fonts = listOf(
        Font(
            resId = R.font.poppins_regular,
            weight = FontWeight.W700,
            style = FontStyle.Normal
        ),
        Font(
            resId = R.font.poppins_italic,
            weight = FontWeight.W900,
            style = FontStyle.Italic
        ),
        Font(
            resId = R.font.poppins_semibold,
            weight = FontWeight.ExtraBold,
        ),
//)
)
val Typography =
    Typography(
        displayLarge =
        TextStyle(
            // Default font family
            fontFamily = appFontFamily,
            // Normal font weight
            fontWeight = FontWeight.Normal,
            // Font size of 16sp
            fontSize = 16.sp,
        ),
        headlineLarge = Typography().headlineLarge.copy(fontFamily = appFontFamily),
        displayMedium = Typography().displayMedium.copy(fontFamily = appFontFamily),
        displaySmall = Typography().displaySmall.copy(fontFamily = appFontFamily),
        headlineMedium = Typography().headlineMedium.copy(fontFamily = appFontFamily),
        headlineSmall = Typography().headlineSmall.copy(fontFamily = appFontFamily),
        titleLarge = Typography().titleLarge.copy(fontFamily = appFontFamily),
        titleMedium = Typography().titleMedium.copy(fontFamily = appFontFamily),
        titleSmall = Typography().titleSmall.copy(fontFamily = appFontFamily),
        bodyLarge = Typography().bodyLarge.copy(fontFamily = appFontFamily),
        bodyMedium = Typography().bodyMedium.copy(fontFamily = appFontFamily),
        bodySmall = Typography().bodySmall.copy(fontFamily = appFontFamily),
        labelLarge = Typography().labelLarge.copy(fontFamily = appFontFamily),
        labelMedium = Typography().labelMedium.copy(fontFamily = appFontFamily),
        labelSmall = Typography().labelSmall.copy(fontFamily = appFontFamily),

    )

val textFieldLabel =
    TextStyle(
        fontFamily = appFontFamily,
        // Normal font weight
        fontWeight = FontWeight.Normal,
        // Font size of 13sp
        fontSize = 13.sp,
    )

val buttonTextBold =
    TextStyle(
        fontFamily = appFontFamily,
        // Normal font weight
        fontWeight = FontWeight.Bold,
        // Font size of 13sp
        fontSize = 13.sp,
        letterSpacing = 3.sp,
    )

val buttonTextNormal =
    TextStyle(
        fontFamily = appFontFamily,
        // Normal font weight
        fontWeight = FontWeight.Normal,
        // Font size of 13sp
        fontSize = 13.sp,
        letterSpacing = 3.sp,
    )

val textStyle1 =
    TextStyle(
        fontFamily = appFontFamily,
        // Normal font weight
        fontWeight = FontWeight.Normal,
        // Font size of 13sp
        fontSize = 13.sp,
    )

val textStyle2 =
    TextStyle(
        fontFamily = appFontFamily,
        // Normal font weight
        fontWeight = FontWeight.Bold,
        // Font size of 13sp
        fontSize = 13.sp,
    )

val textStyleLight =
    TextStyle(
        fontFamily = appFontFamily,
        // Normal font weight
        fontWeight = FontWeight.Light,
        // Font size of 13sp
        fontSize = 13.sp,
    )
