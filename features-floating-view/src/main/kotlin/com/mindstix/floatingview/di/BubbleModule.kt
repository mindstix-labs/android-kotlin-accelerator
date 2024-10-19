/**
 * Copyright (c) 2023 Mindstix Software Labs
 * All rights reserved.
 */

package com.mindstix.floatingview.di

import com.mindstix.floatingview.service.FloatingBubbleService
import com.mindstix.floatingview.service.FloatingBubbleServiceImpl
import com.mindstix.floatingview.usecases.GeminiUseCase
import com.mindstix.floatingview.usecases.GeminiUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * @author Pranav Hadawale
 */
@Module
@InstallIn(SingletonComponent::class)
interface BubbleModule {

    @Binds
    abstract fun bindTestUseCase(
        impl: GeminiUseCaseImpl
    ): GeminiUseCase
//    @Provides
//    @Singleton
//    fun provideTestUseCase(userDataStorageContract: UserDataStorageContract): TestUseCase {
//        return TestUseCaseImpl(userDataStorageContract)
//    }
    @Binds
    abstract fun provideFloatingBubbleServiceImpl(
        impl: FloatingBubbleServiceImpl,
    ): FloatingBubbleService
}
