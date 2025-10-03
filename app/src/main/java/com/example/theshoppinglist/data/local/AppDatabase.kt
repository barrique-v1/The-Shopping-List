package com.example.theshoppinglist.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.theshoppinglist.data.local.converter.Converters
import com.example.theshoppinglist.data.local.dao.IngredientDao
import com.example.theshoppinglist.data.local.dao.ListItemDao
import com.example.theshoppinglist.data.local.dao.RecipeDao
import com.example.theshoppinglist.data.local.dao.ShoppingListDao
import com.example.theshoppinglist.data.local.entity.IngredientEntity
import com.example.theshoppinglist.data.local.entity.ListItemEntity
import com.example.theshoppinglist.data.local.entity.RecipeEntity
import com.example.theshoppinglist.data.local.entity.ShoppingListEntity

@Database(
    entities = [
        ShoppingListEntity::class,
        ListItemEntity::class,
        RecipeEntity::class,
        IngredientEntity::class
    ],
    version = 1,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun shoppingListDao(): ShoppingListDao
    abstract fun listItemDao(): ListItemDao
    abstract fun recipeDao(): RecipeDao
    abstract fun ingredientDao(): IngredientDao

    companion object {
        const val DATABASE_NAME = "shopping_list_database"
    }
}
