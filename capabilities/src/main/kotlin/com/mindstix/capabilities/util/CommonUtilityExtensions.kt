/**
 * Copyright (c) 2023 Mindstix Software Labs
 * All rights reserved.
 */

package com.mindstix.capabilities.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.util.Patterns
import com.mindstix.capabilities.util.Constants.DEFAULT_EMPTY_STRING
import java.net.URL
import java.net.URLDecoder
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.Locale

/**
 * Utility class providing common extensions and functions for various tasks.
 *
 * This class contains utility functions related to network connectivity, default values,
 * string manipulation, date handling, and URL operations, locale handling.
 *
 * @author Pranav Hadawale
 */
object CommonUtilityExtensions {

    /**
     * Checks whether the device is currently connected to a mobile or Wi-Fi network.
     *
     * @param context The application or activity context.
     * @return `true` if the device is connected to a mobile or Wi-Fi network, `false` otherwise.
     */
    fun isConnectedToNetwork(context: Context): Boolean {
        val networkConnectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val networkCapabilities =
            networkConnectivityManager.getNetworkCapabilities(networkConnectivityManager.activeNetwork)

        val isConnectedToMobileNetwork =
            networkCapabilities?.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ?: false

        val isConnectedToWifiNetwork =
            networkCapabilities?.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ?: false

        return isConnectedToMobileNetwork || isConnectedToWifiNetwork
    }

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
     * Gets the current date as a [LocalDate] object.
     *
     * @return The current date.
     */
    fun getCurrentDate(): LocalDate {
        return LocalDate.now()
    }

    /**
     * Gets the current date and time as a [LocalDateTime] object.
     *
     * @return The current date and time.
     */
    fun getCurrentDateTime(): LocalDateTime {
        return LocalDateTime.now()
    }

    /**
     * Formats the provided [date] to a string representation based on the specified [pattern].
     * The default pattern is "yyyy-MM-dd".
     *
     * @param date The [LocalDate] to format.
     * @param pattern The pattern to use for formatting (default is "yyyy-MM-dd").
     * @return A formatted string representation of the [date].
     */
    fun formatDateToString(date: LocalDate, pattern: String = "yyyy-MM-dd"): String {
        val formatter = DateTimeFormatter.ofPattern(pattern)
        return date.format(formatter)
    }

    /**
     * Parses the provided [dateString] to a [LocalDate] object based on the specified [pattern].
     * The default pattern is "yyyy-MM-dd".
     *
     * @param dateString The string representation of the date to parse.
     * @param pattern The pattern to use for parsing (default is "yyyy-MM-dd").
     * @return A [LocalDate] object representing the parsed date.
     * @throws DateTimeParseException if the input string is not in the expected format.
     */
    fun parseStringToDate(dateString: String, pattern: String = "yyyy-MM-dd"): LocalDate {
        val formatter = DateTimeFormatter.ofPattern(pattern)
        return try {
            LocalDate.parse(dateString, formatter)
        } catch (e: DateTimeParseException) {
            throw DateTimeParseException("Failed to parse date: $dateString", dateString, 0, e)
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
     * Given a base [url] and a map of [parameters], this function constructs a new URL string
     * with the appended query parameters.
     *
     * @param url The base URL to which query parameters will be appended.
     * @param parameters A map of key-value pairs representing the query parameters.
     * @return A new URL string with the appended query parameters.
     */
    fun appendQueryParameters(url: String, parameters: Map<String, String>): String =
        buildString {
            append(url)
            if (url.contains("?").not()) {
                append('?')
            }
            parameters.entries.joinTo(this, "&") { (key, value) ->
                "${key.urlEncodeToUTF8()}=${value.urlEncodeToUTF8()}"
            }
        }

    /**
     * URL-encodes the string using UTF-8 encoding.
     *
     * @return The URL-encoded string.
     */
    private fun String.urlEncodeToUTF8(): String {
        return java.net.URLEncoder.encode(this, "UTF-8")
    }

    /**
     * Removes a specified query parameter from a URL.
     *
     * Given a [url] and a query parameter [key], this function constructs a new [Uri]
     * with the specified query parameter removed. If the URL does not contain the specified
     * parameter, the original [Uri] is returned.
     *
     * @param url The URL from which to remove the query parameter.
     * @param key The key of the query parameter to remove.
     * @return A new [Uri] with the specified query parameter removed or the original [Uri]
     * if the parameter is not present.
     */
    fun removeSpecifiedQueryParameter(url: String, key: String): Uri? {
        val uri = Uri.parse(url)
        val params = uri.queryParameterNames
        if (!params.contains(key)) {
            return uri
        }
        if (!params.contains(key)) {
            return uri
        }

        val newUriBuilder = uri.buildUpon().clearQuery()

        for (param in params) {
            if (param != key) {
                newUriBuilder.appendQueryParameter(param, uri.getQueryParameter(param))
            }
        }
        return newUriBuilder.build()
    }

    /**
     * Gets the value of a specified query parameter from a URL.
     *
     * Given a [url] and a query parameter [paramName], this function retrieves the value
     * associated with the specified parameter from the URLs query string.
     *
     * @param url The URL from which to retrieve the query parameter value.
     * @param paramName The name of the query parameter whose value is to be retrieved.
     * @return The value of the specified query parameter, or null if the parameter is not found.
     */
    fun getQueryParamValue(url: String, paramName: String): String? {
        val queryString = URL(url).query
        val params = queryString?.split("&") ?: emptyList()

        for (param in params) {
            val pair = param.split("=")
            if (pair.size == 2 && pair[0] == paramName) {
                return URLDecoder.decode(pair[1], "UTF-8")
            }
        }
        return null
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

    /**
     * Gets the default locale of the system.
     *
     * @return The default locale of the system.
     */
    fun getDefaultLocale(): Locale {
        return Locale.getDefault()
    }

    /**
     * Sets the default locale for the application based on the provided language code.
     *
     * This function checks if the provided language code is valid by comparing it to the list
     * of available locales. If a valid match is found, the default locale is set to the specified language.
     * If the provided language code is not valid, an [IllegalArgumentException] is thrown.
     *
     * @param languageCode The language code to set as the default locale.
     * @throws IllegalArgumentException if the provided language code is not valid.
     */
    fun setDefaultLocale(languageCode: String) {
        val availableLocales = Locale.getAvailableLocales()
        // Check if the provided language code is valid
        if (availableLocales.any { it.language == languageCode }) {
            val locale = Locale(languageCode)
            Locale.setDefault(locale)
        } else {
            // Handle invalid language code (you can choose to ignore or throw an exception)
            throw IllegalArgumentException("Invalid language code: $languageCode")
        }
    }
}
