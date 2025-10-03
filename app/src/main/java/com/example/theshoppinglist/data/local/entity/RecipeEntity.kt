package com.example.theshoppinglist.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.theshoppinglist.domain.model.Recipe
import java.time.LocalDateTime

@Entity(tableName = "recipes")
data class RecipeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val instructions: String? = null,
    val servings: Int = 1,
    val imageUrl: String? = null,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)

fun RecipeEntity.toDomainModel(): Recipe {
    return Recipe(
        id = id,
        name = name,
        instructions = instructions,
        servings = servings,
        imageUrl = imageUrl,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}

fun Recipe.toEntity(): RecipeEntity {
    return RecipeEntity(
        id = id,
        name = name,
        instructions = instructions,
        servings = servings,
        imageUrl = imageUrl,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}
