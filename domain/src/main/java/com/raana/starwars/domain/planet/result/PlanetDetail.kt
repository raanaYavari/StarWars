package com.raana.starwars.domain.planet.result
/**
 * PlanetDetail object
 *
 * @property PlanetDetail
 * @constructor Create empty Planet object
 */
data class PlanetDetail(
    val name: String,
    val homeWorldUrl: String?,
    val population: String,
)