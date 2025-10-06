package com.example.theshoppinglist.domain.usecase.shoppinglist

import com.example.theshoppinglist.domain.common.Result
import com.example.theshoppinglist.domain.model.Category
import com.example.theshoppinglist.domain.model.ListItem
import com.example.theshoppinglist.domain.model.Unit
import com.example.theshoppinglist.domain.repository.ShoppingListRepository
import javax.inject.Inject

/**
 * Use case for updating an existing list item.
 * Handles validation and business logic before updating.
 */
class UpdateListItemUseCase @Inject constructor(
    private val repository: ShoppingListRepository
) {
    /**
     * Executes the use case to update a list item.
     * @param id The ID of the item to update
     * @param shoppingListId The ID of the shopping list
     * @param name The updated name (must not be blank)
     * @param quantity The updated quantity (must be positive)
     * @param unit The updated unit of measurement
     * @param category The updated category
     * @param isChecked The checked state
     * @param recipeId Optional recipe ID
     * @return Result indicating success or failure
     */
    suspend operator fun invoke(
        id: Long,
        shoppingListId: Long,
        name: String,
        quantity: Double,
        unit: Unit,
        category: Category,
        isChecked: Boolean = false,
        recipeId: Long? = null
    ): Result<kotlin.Unit> {
        // Validate input
        if (name.isBlank()) {
            return Result.Error(IllegalArgumentException("Item name cannot be blank"))
        }

        if (quantity <= 0) {
            return Result.Error(IllegalArgumentException("Quantity must be positive"))
        }

        // Create updated list item
        val listItem = ListItem(
            id = id,
            shoppingListId = shoppingListId,
            name = name.trim(),
            quantity = quantity,
            unit = unit,
            category = category,
            isChecked = isChecked,
            recipeId = recipeId
        )

        return repository.updateListItem(listItem)
    }
}
