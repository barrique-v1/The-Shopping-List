package com.example.theshoppinglist.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.theshoppinglist.domain.model.ShoppingList
import java.time.LocalDateTime

@Entity(tableName = "shopping_lists")
data class ShoppingListEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)

fun ShoppingListEntity.toDomainModel(): ShoppingList {
    return ShoppingList(
        id = id,
        name = name,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}

fun ShoppingList.toEntity(): ShoppingListEntity {
    return ShoppingListEntity(
        id = id,
        name = name,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}
