/**
 * Copyright (c) 2023 Mindstix Software Labs
 * All rights reserved.
 */
package com.mindstix.capabilities.presentation.reusableComponents.alertdialog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mindstix.capabilities.presentation.theme.AppTheme

/**
 * Composable function demonstrating the usage of AlertDialog in Jetpack Compose.
 *
 * @author Abhijeet Kokane
 */
@Composable
fun AlertDialogSample() {
    var showDialog by remember { mutableStateOf(false) }

    Column(
        modifier =
            Modifier
                .padding(4.dp),
    ) {
        Button(onClick = { showDialog = true }) {
            Text("Show Alert Dialog")
        }

        if (showDialog) {
            AlertDialog(
                onDismissRequest = {
                    // Handle dismiss action
                    showDialog = false
                },
                title = {
                    Text(text = "Alert Title")
                },
                text = {
                    Text("This is the content of the alert dialog.")
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            // Handle positive action
                            showDialog = false
                        },
                    ) {
                        Text("OK")
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            // Handle negative action
                            showDialog = false
                        },
                    ) {
                        Text("Cancel")
                    }
                },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AlertDialogSamplePreview() {
    AppTheme {
        AlertDialogSample()
    }
}
