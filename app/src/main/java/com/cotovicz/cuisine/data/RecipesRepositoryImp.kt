package com.cotovicz.cuisine.data

import com.cotovicz.cuisine.domain.Recipes
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RecipesRepositoryImp(
    private val dao: RecipesDao
) : RecipesRepository {

    override suspend fun insert(
        title: String,
        description: String?,
        photo: String?,
        ingredients: List<String>,
        preparation: String?
    ) {
        val entity = RecipesEntity(
            title = title,
            description = description,
            photo = photo,
            ingredients = ingredients,
            preparation = preparation
        )

        dao.insert(entity)
    }

    override suspend fun delete(id: Long) {
        val existingEntity = dao.getById(id) ?: return
        dao.delete(existingEntity)
    }

    override fun getAll(): Flow<List<Recipes>> {
        return dao.getAll().map { entities ->
            entities.map { entity ->
                Recipes(
                    id = entity.id,
                    title = entity.title,
                    description = entity.description,
                    photo = entity.photo,
                    ingredients = entity.ingredients,
                    preparation = entity.preparation
                )
            }
        }
    }

    override suspend fun getById(id: Long): Recipes? {
        return dao.getById(id)?.let { entity ->
            Recipes(
                id = entity.id,
                title = entity.title,
                description = entity.description,
                photo = entity.photo,
                ingredients = entity.ingredients,
                preparation = entity.preparation
            )
        }
    }
}