package com.raana.starwars.data.species.model

import com.google.gson.annotations.SerializedName
import com.raana.starwars.domain.species.result.SpeciesDetail

data class SpeciesDetailNetwork(
    val name: String,
    @SerializedName("homeworld")
    val homeWorld: String?,
    val language: String,
) {
    fun toDomainModel(): SpeciesDetail =
        SpeciesDetail(name, homeWorld, language)
}