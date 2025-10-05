package com.example.theshoppinglist.domain.repository

import com.example.theshoppinglist.domain.common.Result
import com.example.theshoppinglist.domain.model.Ingredient
import com.example.theshoppinglist.domain.model.Recipe
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for recipe operations.
 * Defines the contract for data access abstraction.
 * Also manages Ingredient operations as they are part of the Recipe aggregate.
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

    // ============ Ingredient Operations ============

    /**
     * Get all ingredients for a specific recipe as a reactive stream
     * @param recipeId The ID of the recipe
     * @return Flow emitting Result with list of ingredients
     */
    fun getIngredients(recipeId: Long): Flow<Result<List<Ingredient>>>

    /**
     * Get a specific ingredient by ID as a reactive stream
     * @param id The ID of the ingredient
     * @return Flow emitting Result with the ingredient or null if not found
     */
    fun getIngredientById(id: Long): Flow<Result<Ingredient?>>

    /**
     * Add a new ingredient to a recipe
     * @param ingredient The ingredient to add
     * @return Result with the ID of the created ingredient
     */
    suspend fun addIngredient(ingredient: Ingredient): Result<Long>

    /**
     * Add multiple ingredients to a recipe
     * @param ingredients The ingredients to add
     * @return Result indicating success or failure
     */
    suspend fun addIngredients(ingredients: List<Ingredient>): Result<Unit>

    /**
     * Update an existing ingredient
     * @param ingredient The ingredient to update
     * @return Result indicating success or failure
     */
    suspend fun updateIngredient(ingredient: Ingredient): Result<Unit>

    /**
     * Delete an ingredient
     * @param ingredient The ingredient to delete
     * @return Result indicating success or failure
     */
    suspend fun deleteIngredient(ingredient: Ingredient): Result<Unit>

    /**
     * Delete an ingredient by ID
     * @param id The ID of the ingredient to delete
     * @return Result indicating success or failure
     */
    suspend fun deleteIngredientById(id: Long): Result<Unit>
}
