package com.example.theshoppinglist.domain.common

/**
 * A generic wrapper class that represents the result of an operation.
 * Used for explicit error handling throughout the app.
 *
 * @param T The type of data being wrapped
 */
sealed class Result<out T> {

    /**
     * Represents a successful operation with data
     */
    data class Success<T>(val data: T) : Result<T>()

    /**
     * Represents a failed operation with an error
     */
    data class Error(val exception: Throwable, val message: String? = exception.message) : Result<Nothing>()

    /**
     * Represents an operation in progress
     */
    data object Loading : Result<Nothing>()
}

/**
 * Extension function to check if the result is successful
 */
fun <T> Result<T>.isSuccess(): Boolean = this is Result.Success

/**
 * Extension function to check if the result is an error
 */
fun <T> Result<T>.isError(): Boolean = this is Result.Error

/**
 * Extension function to get data if result is successful, null otherwise
 */
fun <T> Result<T>.getOrNull(): T? = when (this) {
    is Result.Success -> data
    else -> null
}

/**
 * Extension function to get data if successful, or throw the exception
 */
fun <T> Result<T>.getOrThrow(): T = when (this) {
    is Result.Success -> data
    is Result.Error -> throw exception
    is Result.Loading -> throw IllegalStateException("Result is still loading")
}
