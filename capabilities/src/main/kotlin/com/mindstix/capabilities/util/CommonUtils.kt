/**
 * Copyright (c) 2023 Mindstix Software Labs
 * All rights reserved.
 */
package com.mindstix.capabilities.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import java.net.URL
import java.net.URLDecoder
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.Locale

/**
 * Utility class providing common functions for various tasks.
 *
 * This class contains utility functions related to network connectivity, default values,
 * string manipulation, date handling, URL operations, and locale handling.
 *
 * @author Pranav Hadawale
 */
object CommonUtils {
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
    fun formatDateToString(
        date: LocalDate,
        pattern: String = "yyyy-MM-dd",
    ): String {
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
    fun parseStringToDate(
        dateString: String,
        pattern: String = "yyyy-MM-dd",
    ): LocalDate {
        val formatter = DateTimeFormatter.ofPattern(pattern)
        return try {
            LocalDate.parse(dateString, formatter)
        } catch (e: DateTimeParseException) {
            throw DateTimeParseException("Failed to parse date: $dateString", dateString, 0, e)
        }
    }

    /**
     * Given a base [url] and a map of [parameters], this function constructs a new URL string
     * with the appended query parameters.
     *
     * @param url The base URL to which query parameters will be appended.
     * @param parameters A map of key-value pairs representing the query parameters.
     * @return A new URL string with the appended query parameters.
     */
    fun appendQueryParameters(
        url: String,
        parameters: Map<String, String>,
    ): String =
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
    fun removeSpecifiedQueryParameter(
        url: String,
        key: String,
    ): Uri? {
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
    fun getQueryParamValue(
        url: String,
        paramName: String,
    ): String? {
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
