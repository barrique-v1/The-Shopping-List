package com.example.theshoppinglist.domain.model

data class ListItem(
    val id: Long = 0,
    val shoppingListId: Long,
    val name: String,
    val quantity: Double,
    val unit: Unit,
    val category: Category,
    val isChecked: Boolean = false,
    val recipeId: Long? = null // Optional: track if item came from a recipe
)
