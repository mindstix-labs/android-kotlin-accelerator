/**
 * Copyright (c) 2023 Mindstix Software Labs
 * All rights reserved.
 */

package com.mindstix.core.di

import android.content.Context
import com.mindstix.core.sharedpref.accessToken.UserDataStorageContract
import com.mindstix.core.sharedpref.accessToken.UserDataStorageContractImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CoreModule {
    @Provides
    @Singleton
    fun provideAccessTokenStorageContract(@ApplicationContext context: Context): UserDataStorageContract {
        return UserDataStorageContractImpl(context)
    }
}
