package com.cotovicz.cuisine.ui.feature.recipes

sealed interface RecipesListEvent {
    data class DeleteRecipe(val id: Long): RecipesListEvent
    data class AddEdit(val id: Long?) : RecipesListEvent
}