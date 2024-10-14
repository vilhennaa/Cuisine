package com.cotovicz.cuisine.data

import android.icu.text.CaseMap.Title
import com.cotovicz.cuisine.domain.Recipes
import kotlinx.coroutines.flow.Flow

interface RecipesRepository {

    suspend fun insert(
        title: String,
        description: String?,
        photo: String?,
       // ingredients: List<String>,
        preparation: String?,
        id: Long? = null
    )

    suspend fun delete(id: Long)

    fun getAll(): Flow<List<Recipes>>

    suspend fun getById(id: Long): Recipes?
}