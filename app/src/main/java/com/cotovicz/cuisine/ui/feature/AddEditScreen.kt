package com.cotovicz.cuisine.ui.feature

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cotovicz.cuisine.ui.theme.CuisineTheme

@Composable
fun AddEditScreen() {
    AddEditContent()
}

@Composable
fun AddEditContent() {
    Scaffold (
        floatingActionButton = {
            FloatingActionButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Default.Check, contentDescription = "Save")
            }
        }
    ) {
        Column(
            modifier = Modifier
                .consumeWindowInsets(it)
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = {
                    Text("Title")
                },
                modifier = Modifier
                    .fillMaxWidth(),
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = "",
                onValueChange = {},
                placeholder = {
                    Text("Description (optional)")
                },
                modifier = Modifier
                    .fillMaxWidth(),
            )
        }
    }
}

@Preview
@Composable
private fun AddEditContentPreview() {
    CuisineTheme {
        AddEditContent()
    }
}