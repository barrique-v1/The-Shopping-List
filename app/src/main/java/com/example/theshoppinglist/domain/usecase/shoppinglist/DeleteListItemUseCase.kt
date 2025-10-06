package com.example.theshoppinglist.domain.usecase.shoppinglist

import com.example.theshoppinglist.domain.common.Result
import com.example.theshoppinglist.domain.repository.ShoppingListRepository
import javax.inject.Inject

/**
 * Use case for deleting a list item by ID.
 * This is typically triggered by swipe-to-delete or a delete button in the UI.
 */
class DeleteListItemUseCase @Inject constructor(
    private val repository: ShoppingListRepository
) {
    /**
     * Executes the use case to delete a list item.
     * @param id The ID of the list item to delete
     * @return Result indicating success or failure
     */
    suspend operator fun invoke(id: Long): Result<Unit> {
        return repository.deleteListItemById(id)
    }
}
