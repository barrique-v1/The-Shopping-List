package com.example.theshoppinglist.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.theshoppinglist.data.local.entity.IngredientEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface IngredientDao {

    @Query("SELECT * FROM ingredients WHERE recipeId = :recipeId ORDER BY category, name")
    fun getIngredientsByRecipeId(recipeId: Long): Flow<List<IngredientEntity>>

    @Query("SELECT * FROM ingredients WHERE id = :id")
    fun getIngredientById(id: Long): Flow<IngredientEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIngredient(ingredient: IngredientEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIngredients(ingredients: List<IngredientEntity>)

    @Update
    suspend fun updateIngredient(ingredient: IngredientEntity)

    @Delete
    suspend fun deleteIngredient(ingredient: IngredientEntity)

    @Query("DELETE FROM ingredients WHERE id = :id")
    suspend fun deleteIngredientById(id: Long)
}
