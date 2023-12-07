package com.mindstix.capabilities.network.rest.di

import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mindstix.capabilities.network.rest.api.ApiConfig
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

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    @Named(NETWORK_NAMED_ARGUMENTS)
    fun provideAnaHostSelectionInterceptor(): com.mindstix.capabilities.network.rest.api.AuthInterceptor {
        return com.mindstix.capabilities.network.rest.api.AuthInterceptor()
    }

    /**
     * Create [OkHttpClient] is used to create a retrofit instance.
     */
    @Singleton
    @Provides
    @Named(NETWORK_NAMED_ARGUMENTS)
    fun provideOkHttp(
        @Named(NETWORK_NAMED_ARGUMENTS) oktaHostSelectionInterceptor: com.mindstix.capabilities.network.rest.api.AuthInterceptor,
    ): OkHttpClient {
        return if (true) {
            val loggingInterceptor =
                HttpLoggingInterceptor { message -> // Log the message along with the associated request tag
                    Log.d("Okta-login", message)
                }
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            OkHttpClient.Builder().addInterceptor(loggingInterceptor)
                .addInterceptor(oktaHostSelectionInterceptor)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .build()
        } else {
            OkHttpClient.Builder()
                .addInterceptor(oktaHostSelectionInterceptor)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .build()
        }
    }

    /**
     * Create [Gson] object is used to create a retrofit instance.
     */
    @Singleton
    @Provides
    @Named(NETWORK_NAMED_ARGUMENTS)
    fun providesGson() = GsonBuilder().setLenient().create()

    /**
     * Create retrofit object based on [okHttpClient] and [gson] configuration.
     */
    @Singleton
    @Provides
    @Named(NETWORK_NAMED_ARGUMENTS)
    fun provideRetrofit(
        @Named(NETWORK_NAMED_ARGUMENTS) okHttpClient: OkHttpClient,
        @Named(NETWORK_NAMED_ARGUMENTS) gson: Gson,
    ): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(gson))
        .baseUrl("")
        .client(okHttpClient).build()

    /**
     * Tell to Hilt how to construct the [ApiConfig] object.
     */
    @Provides
    fun provideRetrofitApi(
        @Named(NETWORK_NAMED_ARGUMENTS) retrofit: Retrofit,
    ): ApiConfig = retrofit.create(ApiConfig::class.java)
}
