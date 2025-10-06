package com.example.theshoppinglist.domain.usecase.shoppinglist

import com.example.theshoppinglist.domain.common.Result
import com.example.theshoppinglist.domain.repository.ShoppingListRepository
import javax.inject.Inject

/**
 * Use case for toggling the checked state of a list item.
 * This is the most common user interaction - checking/unchecking items while shopping.
 */
class ToggleListItemCheckedUseCase @Inject constructor(
    private val repository: ShoppingListRepository
) {
    /**
     * Executes the use case to toggle a list item's checked state.
     * @param id The ID of the list item to toggle
     * @param isChecked The new checked state (true = checked, false = unchecked)
     * @return Result indicating success or failure
     */
    suspend operator fun invoke(id: Long, isChecked: Boolean): Result<Unit> {
        return repository.toggleListItemChecked(id, isChecked)
    }
}
