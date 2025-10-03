package com.example.theshoppinglist.di

import android.content.Context
import androidx.room.Room
import com.example.theshoppinglist.data.local.AppDatabase
import com.example.theshoppinglist.data.local.dao.IngredientDao
import com.example.theshoppinglist.data.local.dao.ListItemDao
import com.example.theshoppinglist.data.local.dao.RecipeDao
import com.example.theshoppinglist.data.local.dao.ShoppingListDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration(dropAllTables = true) // For development only
            .build()
    }

    @Provides
    @Singleton
    fun provideShoppingListDao(database: AppDatabase): ShoppingListDao {
        return database.shoppingListDao()
    }

    @Provides
    @Singleton
    fun provideListItemDao(database: AppDatabase): ListItemDao {
        return database.listItemDao()
    }

    @Provides
    @Singleton
    fun provideRecipeDao(database: AppDatabase): RecipeDao {
        return database.recipeDao()
    }

    @Provides
    @Singleton
    fun provideIngredientDao(database: AppDatabase): IngredientDao {
        return database.ingredientDao()
    }
}
