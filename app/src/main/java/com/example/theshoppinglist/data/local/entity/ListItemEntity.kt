package com.example.theshoppinglist.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.theshoppinglist.domain.model.Category
import com.example.theshoppinglist.domain.model.ListItem
import com.example.theshoppinglist.domain.model.Unit

@Entity(
    tableName = "list_items",
    foreignKeys = [
        ForeignKey(
            entity = ShoppingListEntity::class,
            parentColumns = ["id"],
            childColumns = ["shoppingListId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = RecipeEntity::class,
            parentColumns = ["id"],
            childColumns = ["recipeId"],
            onDelete = ForeignKey.SET_NULL
        )
    ],
    indices = [
        Index("shoppingListId"),
        Index("recipeId")
    ]
)
data class ListItemEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val shoppingListId: Long,
    val name: String,
    val quantity: Double,
    val unit: Unit,
    val category: Category,
    val isChecked: Boolean = false,
    val recipeId: Long? = null
)

fun ListItemEntity.toDomainModel(): ListItem {
    return ListItem(
        id = id,
        shoppingListId = shoppingListId,
        name = name,
        quantity = quantity,
        unit = unit,
        category = category,
        isChecked = isChecked,
        recipeId = recipeId
    )
}

fun ListItem.toEntity(): ListItemEntity {
    return ListItemEntity(
        id = id,
        shoppingListId = shoppingListId,
        name = name,
        quantity = quantity,
        unit = unit,
        category = category,
        isChecked = isChecked,
        recipeId = recipeId
    )
}
