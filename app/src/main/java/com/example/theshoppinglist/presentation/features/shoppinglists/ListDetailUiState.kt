package com.example.theshoppinglist.presentation.features.shoppinglists

import com.example.theshoppinglist.domain.model.ListItem
import com.example.theshoppinglist.domain.model.ShoppingList

/**
 * UI state for the list detail screen.
 * Represents all data needed to render a single shopping list with its items.
 *
 * @param shoppingList The shopping list details (null if not loaded yet)
 * @param items The list of items in this shopping list
 */
data class ListDetailUiState(
    val shoppingList: ShoppingList? = null,
    val items: List<ListItem> = emptyList()
)
