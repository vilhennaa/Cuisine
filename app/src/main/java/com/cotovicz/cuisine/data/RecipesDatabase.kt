package com.cotovicz.cuisine.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [RecipesEntity::class],
    version = 1,
)
abstract class RecipesDatabase: RoomDatabase() {

    abstract val recipesDao: RecipesDao
}

object RecipesDatabaseProvider {

    @Volatile
    private var INSTANCE: RecipesDatabase? = null

    fun provide(context: Context): RecipesDatabase {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                RecipesDatabase::class.java,
                "cuisine-app"
            ).build()
            INSTANCE = instance
            instance
        }
    }
}