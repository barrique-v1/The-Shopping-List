package com.example.theshoppinglist.data.repository

import com.example.theshoppinglist.data.local.dao.IngredientDao
import com.example.theshoppinglist.data.local.dao.RecipeDao
import com.example.theshoppinglist.data.local.entity.IngredientEntity
import com.example.theshoppinglist.data.local.entity.RecipeEntity
import com.example.theshoppinglist.data.local.entity.toDomainModel
import com.example.theshoppinglist.data.local.entity.toEntity
import com.example.theshoppinglist.domain.common.Result
import com.example.theshoppinglist.domain.model.Ingredient
import com.example.theshoppinglist.domain.model.Recipe
import com.example.theshoppinglist.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Implementation of RecipeRepository.
 * Handles data operations for recipes and their ingredients with proper error handling.
 */
class RecipeRepositoryImpl @Inject constructor(
    private val recipeDao: RecipeDao,
    private val ingredientDao: IngredientDao
) : RecipeRepository {

    override fun getAllRecipes(): Flow<Result<List<Recipe>>> {
        return recipeDao.getAllRecipes()
            .map<List<RecipeEntity>, Result<List<Recipe>>> { entities ->
                Result.Success(entities.map { it.toDomainModel() })
            }
            .catch { exception ->
                emit(Result.Error(exception, "Failed to fetch recipes"))
            }
    }

    override fun getRecipeById(id: Long): Flow<Result<Recipe?>> {
        return recipeDao.getRecipeById(id)
            .map<RecipeEntity?, Result<Recipe?>> { entity ->
                Result.Success(entity?.toDomainModel())
            }
            .catch { exception ->
                emit(Result.Error(exception, "Failed to fetch recipe with ID: $id"))
            }
    }

    override suspend fun createRecipe(recipe: Recipe): Result<Long> {
        return try {
            val id = recipeDao.insertRecipe(recipe.toEntity())
            Result.Success(id)
        } catch (exception: Exception) {
            Result.Error(exception, "Failed to create recipe")
        }
    }

    override suspend fun updateRecipe(recipe: Recipe): Result<Unit> {
        return try {
            recipeDao.updateRecipe(recipe.toEntity())
            Result.Success(Unit)
        } catch (exception: Exception) {
            Result.Error(exception, "Failed to update recipe")
        }
    }

    override suspend fun deleteRecipe(recipe: Recipe): Result<Unit> {
        return try {
            recipeDao.deleteRecipe(recipe.toEntity())
            Result.Success(Unit)
        } catch (exception: Exception) {
            Result.Error(exception, "Failed to delete recipe")
        }
    }

    override suspend fun deleteRecipeById(id: Long): Result<Unit> {
        return try {
            recipeDao.deleteRecipeById(id)
            Result.Success(Unit)
        } catch (exception: Exception) {
            Result.Error(exception, "Failed to delete recipe with ID: $id")
        }
    }

    // ============ Ingredient Operations ============

    override fun getIngredients(recipeId: Long): Flow<Result<List<Ingredient>>> {
        return ingredientDao.getIngredientsByRecipeId(recipeId)
            .map<List<IngredientEntity>, Result<List<Ingredient>>> { entities ->
                Result.Success(entities.map { it.toDomainModel() })
            }
            .catch { exception ->
                emit(Result.Error(exception, "Failed to fetch ingredients for recipe ID: $recipeId"))
            }
    }

    override fun getIngredientById(id: Long): Flow<Result<Ingredient?>> {
        return ingredientDao.getIngredientById(id)
            .map<IngredientEntity?, Result<Ingredient?>> { entity ->
                Result.Success(entity?.toDomainModel())
            }
            .catch { exception ->
                emit(Result.Error(exception, "Failed to fetch ingredient with ID: $id"))
            }
    }

    override suspend fun addIngredient(ingredient: Ingredient): Result<Long> {
        return try {
            val id = ingredientDao.insertIngredient(ingredient.toEntity())
            Result.Success(id)
        } catch (exception: Exception) {
            Result.Error(exception, "Failed to add ingredient")
        }
    }

    override suspend fun addIngredients(ingredients: List<Ingredient>): Result<Unit> {
        return try {
            ingredientDao.insertIngredients(ingredients.map { it.toEntity() })
            Result.Success(Unit)
        } catch (exception: Exception) {
            Result.Error(exception, "Failed to add ingredients")
        }
    }

    override suspend fun updateIngredient(ingredient: Ingredient): Result<Unit> {
        return try {
            ingredientDao.updateIngredient(ingredient.toEntity())
            Result.Success(Unit)
        } catch (exception: Exception) {
            Result.Error(exception, "Failed to update ingredient")
        }
    }

    override suspend fun deleteIngredient(ingredient: Ingredient): Result<Unit> {
        return try {
            ingredientDao.deleteIngredient(ingredient.toEntity())
            Result.Success(Unit)
        } catch (exception: Exception) {
            Result.Error(exception, "Failed to delete ingredient")
        }
    }

    override suspend fun deleteIngredientById(id: Long): Result<Unit> {
        return try {
            ingredientDao.deleteIngredientById(id)
            Result.Success(Unit)
        } catch (exception: Exception) {
            Result.Error(exception, "Failed to delete ingredient with ID: $id")
        }
    }
}
