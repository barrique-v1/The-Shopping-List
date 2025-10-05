package com.example.theshoppinglist.domain.repository

import com.example.theshoppinglist.domain.common.Result
import com.example.theshoppinglist.domain.model.Recipe
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for recipe operations.
 * Defines the contract for data access abstraction.
 */
interface RecipeRepository {

    /**
     * Get all recipes as a reactive stream
     * @return Flow emitting Result with list of recipes
     */
    fun getAllRecipes(): Flow<Result<List<Recipe>>>

    /**
     * Get a specific recipe by ID as a reactive stream
     * @param id The ID of the recipe
     * @return Flow emitting Result with the recipe or null if not found
     */
    fun getRecipeById(id: Long): Flow<Result<Recipe?>>

    /**
     * Create a new recipe
     * @param recipe The recipe to create
     * @return Result with the ID of the created recipe
     */
    suspend fun createRecipe(recipe: Recipe): Result<Long>

    /**
     * Update an existing recipe
     * @param recipe The recipe to update
     * @return Result indicating success or failure
     */
    suspend fun updateRecipe(recipe: Recipe): Result<Unit>

    /**
     * Delete a recipe
     * @param recipe The recipe to delete
     * @return Result indicating success or failure
     */
    suspend fun deleteRecipe(recipe: Recipe): Result<Unit>

    /**
     * Delete a recipe by ID
     * @param id The ID of the recipe to delete
     * @return Result indicating success or failure
     */
    suspend fun deleteRecipeById(id: Long): Result<Unit>
}
