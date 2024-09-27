/**
 * Copyright (c) 2023 Mindstix Software Labs
 * All rights reserved.
 */
package com.mindstix.capabilities.util

import android.util.Patterns
import com.mindstix.capabilities.util.Constants.DEFAULT_EMPTY_STRING
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

/**
 * Utility extensions for common operations on various types.
 *
 * This object provides extension functions for common scenarios involving String, Int, Long,
 * Float, Boolean, and date manipulations.
 *
 * @author Pranav Hadawale
 */
object CommonExtensions {
    /**
     * Provides a default empty string value for scenarios
     * where you prefer to avoid null checks on string variables.
     *
     * @return The default empty string.
     */
    fun String.Companion.defaultEmpty() = DEFAULT_EMPTY_STRING

    /**
     * Returns a default empty integer value (0) for scenarios
     * where you want to avoid null checks on integer variables.
     *
     * @return The default empty integer value (0).
     */
    fun Int.Companion.defaultEmpty() = 0

    /**
     * Returns a default empty long value (0L) for scenarios
     * where you want to avoid null checks on long variables.
     *
     * @return The default empty long value(0L).
     */
    fun Long.Companion.defaultEmpty() = 0L

    /**
     * Returns a default empty float value (0.0f) for scenarios
     * where you want to avoid null checks on float variables.
     *
     * @return The default empty float value (0.0f).
     */
    fun Float.Companion.defaultEmpty() = 0f

    /**
     * Returns a default empty boolean value (false) for scenarios
     * where you want to avoid null checks on boolean variables.
     *
     * @return The default empty boolean value (false).
     */
    fun Boolean.Companion.defaultFalse() = false

    /**
     * Checks if a nullable String is either null, empty, or consists of only whitespace characters
     * for scenarios where you need to determine if a string is effectively empty, including cases
     * where it is null or contains only whitespace.
     *
     * @return `true` if the string is null, empty, or blank; `false` otherwise.
     */
    fun String?.isEmptyOrNull(): Boolean = this.isNullOrEmpty() || this.isBlank()

    /**
     * Checks if a String consists of only numeric characters when you want to
     * determine if a string contains only numeric digits (0-9).
     *
     * @return `true` if the string is numeric; `false` otherwise.
     */
    fun String.isNumeric(): Boolean = all { it.isDigit() }

    /**
     * Gets the value of a nullable String or a default value if the string is null
     * when you need to retrieve the value of a string,
     * providing a default value in case the string is null.
     *
     * @param defaultValue The default value to return if the string is null.
     * @return The value of the string or the default value.
     */
    fun String?.getValueOrDefault(defaultValue: String): String = this ?: defaultValue

    /**
     * Gets the value of a nullable String or returns an empty string if null.
     * when you need to retrieve the value of a string,
     * providing a empty string in case the string is null.
     *
     * @return The value of the string or an empty string.
     */
    fun String?.getValueOrEmpty(): String = this ?: DEFAULT_EMPTY_STRING

    /**
     * Gets the value of a nullable Boolean or returns false if the boolean is null.
     * when you need to retrieve the value of a boolean,
     * providing false as the default value in case the boolean is null.
     *
     * @return The value of the boolean or false.
     */
    fun Boolean?.getValueOrFalse() = this ?: false

    /**
     * Gets the value of a nullable Boolean or returns true if the boolean is null.
     * when you need to retrieve the value of a boolean,
     * providing true as the default value in case the boolean is null.
     *
     * @return The value of the boolean or true.
     */
    fun Boolean?.getValueOrTrue() = this ?: true

    /**
     * Gets the value of a nullable Int or returns zero if the integer is null.
     * when you need to retrieve the value of an integer,
     * providing zero as the default value in case the integer is null.
     *
     * @return The value of the integer or zero.
     */
    fun Int?.getValueOrZero() = this ?: 0

    /**
     * Gets the value of a nullable Long or returns zero if the long is null.
     * when you need to retrieve the value of a long,
     * providing zero as the default value in case the long is null.
     *
     * @return The value of the long or zero.
     */
    fun Long?.getValueOrZero() = this ?: 0L

    /**
     * Converts the first character of the string to uppercase, while
     * preserving the case of the remaining characters.
     *
     * @return A new string with the first letter capitalized.
     */
    fun String.capitalizeFirstLetter(): String {
        return this.lowercase(Locale.getDefault()).replaceFirstChar { it.uppercase() }
    }

    /**
     * Title case capitalizes the first letter of each word in the string
     * while preserving the case of the remaining characters.
     *
     * @return A new string with each word in title case.
     */
    fun String.toTitleCase(): String {
        return this.split(" ").joinToString(" ") {
            it.replaceFirstChar { character ->
                if (character.isLowerCase()) {
                    character.titlecase(Locale.getDefault())
                } else {
                    character.toString()
                }
            }
        }
    }

    /**
     * Checks if the string is a valid date in ISO date-time format.
     *
     * @return true if the string represents a valid date, false otherwise.
     */
    fun String?.isValidDate(): Boolean {
        if (this.isNullOrEmpty()) {
            return false
        }
        val currentTimeZone = ZoneId.systemDefault()
        try {
            // Attempt to parse the string as an ISO date-time and adjust the time zone
            ZonedDateTime.parse(this, DateTimeFormatter.ISO_DATE_TIME)
                .withZoneSameInstant(currentTimeZone)
        } catch (e: Exception) {
            // An exception occurred during parsing, indicating an invalid date
            return false
        }
        // The string represents a valid date
        return true
    }

    /**
     * Checks if the given string is a valid email address.
     *
     * @param email The string to check for a valid email address.
     * @return true if the provided string is a valid email address, false otherwise.
     */
    fun String.isValidEmail(email: String): Boolean {
        val pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }
}
