package com.example.theshoppinglist.domain.model

import java.time.LocalDateTime

data class Recipe(
    val id: Long = 0,
    val name: String,
    val instructions: String? = null,
    val servings: Int = 1,
    val imageUrl: String? = null,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now()
)
