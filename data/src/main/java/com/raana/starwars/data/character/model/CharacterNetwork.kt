package com.raana.starwars.data.character.model

import kotlinx.serialization.Serializable
@Serializable
data class CharacterSearchResponse(
    val results: List<CharacterDetailNetwork>,
)

