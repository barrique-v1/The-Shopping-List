package com.example.theshoppinglist.presentation.features.shoppinglists

import com.example.theshoppinglist.domain.model.ShoppingList

/**
 * UI state for the shopping lists screen.
 * Represents all data needed to render the lists overview.
 *
 * @param lists The list of shopping lists to display
 */
data class ListsUiState(
    val lists: List<ShoppingList> = emptyList()
)
