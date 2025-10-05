package com.example.theshoppinglist.domain.repository

import com.example.theshoppinglist.domain.common.Result
import com.example.theshoppinglist.domain.model.ShoppingList
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for shopping list operations.
 * Defines the contract for data access abstraction.
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
}
