package com.cotovicz.cuisine.domain

data class Recipes(
    val id: Long,
    val title: String,
    val description: String? = null,
    val photo: String? = null, // String para o momento...
  //  val ingredients: List<String> = emptyList(),
    val preparation: String? = null
)
//fake objcs
val recipe1 = Recipes(
    id = 1,
    title = "Receita 1",
    description = "teste"
)

val recipe2 = Recipes(
    id = 2,
    title = "Receita 2",
    description = "teste"
)

val recipe3 = Recipes(
    id = 3,
    title = "Receita 3",
    description = "teste"
)