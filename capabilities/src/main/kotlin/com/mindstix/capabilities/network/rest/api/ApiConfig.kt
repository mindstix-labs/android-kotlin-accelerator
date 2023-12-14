/**
 * Copyright (c) 2023 Mindstix Software Labs
 * All rights reserved.
 */
package com.mindstix.capabilities.network.rest.api

import com.mindstix.core.models.SampleRequest
import com.mindstix.core.models.SampleResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

/**
 * API configurations interface for defining API calls.
 *
 * @author Abhijeet Kokane
 */
interface ApiConfig {
    /**
     * Example API call to retrieve account information.
     *
     * @param userId The user ID for which the account information is requested.
     * @param authToken The authorization token for authentication.
     * @param sampleRequest The request body containing sample data.
     * @return A [Response] containing the [SampleResponse] or null.
     */
    @POST("v1/path/{userId}/account")
    suspend fun getAccount(
        @Path("userId") userId: String,
        @Header("Authorization") authToken: String,
        @Body sampleRequest: SampleRequest,
    ): Response<SampleResponse?>
}
