package com.example.theshoppinglist.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.theshoppinglist.data.local.entity.ListItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ListItemDao {

    @Query("SELECT * FROM list_items WHERE shoppingListId = :shoppingListId ORDER BY category, name")
    fun getListItemsByShoppingListId(shoppingListId: Long): Flow<List<ListItemEntity>>

    @Query("SELECT * FROM list_items WHERE id = :id")
    fun getListItemById(id: Long): Flow<ListItemEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertListItem(listItem: ListItemEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertListItems(listItems: List<ListItemEntity>)

    @Update
    suspend fun updateListItem(listItem: ListItemEntity)

    @Delete
    suspend fun deleteListItem(listItem: ListItemEntity)

    @Query("DELETE FROM list_items WHERE id = :id")
    suspend fun deleteListItemById(id: Long)

    @Query("UPDATE list_items SET isChecked = :isChecked WHERE id = :id")
    suspend fun updateListItemCheckedState(id: Long, isChecked: Boolean)
}
