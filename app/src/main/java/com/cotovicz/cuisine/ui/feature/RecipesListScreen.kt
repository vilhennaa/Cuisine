package com.cotovicz.cuisine.ui.feature

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cotovicz.cuisine.domain.Recipes
import com.cotovicz.cuisine.domain.recipe1
import com.cotovicz.cuisine.domain.recipe2
import com.cotovicz.cuisine.domain.recipe3
import com.cotovicz.cuisine.ui.components.RecipesItem
import com.cotovicz.cuisine.ui.theme.CuisineTheme

@Composable
fun RecipesListScreen(
    navigateToAddEditScreen: (Long?) -> Unit,
) {
    RecipesListContent(
        recipes = emptyList(),
        onAddItemClick = navigateToAddEditScreen,
    )
}

@Composable
fun RecipesListContent(
    recipes: List<Recipes>,
    onAddItemClick: (id: Long?) -> Unit,
) {
    Scaffold(
       floatingActionButton = {
           FloatingActionButton(onClick = { onAddItemClick(null) }) {
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
                    onItemClick = {  },
                    onDeleteClick = {  }
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
            onAddItemClick = {},
        )
    }
}