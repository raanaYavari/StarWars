package com.raana.starwars.domain.species.result


/**
 * SpeciesDetail object
 *
 * @property SpeciesDetail
 * @constructor Create empty Species object
 */
data class SpeciesDetail(
    val name: String,
    val homeWorld: String?,
    val language: String?,
)