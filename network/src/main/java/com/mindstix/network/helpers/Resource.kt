package com.mindstix.network.helpers

import com.mindstix.network.data.GenericErrorModel


/**
 * A generic class that contains data and status about loading this data Success,Loading and Error.
 */
data class Resource<out T>(val status: Status, val data: T?, val error: GenericErrorModel?) {

    companion object {
        // Handles success
        fun <T> success(data: T): Resource<T> = Resource(
            status = Status.SUCCESS,
            data = data,
            error = null,
        )

        // Handles Loading
        fun <T> loading(data: T? = null): Resource<T> = Resource(
            status = Status.LOADING,
            data = data,
            error = null,
        )

        // Handles Error
        fun <T> error(data: T? = null, error: GenericErrorModel? = null): Resource<T> = Resource(
            status = Status.ERROR,
            data = data,
            error = error,
        )

        fun <T> idle(data: T? = null) = Resource(status = Status.NONE, data = data, error = null)
    }

    /**
     * This probably has a purpose.
     */
    enum class Status {
        NONE,
        SUCCESS,
        ERROR,
        LOADING,
    }
}
