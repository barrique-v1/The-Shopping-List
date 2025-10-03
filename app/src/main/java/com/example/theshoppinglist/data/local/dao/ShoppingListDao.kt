package com.example.theshoppinglist.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.theshoppinglist.data.local.entity.ShoppingListEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ShoppingListDao {

    @Query("SELECT * FROM shopping_lists ORDER BY updatedAt DESC")
    fun getAllShoppingLists(): Flow<List<ShoppingListEntity>>

    @Query("SELECT * FROM shopping_lists WHERE id = :id")
    fun getShoppingListById(id: Long): Flow<ShoppingListEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShoppingList(shoppingList: ShoppingListEntity): Long

    @Update
    suspend fun updateShoppingList(shoppingList: ShoppingListEntity)

    @Delete
    suspend fun deleteShoppingList(shoppingList: ShoppingListEntity)

    @Query("DELETE FROM shopping_lists WHERE id = :id")
    suspend fun deleteShoppingListById(id: Long)
}
