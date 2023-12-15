/**
 * Copyright (c) 2023 Mindstix Software Labs
 * All rights reserved.
 */

package com.mindstix.onboarding.usecases

import com.mindstix.onboarding.models.LoginScreenDataModel

/**
 * Interface defining the contract for interacting with the Login Screen use case.
 *
 * Abhijeet Kokane
 */
interface LoginUseCase {
    /**
     * Retrieves the content for the Login Screen.
     *
     * @return An instance of [LoginScreenDataModel] containing data for rendering the Login Screen.
     */
    fun getLoginScreenContent(): LoginScreenDataModel
}
