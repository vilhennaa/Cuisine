package com.cotovicz.cuisine.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipes")
data class RecipesEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val description: String?,
    val ingredients: List<String>,
    val preparation: String?,
    val photo: String?,
)
