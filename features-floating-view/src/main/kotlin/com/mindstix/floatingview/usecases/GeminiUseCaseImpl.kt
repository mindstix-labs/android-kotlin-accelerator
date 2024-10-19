/**
 * Copyright (c) 2023 Mindstix Software Labs
 * All rights reserved.
 */

package com.mindstix.floatingview.usecases

import android.content.Context
import android.graphics.Bitmap
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mindstix.capabilities.util.CommonExtensions.getValueOrEmpty
import com.mindstix.core.sharedpref.accessToken.UserDataStorageContract
import com.mindstix.core.utils.API_KEY
import com.mindstix.core.utils.EMPTY_STRING
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author Pranav Hadawale
 */
class GeminiUseCaseImpl @Inject constructor(
    private val userDataStorageContract: UserDataStorageContract
) : GeminiUseCase {
    private val generativeModel =
        GenerativeModel(
            modelName = "models/gemini-1.5-flash-latest",
            apiKey = API_KEY,
        )

    override fun addPrompt(
        image: Bitmap,
        applicationContext: Context,
        callback: (String) -> Unit,
    ) {
        val gson = Gson()
        val mapType = object : TypeToken<Map<String, Any>>() {}.type
        val userDataMap = userDataStorageContract.getUserData()
        val userData: Map<String, Any> = gson.fromJson(userDataMap, mapType)
        val prompt1 = "These are my personal details - $userData Based on this data Give me opinion on this product image if its good for me or not, " +
                "Give response exact only json object having two keys -'isGood'(true or false) and 'reason' " +
                    "with structure like - {'isGood': false,'reason': 'data....'}." +
                    "Tip : if its Red shirt respond 'isGood' = 'false'"
        val prompt = "Here are my personal details: $userData. Based on this information, " +
                "please provide your opinion on whether the product in the image is suitable for me or not " +
                "Respond only with a JSON object containing two keys: 'isGood' (true or false) and 'reason'.  reason should be consist of 20-25 words or less. " +
                "The response should follow this structure: {'isGood': false, 'reason': 'data...'}, for decision making, pls use this data model "

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
