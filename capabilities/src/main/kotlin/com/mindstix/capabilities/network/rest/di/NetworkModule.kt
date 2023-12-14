/**
 * Copyright (c) 2023 Mindstix Software Labs
 * All rights reserved.
 */

package com.mindstix.capabilities.network.rest.di

import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mindstix.capabilities.network.rest.api.ApiConfig
import com.mindstix.capabilities.network.rest.api.AuthInterceptor
import com.mindstix.capabilities.util.Constants.NETWORK_NAMED_ARGUMENTS
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

// Constants
private const val READ_TIMEOUT = 60L
private const val CONNECT_TIMEOUT = 60L

/**
 * Dagger Hilt module for providing network-related dependencies.
 *
 * @author Abhijeet Kokane
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    /**
     * Provides the [AuthInterceptor] with a named annotation.
     */
    @Provides
    @Singleton
    @Named(NETWORK_NAMED_ARGUMENTS)
    fun provideAuthInterceptor(): AuthInterceptor {
        return AuthInterceptor()
    }

    /**
     * Provides the [OkHttpClient] for creating a Retrofit instance.
     */
    @Singleton
    @Provides
    @Named(NETWORK_NAMED_ARGUMENTS)
    fun provideOkHttp(
        @Named(NETWORK_NAMED_ARGUMENTS) authInterceptor: AuthInterceptor,
    ): OkHttpClient {
        val loggingInterceptor =
            HttpLoggingInterceptor { message ->
                Log.d("Okta-login", message)
            }
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(authInterceptor)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .build()
    }

    /**
     * Provides the [Gson] object for creating a Retrofit instance.
     */
    @Singleton
    @Provides
    @Named(NETWORK_NAMED_ARGUMENTS)
    fun providesGson(): Gson = GsonBuilder().setLenient().create()

    /**
     * Provides the [Retrofit] object based on [OkHttpClient] and [Gson] configuration.
     */
    @Singleton
    @Provides
    @Named(NETWORK_NAMED_ARGUMENTS)
    fun provideRetrofit(
        @Named(NETWORK_NAMED_ARGUMENTS) okHttpClient: OkHttpClient,
        @Named(NETWORK_NAMED_ARGUMENTS) gson: Gson,
    ): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl("") // TODO: Provide base URL
            .client(okHttpClient)
            .build()

    /**
     * Provides the implementation of [ApiConfig] for network operations.
     */
    @Provides
    fun provideRetrofitApi(
        @Named(NETWORK_NAMED_ARGUMENTS) retrofit: Retrofit,
    ): ApiConfig = retrofit.create(ApiConfig::class.java)
}
