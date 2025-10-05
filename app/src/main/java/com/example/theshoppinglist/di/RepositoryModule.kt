package com.example.theshoppinglist.di

import com.example.theshoppinglist.data.repository.RecipeRepositoryImpl
import com.example.theshoppinglist.data.repository.ShoppingListRepositoryImpl
import com.example.theshoppinglist.domain.repository.RecipeRepository
import com.example.theshoppinglist.domain.repository.ShoppingListRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt module for providing repository implementations.
 * Binds repository interfaces to their concrete implementations.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    /**
     * Binds ShoppingListRepositoryImpl to ShoppingListRepository interface
     */
    @Binds
    @Singleton
    abstract fun bindShoppingListRepository(
        shoppingListRepositoryImpl: ShoppingListRepositoryImpl
    ): ShoppingListRepository

    /**
     * Binds RecipeRepositoryImpl to RecipeRepository interface
     */
    @Binds
    @Singleton
    abstract fun bindRecipeRepository(
        recipeRepositoryImpl: RecipeRepositoryImpl
    ): RecipeRepository
}
