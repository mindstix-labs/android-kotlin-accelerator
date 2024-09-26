package com.mindstix.floatingview.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/** @author Apoorv Gupta */

@Composable
fun ExpandedComposeView(popBack: () -> Unit = {}) {

    Column(modifier = Modifier) {
        Column(
            modifier =
            Modifier
                .size(200.dp)
                .background(Color.LightGray),
        ) {
            Row {
                Button(onClick = { popBack() }) {
                    Text(text = "pop back!")
                }
            }
        }
    }
}
