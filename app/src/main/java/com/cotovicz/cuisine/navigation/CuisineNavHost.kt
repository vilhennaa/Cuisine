package com.cotovicz.cuisine.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.cotovicz.cuisine.ui.feature.addedit.AddEditScreen
import com.cotovicz.cuisine.ui.feature.recipes.RecipesListScreen
import kotlinx.serialization.Serializable

@Serializable
object ListRoute

@Serializable
data class AddEditRoute(val id: Long? = null)

@Composable
fun CuisineNavHost() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = ListRoute
    ) {
        composable<ListRoute> {
            RecipesListScreen(
                navigateToAddEditScreen = { id ->
                    navController.navigate(AddEditRoute(id = id))
                }
            )
        }

        composable<AddEditRoute> { navBackStackEntry ->
            val addEditRoute = navBackStackEntry.toRoute<AddEditRoute>()
            AddEditScreen(
                id = addEditRoute.id,
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}