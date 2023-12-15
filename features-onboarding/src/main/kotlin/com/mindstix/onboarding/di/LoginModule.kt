/**
 * Copyright (c) 2023 Mindstix Software Labs
 * All rights reserved.
 */

package com.mindstix.onboarding.di

import com.mindstix.onboarding.usecases.LoginUseCase
import com.mindstix.onboarding.usecases.LoginUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Dagger Hilt module for providing dependencies related to the login feature.
 *
 * @author Abhijeet Kokane
 */
@Module
@InstallIn(SingletonComponent::class)
class LoginModule {
    /**
     * Provides the implementation of the [LoginUseCase] interface.
     *
     * @param loginUseCaseImpl The concrete implementation of [LoginUseCase].
     * @return An instance of [LoginUseCase].
     */
    @Provides
    fun provideLoginRepo(loginUseCaseImpl: LoginUseCaseImpl): LoginUseCase = loginUseCaseImpl
}
