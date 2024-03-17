package com.raana.starwars.model

data class CharacterDetailView(
    val name: String,
    val heightInCm: String,
    val heightInFeet : String,
    val birthYear: String,
    val url: String,
    val films: List<String>,
    val homeWorldUrl: String,
    val species: List<String>,
)