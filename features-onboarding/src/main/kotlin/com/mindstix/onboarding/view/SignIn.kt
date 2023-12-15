/**
 * Copyright (c) 2023 Mindstix Software Labs
 * All rights reserved.
 */
package com.mindstix.onboarding.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import com.mindstix.capabilities.presentation.theme.buttonTextNormal
import com.mindstix.capabilities.presentation.theme.login_in_button_bg_color
import com.mindstix.features.login.R

/**
 * @author Asim Shah
 */

@Composable
fun SignIn() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 12.dp),
                style = buttonTextNormal,
                color = login_in_button_bg_color,
                text = "Sign in with".toUpperCase(Locale.current),
            )

            Image(
                modifier = Modifier.padding(horizontal = 12.dp),
                painter = painterResource(id = R.drawable.icon_google),
                contentDescription = "Google Login",
            )

            Image(
                painter = painterResource(id = R.drawable.icon_apple),
                contentDescription = "Apple Login",
            )

            Image(
                modifier = Modifier.padding(horizontal = 12.dp),
                painter = painterResource(id = R.drawable.icon_meta),
                contentDescription = "Meta Login",
            )
        }
    }
}
