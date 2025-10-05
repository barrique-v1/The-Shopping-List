package com.example.theshoppinglist.presentation.common

/**
 * A generic sealed class representing the UI state for ViewModels.
 * Separates data loading states from the actual data.
 *
 * @param T The type of data being represented
 */
sealed class UiState<out T> {

    /**
     * Initial state before any action is taken
     */
    data object Idle : UiState<Nothing>()

    /**
     * Loading state while data is being fetched
     */
    data object Loading : UiState<Nothing>()

    /**
     * Success state with data
     * @param data The successfully loaded data
     */
    data class Success<T>(val data: T) : UiState<T>()

    /**
     * Error state when something goes wrong
     * @param message User-friendly error message
     * @param throwable Optional throwable for debugging
     */
    data class Error(
        val message: String,
        val throwable: Throwable? = null
    ) : UiState<Nothing>()
}

/**
 * Extension function to check if state is loading
 */
fun <T> UiState<T>.isLoading(): Boolean = this is UiState.Loading

/**
 * Extension function to check if state is success
 */
fun <T> UiState<T>.isSuccess(): Boolean = this is UiState.Success

/**
 * Extension function to check if state is error
 */
fun <T> UiState<T>.isError(): Boolean = this is UiState.Error

/**
 * Extension function to get data if state is success, null otherwise
 */
fun <T> UiState<T>.dataOrNull(): T? = when (this) {
    is UiState.Success -> data
    else -> null
}

/**
 * Extension function to get error message if state is error, null otherwise
 */
fun <T> UiState<T>.errorOrNull(): String? = when (this) {
    is UiState.Error -> message
    else -> null
}
