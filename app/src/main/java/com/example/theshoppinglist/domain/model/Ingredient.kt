package com.example.theshoppinglist.domain.model

data class Ingredient(
    val id: Long = 0,
    val recipeId: Long,
    val name: String,
    val quantity: Double,
    val unit: Unit,
    val category: Category
)
