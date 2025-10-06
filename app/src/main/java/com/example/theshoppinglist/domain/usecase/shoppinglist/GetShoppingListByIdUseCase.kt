package com.example.theshoppinglist.domain.usecase.shoppinglist

import com.example.theshoppinglist.domain.common.Result
import com.example.theshoppinglist.domain.model.ShoppingList
import com.example.theshoppinglist.domain.repository.ShoppingListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Use case for fetching a specific shopping list by ID.
 * Returns a reactive stream that updates automatically when the list changes.
 * Useful for the list detail screen.
 */
class GetShoppingListByIdUseCase @Inject constructor(
    private val repository: ShoppingListRepository
) {
    /**
     * Executes the use case to get a shopping list by ID.
     * @param id The ID of the shopping list to fetch
     * @return Flow emitting Result with the shopping list or null if not found
     */
    operator fun invoke(id: Long): Flow<Result<ShoppingList?>> {
        return repository.getShoppingListById(id)
    }
}
