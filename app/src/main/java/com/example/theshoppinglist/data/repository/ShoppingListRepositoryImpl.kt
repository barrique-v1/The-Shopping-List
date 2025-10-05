package com.example.theshoppinglist.data.repository

import com.example.theshoppinglist.data.local.dao.ListItemDao
import com.example.theshoppinglist.data.local.dao.ShoppingListDao
import com.example.theshoppinglist.data.local.entity.ListItemEntity
import com.example.theshoppinglist.data.local.entity.ShoppingListEntity
import com.example.theshoppinglist.data.local.entity.toDomainModel
import com.example.theshoppinglist.data.local.entity.toEntity
import com.example.theshoppinglist.domain.common.Result
import com.example.theshoppinglist.domain.model.ListItem
import com.example.theshoppinglist.domain.model.ShoppingList
import com.example.theshoppinglist.domain.repository.ShoppingListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Implementation of ShoppingListRepository.
 * Handles data operations for shopping lists and their items with proper error handling.
 */
class ShoppingListRepositoryImpl @Inject constructor(
    private val shoppingListDao: ShoppingListDao,
    private val listItemDao: ListItemDao
) : ShoppingListRepository {

    override fun getAllShoppingLists(): Flow<Result<List<ShoppingList>>> {
        return shoppingListDao.getAllShoppingLists()
            .map<List<ShoppingListEntity>, Result<List<ShoppingList>>> { entities ->
                Result.Success(entities.map { it.toDomainModel() })
            }
            .catch { exception ->
                emit(Result.Error(exception, "Failed to fetch shopping lists"))
            }
    }

    override fun getShoppingListById(id: Long): Flow<Result<ShoppingList?>> {
        return shoppingListDao.getShoppingListById(id)
            .map<ShoppingListEntity?, Result<ShoppingList?>> { entity ->
                Result.Success(entity?.toDomainModel())
            }
            .catch { exception ->
                emit(Result.Error(exception, "Failed to fetch shopping list with ID: $id"))
            }
    }

    override suspend fun createShoppingList(shoppingList: ShoppingList): Result<Long> {
        return try {
            val id = shoppingListDao.insertShoppingList(shoppingList.toEntity())
            Result.Success(id)
        } catch (exception: Exception) {
            Result.Error(exception, "Failed to create shopping list")
        }
    }

    override suspend fun updateShoppingList(shoppingList: ShoppingList): Result<Unit> {
        return try {
            shoppingListDao.updateShoppingList(shoppingList.toEntity())
            Result.Success(Unit)
        } catch (exception: Exception) {
            Result.Error(exception, "Failed to update shopping list")
        }
    }

    override suspend fun deleteShoppingList(shoppingList: ShoppingList): Result<Unit> {
        return try {
            shoppingListDao.deleteShoppingList(shoppingList.toEntity())
            Result.Success(Unit)
        } catch (exception: Exception) {
            Result.Error(exception, "Failed to delete shopping list")
        }
    }

    override suspend fun deleteShoppingListById(id: Long): Result<Unit> {
        return try {
            shoppingListDao.deleteShoppingListById(id)
            Result.Success(Unit)
        } catch (exception: Exception) {
            Result.Error(exception, "Failed to delete shopping list with ID: $id")
        }
    }

    // ============ ListItem Operations ============

    override fun getListItems(shoppingListId: Long): Flow<Result<List<ListItem>>> {
        return listItemDao.getListItemsByShoppingListId(shoppingListId)
            .map<List<ListItemEntity>, Result<List<ListItem>>> { entities ->
                Result.Success(entities.map { it.toDomainModel() })
            }
            .catch { exception ->
                emit(Result.Error(exception, "Failed to fetch list items for shopping list ID: $shoppingListId"))
            }
    }

    override fun getListItemById(id: Long): Flow<Result<ListItem?>> {
        return listItemDao.getListItemById(id)
            .map<ListItemEntity?, Result<ListItem?>> { entity ->
                Result.Success(entity?.toDomainModel())
            }
            .catch { exception ->
                emit(Result.Error(exception, "Failed to fetch list item with ID: $id"))
            }
    }

    override suspend fun addListItem(listItem: ListItem): Result<Long> {
        return try {
            val id = listItemDao.insertListItem(listItem.toEntity())
            Result.Success(id)
        } catch (exception: Exception) {
            Result.Error(exception, "Failed to add list item")
        }
    }

    override suspend fun addListItems(listItems: List<ListItem>): Result<Unit> {
        return try {
            listItemDao.insertListItems(listItems.map { it.toEntity() })
            Result.Success(Unit)
        } catch (exception: Exception) {
            Result.Error(exception, "Failed to add list items")
        }
    }

    override suspend fun updateListItem(listItem: ListItem): Result<Unit> {
        return try {
            listItemDao.updateListItem(listItem.toEntity())
            Result.Success(Unit)
        } catch (exception: Exception) {
            Result.Error(exception, "Failed to update list item")
        }
    }

    override suspend fun deleteListItem(listItem: ListItem): Result<Unit> {
        return try {
            listItemDao.deleteListItem(listItem.toEntity())
            Result.Success(Unit)
        } catch (exception: Exception) {
            Result.Error(exception, "Failed to delete list item")
        }
    }

    override suspend fun deleteListItemById(id: Long): Result<Unit> {
        return try {
            listItemDao.deleteListItemById(id)
            Result.Success(Unit)
        } catch (exception: Exception) {
            Result.Error(exception, "Failed to delete list item with ID: $id")
        }
    }

    override suspend fun toggleListItemChecked(id: Long, isChecked: Boolean): Result<Unit> {
        return try {
            listItemDao.updateListItemCheckedState(id, isChecked)
            Result.Success(Unit)
        } catch (exception: Exception) {
            Result.Error(exception, "Failed to toggle list item checked state")
        }
    }
}
