/**
 * Copyright (c) 2023 Mindstix Software Labs
 * All rights reserved.
 */

package com.mindstix.floatingview.usecases

import android.content.Context
import android.graphics.Bitmap

/**
 * @author Pranav Hadawale
 */
interface GeminiUseCase {
    /**
     * Retrieves the content for the Login Screen.
     *
     * @return An instance of [LoginScreenDataModel] containing data for rendering the Login Screen.
     */
    fun addPrompt(
        image: Bitmap,
        applicationContext: Context,
        callback: (String) -> Unit,
    )
}
