package com.example.theshoppinglist.domain.repository

import com.example.theshoppinglist.domain.common.Result
import com.example.theshoppinglist.domain.model.ListItem
import com.example.theshoppinglist.domain.model.ShoppingList
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for shopping list operations.
 * Defines the contract for data access abstraction.
 * Also manages ListItem operations as they are part of the ShoppingList aggregate.
 */
interface ShoppingListRepository {

    /**
     * Get all shopping lists as a reactive stream
     * @return Flow emitting Result with list of shopping lists
     */
    fun getAllShoppingLists(): Flow<Result<List<ShoppingList>>>

    /**
     * Get a specific shopping list by ID as a reactive stream
     * @param id The ID of the shopping list
     * @return Flow emitting Result with the shopping list or null if not found
     */
    fun getShoppingListById(id: Long): Flow<Result<ShoppingList?>>

    /**
     * Create a new shopping list
     * @param shoppingList The shopping list to create
     * @return Result with the ID of the created shopping list
     */
    suspend fun createShoppingList(shoppingList: ShoppingList): Result<Long>

    /**
     * Update an existing shopping list
     * @param shoppingList The shopping list to update
     * @return Result indicating success or failure
     */
    suspend fun updateShoppingList(shoppingList: ShoppingList): Result<Unit>

    /**
     * Delete a shopping list
     * @param shoppingList The shopping list to delete
     * @return Result indicating success or failure
     */
    suspend fun deleteShoppingList(shoppingList: ShoppingList): Result<Unit>

    /**
     * Delete a shopping list by ID
     * @param id The ID of the shopping list to delete
     * @return Result indicating success or failure
     */
    suspend fun deleteShoppingListById(id: Long): Result<Unit>

    // ============ ListItem Operations ============

    /**
     * Get all items for a specific shopping list as a reactive stream
     * @param shoppingListId The ID of the shopping list
     * @return Flow emitting Result with list of items
     */
    fun getListItems(shoppingListId: Long): Flow<Result<List<ListItem>>>

    /**
     * Get a specific list item by ID as a reactive stream
     * @param id The ID of the list item
     * @return Flow emitting Result with the list item or null if not found
     */
    fun getListItemById(id: Long): Flow<Result<ListItem?>>

    /**
     * Add a new item to a shopping list
     * @param listItem The list item to add
     * @return Result with the ID of the created item
     */
    suspend fun addListItem(listItem: ListItem): Result<Long>

    /**
     * Add multiple items to a shopping list
     * @param listItems The list items to add
     * @return Result indicating success or failure
     */
    suspend fun addListItems(listItems: List<ListItem>): Result<Unit>

    /**
     * Update an existing list item
     * @param listItem The list item to update
     * @return Result indicating success or failure
     */
    suspend fun updateListItem(listItem: ListItem): Result<Unit>

    /**
     * Delete a list item
     * @param listItem The list item to delete
     * @return Result indicating success or failure
     */
    suspend fun deleteListItem(listItem: ListItem): Result<Unit>

    /**
     * Delete a list item by ID
     * @param id The ID of the list item to delete
     * @return Result indicating success or failure
     */
    suspend fun deleteListItemById(id: Long): Result<Unit>

    /**
     * Toggle the checked state of a list item
     * @param id The ID of the list item
     * @param isChecked The new checked state
     * @return Result indicating success or failure
     */
    suspend fun toggleListItemChecked(id: Long, isChecked: Boolean): Result<Unit>
}
