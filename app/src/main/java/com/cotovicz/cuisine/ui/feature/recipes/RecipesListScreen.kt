package com.cotovicz.cuisine.ui.feature.recipes

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cotovicz.cuisine.data.RecipesDatabaseProvider
import com.cotovicz.cuisine.data.RecipesRepositoryImp
import com.cotovicz.cuisine.domain.Recipes
import com.cotovicz.cuisine.domain.recipe1
import com.cotovicz.cuisine.domain.recipe2
import com.cotovicz.cuisine.domain.recipe3
import com.cotovicz.cuisine.navigation.AddEditRoute
import com.cotovicz.cuisine.ui.UiEvent
import com.cotovicz.cuisine.ui.components.RecipesItem
import com.cotovicz.cuisine.ui.theme.CuisineTheme

@Composable
fun RecipesListScreen(
    navigateToAddEditScreen: (Long?) -> Unit,
) {
    val context = LocalContext.current.applicationContext
    val database = RecipesDatabaseProvider.provide(context)
    val repository = RecipesRepositoryImp(dao = database.recipesDao)

    val viewModel = viewModel<RecipesListViewModel> { RecipesListViewModel(repository = repository) }

    val recipes by viewModel.recipes.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect { uiEvent ->
            when (uiEvent) {
                is UiEvent.Navigate<*> -> {
                    when(uiEvent.route) {
                        is AddEditRoute -> navigateToAddEditScreen(uiEvent.route.id)
                    }
                }
                UiEvent.NavigateBack -> {

                }
                is UiEvent.ShowSnackbar -> {

                }
            }
        }
    }

    RecipesListContent(
        recipes = recipes,
        onEvent = viewModel::onEvent
    )
}

@Composable
fun RecipesListContent(
    recipes: List<Recipes>,
    onEvent: (RecipesListEvent) -> Unit,
) {
    Scaffold(
       floatingActionButton = {
           FloatingActionButton(onClick = {
               onEvent(RecipesListEvent.AddEdit(null))
           }) {
               Icon(Icons.Default.Add, contentDescription = "Add")
           }
       }
    ) {
        paddingValues ->
        LazyColumn(
            modifier = Modifier
                .consumeWindowInsets(paddingValues),
            contentPadding = PaddingValues(16.dp)
        ) {
            itemsIndexed(recipes) { index, recipe ->
                RecipesItem(
                    recipes = recipe,
                    onItemClick = { onEvent(RecipesListEvent.AddEdit(recipe.id)) },
                    onDeleteClick = { onEvent(RecipesListEvent.DeleteRecipe(recipe.id)) }
                )

                if (index < recipes.lastIndex) {
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}

@Preview
@Composable
private fun RecipesListContentPreview() {
    CuisineTheme {
        RecipesListContent(
            recipes = listOf(
                recipe1,
                recipe2,
                recipe3,
            ),
            onEvent = {}
        )
    }
}