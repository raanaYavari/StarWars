package com.raana.starwars.domain.film.result

import com.raana.starwars.domain.character.result.CharacterDetail

/**
 * FilmDetail object
 *
 * @property FilmDetail
 * @constructor Create empty Film object
 */
data class FilmDetail(
    val title: String,
    val openingCrawl: String,
)