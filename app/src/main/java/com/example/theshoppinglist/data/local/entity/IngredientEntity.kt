package com.example.theshoppinglist.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.theshoppinglist.domain.model.Category
import com.example.theshoppinglist.domain.model.Ingredient
import com.example.theshoppinglist.domain.model.Unit

@Entity(
    tableName = "ingredients",
    foreignKeys = [
        ForeignKey(
            entity = RecipeEntity::class,
            parentColumns = ["id"],
            childColumns = ["recipeId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("recipeId")]
)
data class IngredientEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val recipeId: Long,
    val name: String,
    val quantity: Double,
    val unit: Unit,
    val category: Category
)

fun IngredientEntity.toDomainModel(): Ingredient {
    return Ingredient(
        id = id,
        recipeId = recipeId,
        name = name,
        quantity = quantity,
        unit = unit,
        category = category
    )
}

fun Ingredient.toEntity(): IngredientEntity {
    return IngredientEntity(
        id = id,
        recipeId = recipeId,
        name = name,
        quantity = quantity,
        unit = unit,
        category = category
    )
}
