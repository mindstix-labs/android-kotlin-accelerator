/*
 * Copyright (c) 2023 Mindstix Software Labs.
 *  All rights reserved.
 */
package com.mindstix.core.models

/**
 * Enum class representing different types of shared preferences.
 * This enum is designed to categorize shared preferences based on their security level.
 *
 * @property SecuredSharedPref Represents a secured/shared preferences with enhanced security features.
 * @property NormalSharedPref Represents a normal/shared preferences without additional security measures.
 *
 * @see com.mindstix.core.sharedpref.SharedPref
 * @see SecuredSharedPref
 * @see NormalSharedPref
 *
 * @author Nirav Patel
 */
enum class SharedPrefType {
    SecuredSharedPref,
    NormalSharedPref,
}
