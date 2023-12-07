package com.mindstix.capabilities.presentation.reusableComponents.loader

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.mindstix.capabilities.presentation.theme.ll_surrounding_spacing
import com.mindstix.capabilities.presentation.theme.md_theme_light_primary
import com.mindstix.capabilities.presentation.theme.xs_surrounding_spacing

@Composable
fun CircularProgressBar(
    modifier: Modifier = Modifier,
    progressColor: Color = md_theme_light_primary,
    size: Dp = ll_surrounding_spacing,
    strokeWidth: Dp = xs_surrounding_spacing,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        CircularProgressIndicator(
            modifier = modifier.size(size),
            color = progressColor,
            strokeWidth = strokeWidth,
        )
    }
}
