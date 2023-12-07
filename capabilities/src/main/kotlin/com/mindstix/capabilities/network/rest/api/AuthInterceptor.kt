package com.mindstix.capabilities.network.rest.api

import com.mindstix.capabilities.network.rest.constants.ApiConstants.HTTP_HEADER_REQUEST_AUTHORIZATION_KEY
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

/**
 * This class is used to intercept request and apply Authorization Header from single place.
 */
@Singleton
class AuthInterceptor @Inject constructor() : Interceptor {

    @Volatile
    var authorizationHeaderValue: String? = null

    @Volatile
    var anonymousHeaderValue = ""

    init {
        setAuthHeaderValue("YOUR_TOKEN_HERE")
    }

    /**
     * Using this function we can sent the updated Auth Header in request.
     */
    private fun setAuthHeaderValue(authToken: String?) {
        if (authToken != null) {
            this.anonymousHeaderValue = authToken
        }
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        authorizationHeaderValue?.let {
            request.addHeader(
                HTTP_HEADER_REQUEST_AUTHORIZATION_KEY,
                it,
            )
        }
        return chain.proceed(request.build())
    }
}
