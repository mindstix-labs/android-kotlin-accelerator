/**
 * Copyright (c) 2023 Mindstix Software Labs
 * All rights reserved.
 */

package com.mindstix.onboarding.network.rest.api

import com.mindstix.capabilities.network.rest.api.ApiConfig
import com.mindstix.core.models.SampleRequest
import com.mindstix.core.models.SampleResponse
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Test
import retrofit2.Response

/**
 * This unit test exercises the ApiConfig.
 *
 * @author Abhishek Singh
 */
class ApiConfigTest {
    private val apiConfigMock: ApiConfig = mockk()

    /**
     * This test verifies the behavior of the getAccount() function when a success response is received.
     */
    @Test
    fun `getAccount success response`() =
        runBlocking {
            // Given
            val userId = "123"
            val authToken = "your_auth_token"
            val sampleRequest = SampleRequest()

            val successResponse = SampleResponse()

            coEvery {
                apiConfigMock.getAccount(userId, authToken, sampleRequest)
            } returns Response.success(successResponse)

            // When
            val result = apiConfigMock.getAccount(userId, authToken, sampleRequest)

            // Then
            assertTrue(result.isSuccessful)
            assertEquals(successResponse, result.body())
        }

    /**
     * This test verifies the behavior of the getAccount() function when an error response is received.
     */
    @Test
    fun `getAccount error response`() =
        runBlocking {
            // Given
            val userId = "123"
            val authToken = "your_auth_token"
            val sampleRequest = SampleRequest()

            val errorResponseBody = "Error message".toResponseBody("text/plain".toMediaType())
            val errorResponse = Response.error<SampleResponse?>(400, errorResponseBody)

            coEvery {
                apiConfigMock.getAccount(userId, authToken, sampleRequest)
            } returns errorResponse

            // When
            val result = apiConfigMock.getAccount(userId, authToken, sampleRequest)

            // Then
            assertFalse(result.isSuccessful)
            assertEquals(400, result.code())
            assertNotNull(result.errorBody())
        }
}
