package com.example.theshoppinglist.domain.usecase.shoppinglist

import com.example.theshoppinglist.domain.common.Result
import com.example.theshoppinglist.domain.repository.ShoppingListRepository
import javax.inject.Inject

/**
 * Use case for deleting a shopping list by ID.
 * Note: This will also delete all associated list items due to CASCADE delete in the database.
 */
class DeleteShoppingListUseCase @Inject constructor(
    private val repository: ShoppingListRepository
) {
    /**
     * Executes the use case to delete a shopping list.
     * @param id The ID of the shopping list to delete
     * @return Result indicating success or failure
     */
    suspend operator fun invoke(id: Long): Result<Unit> {
        return repository.deleteShoppingListById(id)
    }
}
