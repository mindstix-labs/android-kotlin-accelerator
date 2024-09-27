/**
 * Copyright (c) 2023 Mindstix Software Labs
 * All rights reserved.
 */

package com.mindstix.floatingview.usecases

import android.content.Context
import android.graphics.Bitmap
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import com.mindstix.capabilities.util.CommonExtensions.getValueOrEmpty
import com.mindstix.core.utils.EMPTY_STRING
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author Pranav Hadawale
 */
class TestUseCaseImpl
@Inject
constructor() : TestUseCase {
    private val generativeModel =
        GenerativeModel(
            modelName = "models/gemini-1.5-flash-latest",
            apiKey = "AIzaSyD9tFr5FN6NwIMQ6afdvvfIJUiTkwVp_Ms",
        )

    override fun addPrompt(
        image: Bitmap,
        applicationContext: Context,
        callback: (String) -> Unit,
    ) {
        val prompt = "I am a 26 years old guy, height 5.11 inch, weight around 72 kg, fair skin tone, " +
            "I have a black, grey, brown pant available to pair with and a black formal shoes and white sneakers, " +
            "this is one of the shirt, is this good for me? " +
            "Give a exact only json object having two keys -'isGood'(true or false) and 'reason' " +
            "with structure like - {'isGood': false,'reason': 'data....'}." +
            "Tipe : if its Red shirt respond 'isGood' = 'false'"
//        val bitmap = BitmapFactory.decodeResource(applicationContext.resources, image)
        CoroutineScope(Dispatchers.IO).launch {
            val response =
                try {
                    generativeModel.generateContent(
                        content {
                            image(image)
                            text(prompt)
                        },
                    )
                } catch (e: Exception) {
                    return@launch callback(EMPTY_STRING)
                }
            callback(response.text.getValueOrEmpty())
        }
    }
}
