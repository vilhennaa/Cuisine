package com.cotovicz.cuisine.ui.feature.addedit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cotovicz.cuisine.data.RecipesRepository
import com.cotovicz.cuisine.ui.UiEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class AddEditViewModel(
    private val repository: RecipesRepository,
): ViewModel() {

    var title by mutableStateOf("")
        private set

    var description by mutableStateOf<String?>("")
        private set

    var photo by mutableStateOf<String?>("")
        private set

//    var ingredients by mutableStateOf(emptyList<String>())
//        private set

    var preparation by mutableStateOf<String?>("")
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: AddEditEvent) {
        when(event) {
            is AddEditEvent.TitleChanged -> { title = event.value }
            is AddEditEvent.DescriptionChanged -> { description = event.value }
            is AddEditEvent.PhotoChanged -> { photo = event.value }
//            is AddEditEvent.IngredientsChanged -> { ingredients = event.value }
            is AddEditEvent.PreparationChanged -> { preparation = event.value }
            is AddEditEvent.Save -> { saveRecipe() }
        }
    }

    private fun saveRecipe() {
        viewModelScope.launch {
            if(title.isBlank()) {
                _uiEvent.send(UiEvent.ShowSnackbar(message = "O título não pode estar em branco"))
                return@launch
            }

            repository.insert(
                title = title,
                description = description,
                photo = photo,
                //ingredients = ingredients,
                preparation = preparation
            )
            _uiEvent.send(UiEvent.NavigateBack)
        }
    }

}