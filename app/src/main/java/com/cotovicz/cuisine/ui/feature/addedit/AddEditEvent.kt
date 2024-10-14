package com.cotovicz.cuisine.ui.feature.addedit

interface AddEditEvent {
    data class TitleChanged(val value: String): AddEditEvent
    data class DescriptionChanged(val value: String): AddEditEvent
    data class PhotoChanged(val value: String): AddEditEvent
//    data class IngredientsChanged(val value: List<String>): AddEditEvent
    data class PreparationChanged(val value: String): AddEditEvent
    object Save: AddEditEvent
}