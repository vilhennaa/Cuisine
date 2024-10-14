package com.cotovicz.cuisine.ui.feature.recipes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cotovicz.cuisine.data.RecipesRepository
import com.cotovicz.cuisine.navigation.AddEditRoute
import com.cotovicz.cuisine.ui.UiEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class RecipesListViewModel(
    private val repository: RecipesRepository
): ViewModel()  {
    val recipes = repository.getAll()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: RecipesListEvent) {
        when(event) {
            is RecipesListEvent.DeleteRecipe -> {
                deleteRecipe(event.id)
            }
            is RecipesListEvent.AddEdit -> {
                viewModelScope.launch {
                    _uiEvent.send(UiEvent.Navigate(AddEditRoute(event.id)))
                }
            }
        }
    }

    private fun deleteRecipe(id: Long) {
        viewModelScope.launch {
            repository.delete(id)
        }
    }

}