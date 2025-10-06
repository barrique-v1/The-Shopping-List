package com.example.theshoppinglist.domain.usecase.shoppinglist

import com.example.theshoppinglist.domain.common.Result
import com.example.theshoppinglist.domain.model.ListItem
import com.example.theshoppinglist.domain.repository.ShoppingListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Use case for fetching all items for a specific shopping list.
 * Returns a reactive stream that updates automatically when items change.
 * This is used by the list detail screen to display all items.
 */
class GetListItemsUseCase @Inject constructor(
    private val repository: ShoppingListRepository
) {
    /**
     * Executes the use case to get all items for a shopping list.
     * @param shoppingListId The ID of the shopping list
     * @return Flow emitting Result with list of items
     */
    operator fun invoke(shoppingListId: Long): Flow<Result<List<ListItem>>> {
        return repository.getListItems(shoppingListId)
    }
}
