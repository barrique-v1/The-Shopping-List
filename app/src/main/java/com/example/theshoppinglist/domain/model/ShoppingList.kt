package com.example.theshoppinglist.domain.model

import java.time.LocalDateTime

data class ShoppingList(
    val id: Long = 0,
    val name: String,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now()
)
