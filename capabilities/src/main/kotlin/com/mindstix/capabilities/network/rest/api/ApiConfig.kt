package com.mindstix.capabilities.network.rest.api

import com.mindstix.core.models.SampleRequest
import com.mindstix.core.models.SampleResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

/**
 * This class contains API configurations for API calls.
 */
interface ApiConfig {

    @GET("v1/path/{userId}/account")
    suspend fun getAccount(
        @Path("userId") userId: String,
        @Header("Authorization") authToken: String,
        @Body sampleRequest: SampleRequest,
    ): Response<SampleResponse?>
}
