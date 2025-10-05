package com.example.theshoppinglist.presentation.common

/**
 * Sealed class representing one-time UI events that should be consumed only once.
 * These are different from UI state - they represent actions that should happen once
 * (e.g., navigation, showing a snackbar/toast) rather than persistent state.
 *
 * ViewModels should emit these events through a Channel or SharedFlow, not StateFlow,
 * to ensure they're only consumed once and not re-triggered on configuration changes.
 */
sealed class UiEvent {

    /**
     * Show a snackbar with a message
     * @param message The message to display
     * @param actionLabel Optional action button label
     */
    data class ShowSnackbar(
        val message: String,
        val actionLabel: String? = null
    ) : UiEvent()

    /**
     * Show a toast with a message
     * @param message The message to display
     */
    data class ShowToast(val message: String) : UiEvent()

    /**
     * Navigate to a specific destination
     * @param route The navigation route
     */
    data class Navigate(val route: String) : UiEvent()

    /**
     * Navigate back to the previous screen
     */
    data object NavigateBack : UiEvent()

    /**
     * Navigate up in the navigation hierarchy
     */
    data object NavigateUp : UiEvent()

    /**
     * Pop the back stack up to a specific destination
     * @param route The route to pop up to
     * @param inclusive Whether to also pop the destination route
     */
    data class PopUpTo(
        val route: String,
        val inclusive: Boolean = false
    ) : UiEvent()
}
