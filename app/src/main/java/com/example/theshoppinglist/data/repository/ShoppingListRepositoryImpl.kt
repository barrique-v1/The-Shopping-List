package com.example.theshoppinglist.data.repository

import com.example.theshoppinglist.data.local.dao.ShoppingListDao
import com.example.theshoppinglist.data.local.entity.ShoppingListEntity
import com.example.theshoppinglist.data.local.entity.toDomainModel
import com.example.theshoppinglist.data.local.entity.toEntity
import com.example.theshoppinglist.domain.common.Result
import com.example.theshoppinglist.domain.model.ShoppingList
import com.example.theshoppinglist.domain.repository.ShoppingListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Implementation of ShoppingListRepository.
 * Handles data operations for shopping lists with proper error handling.
 */
class ShoppingListRepositoryImpl @Inject constructor(
    private val shoppingListDao: ShoppingListDao
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
}
