package com.example.theshoppinglist.domain.usecase.shoppinglist

import com.example.theshoppinglist.domain.common.Result
import com.example.theshoppinglist.domain.model.ShoppingList
import com.example.theshoppinglist.domain.repository.ShoppingListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Use case for fetching all shopping lists.
 * Returns a reactive stream of shopping lists that updates automatically when data changes.
 */
class GetShoppingListsUseCase @Inject constructor(
    private val repository: ShoppingListRepository
) {
    /**
     * Executes the use case to get all shopping lists.
     * @return Flow emitting Result with list of shopping lists
     */
    operator fun invoke(): Flow<Result<List<ShoppingList>>> {
        return repository.getAllShoppingLists()
    }
}
