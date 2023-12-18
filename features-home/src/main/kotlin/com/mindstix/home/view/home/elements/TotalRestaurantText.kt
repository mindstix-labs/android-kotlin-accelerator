package com.mindstix.home.view.home.elements

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/*
* function for total restaurant text
* totRest - total restaurant text
* */

@Composable
fun TotalRestaurantText(totRest: String) {
    Text(
        text = totRest,
        style = MaterialTheme.typography.labelMedium,
        color = Color.Black,
        modifier = Modifier.padding(horizontal = 16.dp),
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold
    )
}