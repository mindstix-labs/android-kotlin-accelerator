package com.mindstix.network.api

import android.util.Log
import com.mindstix.network.constants.ApiConstants.HTTP_HEADER_REQUEST_AUTHORIZATION_KEY
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

/**
 * This class is used to intercept request and apply Authorization Header in one place.
 */

@Singleton
class AuthInterceptor @Inject constructor(
    private val readAccessTokenContract: ReadAccessTokenContract,
) : Interceptor {

    @Volatile
    var authorizationHeaderValue: String? = null

    @Volatile
    var anonymousHeaderValue = ""

    init {
        chooseAuthorizer(AuthHeaderProvider.ANONYMOUS_TOKEN)
    }

    /***
     * This is the function used to switch between the Authorization header for JWT token and Anonymous Token.
     */
    fun chooseAuthorizer(authHeaderProvider: AuthHeaderProvider) {
        authorizationHeaderValue = when (authHeaderProvider) {
            AuthHeaderProvider.JWT_TOKEN -> {
                Log.d ("JWT_TOKEN","AuthHeaderProvider.JWT_TOKEN" )
                runBlocking {
                    readAccessTokenContract.getAccessToken().first()
                }
            }

            AuthHeaderProvider.ANONYMOUS_TOKEN -> {
                Log.d ( "AuthHeaderProvider","AuthHeaderProvider.ANONYMOUS_TOKEN" )
                anonymousHeaderValue
            }
        }
    }

    fun setAuthHeaderValue(authToken: String?) {
        Log.d("AUTH_HEADER new value","AUTH_HEADER new value: $authToken" )
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
