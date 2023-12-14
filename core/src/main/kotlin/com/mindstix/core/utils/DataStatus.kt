/**
 * Copyright (c) 2023 Mindstix Software Labs
 * All rights reserved.
 */

package com.mindstix.core.utils

/**
 * Enum class representing the different states of data loading.
 * - [Empty]: Indicates that the data set is empty.
 * - [Offline]: Indicates that the device is offline.
 * - [Error]: Indicates an error occurred while loading data.
 * - [Success]: Indicates successful data loading.
 * - [Loading]: Indicates that data is currently being loaded.
 * This is part of the core utility classes in the application.
 *
 * @author Abhijeet Kokane
 */
enum class DataStatus {
    Empty,
    Offline,
    Error,
    Success,
    Loading,
}
