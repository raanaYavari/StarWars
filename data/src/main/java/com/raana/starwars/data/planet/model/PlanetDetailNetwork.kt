package com.raana.starwars.data.planet.model

import com.google.gson.annotations.SerializedName
import com.raana.starwars.domain.planet.result.PlanetDetail

data class PlanetDetailNetwork(
    val name: String,
    @SerializedName("homeworld")
    val homeWorld: String?,
    val population: String,
) {
    fun toDomainModel(): PlanetDetail =
        PlanetDetail(name, homeWorld, population)
}