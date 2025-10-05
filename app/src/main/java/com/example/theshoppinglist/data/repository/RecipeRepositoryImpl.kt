package com.example.theshoppinglist.data.repository

import com.example.theshoppinglist.data.local.dao.RecipeDao
import com.example.theshoppinglist.data.local.entity.RecipeEntity
import com.example.theshoppinglist.data.local.entity.toDomainModel
import com.example.theshoppinglist.data.local.entity.toEntity
import com.example.theshoppinglist.domain.common.Result
import com.example.theshoppinglist.domain.model.Recipe
import com.example.theshoppinglist.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Implementation of RecipeRepository.
 * Handles data operations for recipes with proper error handling.
 */
class RecipeRepositoryImpl @Inject constructor(
    private val recipeDao: RecipeDao
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
}
