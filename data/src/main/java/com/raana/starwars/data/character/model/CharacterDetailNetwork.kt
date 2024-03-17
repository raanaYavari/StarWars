package com.raana.starwars.data.character.model

import com.google.gson.annotations.SerializedName
import com.raana.starwars.domain.character.result.CharacterDetail
import kotlinx.serialization.Serializable

@Serializable
data class CharacterDetailNetwork(
    val url: String,
    val name: String,
    @SerializedName("birth_year")
    val birthYear: String,
    val height: String,
    @SerializedName("homeworld")
    val homeWorld: String,
    val films: List<String>,
    val species: List<String>,

    ) {
    fun toDomainModel() = CharacterDetail(
        url, name, birthYear, height, homeWorld, films, species
    )
}