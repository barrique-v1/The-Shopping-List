package com.example.theshoppinglist.domain.usecase.shoppinglist

import com.example.theshoppinglist.domain.common.Result
import com.example.theshoppinglist.domain.model.Category
import com.example.theshoppinglist.domain.model.ListItem
import com.example.theshoppinglist.domain.model.Unit
import com.example.theshoppinglist.domain.repository.ShoppingListRepository
import javax.inject.Inject

/**
 * Use case for adding a new item to a shopping list.
 * Handles validation and business logic before adding the item.
 */
class AddListItemUseCase @Inject constructor(
    private val repository: ShoppingListRepository
) {
    /**
     * Executes the use case to add a new list item.
     * @param shoppingListId The ID of the shopping list to add the item to
     * @param name The name of the item (must not be blank)
     * @param quantity The quantity of the item (must be positive)
     * @param unit The unit of measurement
     * @param category The category of the item
     * @param recipeId Optional recipe ID if the item came from a recipe
     * @return Result with the ID of the created item, or an error if validation fails
     */
    suspend operator fun invoke(
        shoppingListId: Long,
        name: String,
        quantity: Double,
        unit: Unit,
        category: Category,
        recipeId: Long? = null
    ): Result<Long> {
        // Validate input
        if (name.isBlank()) {
            return Result.Error(IllegalArgumentException("Item name cannot be blank"))
        }

        if (quantity <= 0) {
            return Result.Error(IllegalArgumentException("Quantity must be positive"))
        }

        // Create the list item
        val listItem = ListItem(
            shoppingListId = shoppingListId,
            name = name.trim(),
            quantity = quantity,
            unit = unit,
            category = category,
            isChecked = false,
            recipeId = recipeId
        )

        return repository.addListItem(listItem)
    }
}
