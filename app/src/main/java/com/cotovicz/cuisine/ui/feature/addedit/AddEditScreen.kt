package com.cotovicz.cuisine.ui.feature.addedit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cotovicz.cuisine.data.RecipesDatabaseProvider
import com.cotovicz.cuisine.data.RecipesRepositoryImp
import com.cotovicz.cuisine.ui.UiEvent
import com.cotovicz.cuisine.ui.theme.CuisineTheme

@Composable
fun AddEditScreen(
    navigateBack: () -> Unit,
) {
    val context = LocalContext.current.applicationContext
    val database = RecipesDatabaseProvider.provide(context)
    val repository = RecipesRepositoryImp(dao = database.recipesDao)

    val viewModel = viewModel<AddEditViewModel> {
        AddEditViewModel(repository = repository)
    }

    val title = viewModel.title
    val description = viewModel.description
    val photo = viewModel.photo
//    val ingredients = viewModel.ingredients
    val preparation = viewModel.preparation

    val snackbarHostState = remember {
        SnackbarHostState()
    }

    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect { uiEvent ->
            when(uiEvent) {
                is UiEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(message = uiEvent.message,)
                }
                UiEvent.NavigateBack -> {
                    navigateBack()
                }
                is UiEvent.Navigate<*> -> {
                }

            }
        }
    }



    AddEditContent(
        title = title,
        description = description,
        photo = photo,
//        ingredients = ingredients,
        preparation = preparation,
        snackbarHostState = snackbarHostState,
        onEvent = viewModel::onEvent
    )
}

@Composable
fun AddEditContent(
    title: String,
    description: String?,
    photo: String?,
//    ingredients: List<String> = emptyList(),
    preparation: String?,
    snackbarHostState: SnackbarHostState,
    onEvent: (AddEditEvent) -> Unit,
) {
    Scaffold (
        floatingActionButton = {
            FloatingActionButton(onClick = {
                onEvent(AddEditEvent.Save)
            }) {
                Icon(Icons.Default.Check, contentDescription = "Save")
            }
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .consumeWindowInsets(paddingValues)
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = title,
                onValueChange = {
                    onEvent(AddEditEvent.TitleChanged(it))
                },
                label = {
                    Text("Title")
                },
                modifier = Modifier
                    .fillMaxWidth(),
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = description?:"",
                onValueChange = {
                    onEvent(AddEditEvent.DescriptionChanged(it))
                },
                placeholder = {
                    Text("Description (optional)")
                },
                modifier = Modifier
                    .fillMaxWidth(),
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = preparation?:"",
                onValueChange = {
                    onEvent(AddEditEvent.PreparationChanged(it))
                },
                placeholder = {
                    Text("Instructions")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
            )
        }
    }
}

@Preview
@Composable
private fun AddEditContentPreview() {
    CuisineTheme {
        AddEditContent(
            title = "",
            description = "",
            photo = "",
//            ingredients = emptyList(),
            preparation = "",
            snackbarHostState = SnackbarHostState(),
            onEvent = {},
        )
    }
}