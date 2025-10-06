package com.example.theshoppinglist.domain.usecase.shoppinglist

import com.example.theshoppinglist.domain.common.Result
import com.example.theshoppinglist.domain.model.ShoppingList
import com.example.theshoppinglist.domain.repository.ShoppingListRepository
import java.time.LocalDateTime
import javax.inject.Inject

/**
 * Use case for creating a new shopping list.
 * Handles business logic and validation before creating the list.
 */
class CreateShoppingListUseCase @Inject constructor(
    private val repository: ShoppingListRepository
) {
    /**
     * Executes the use case to create a new shopping list.
     * @param name The name of the shopping list (must not be blank)
     * @return Result with the ID of the created shopping list, or an error if validation fails
     */
    suspend operator fun invoke(name: String): Result<Long> {
        // Validate input
        if (name.isBlank()) {
            return Result.Error(IllegalArgumentException("Shopping list name cannot be blank"))
        }

        // Create the shopping list with current timestamp
        val shoppingList = ShoppingList(
            name = name.trim(),
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now()
        )

        return repository.createShoppingList(shoppingList)
    }
}
