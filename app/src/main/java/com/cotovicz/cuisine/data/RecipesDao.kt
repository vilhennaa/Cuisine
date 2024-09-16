package com.cotovicz.cuisine.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: RecipesEntity)

    @Delete
    suspend fun delete(entity: RecipesEntity)

    @Query("SELECT * FROM recipes")
    fun getAll(): Flow<List<RecipesEntity>>

    @Query("SELECT * FROM recipes WHERE id = :id")
    suspend fun getById(id: Long): RecipesEntity?
}