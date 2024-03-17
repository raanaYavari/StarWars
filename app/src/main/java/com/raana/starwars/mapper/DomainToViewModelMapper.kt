package com.raana.starwars.mapper

import com.raana.starwars.domain.character.result.CharacterDetail
import com.raana.starwars.domain.film.result.FilmDetail
import com.raana.starwars.domain.planet.result.PlanetDetail
import com.raana.starwars.domain.species.result.SpeciesDetail
import com.raana.starwars.model.CharacterDetailView
import com.raana.starwars.model.CharacterView
import com.raana.starwars.model.FilmView
import com.raana.starwars.model.PlanetView
import com.raana.starwars.model.SpeciesView
import com.raana.starwars.utils.cmToFeetConverter
import com.raana.starwars.utils.populationConverter

fun CharacterDetail.toCharacterView() = CharacterView(
    name = name,
    filmCount = films.size,
    birthYear = birthYear,
    url = url,
)

fun CharacterDetail.toCharacterDetailView() = CharacterDetailView(
    name = name,
    heightInCm = height,
    heightInFeet = cmToFeetConverter(height),
    birthYear = birthYear,
    films = films,
    species = species,
    homeWorldUrl = homeWorldUrl,
    url = url,
)

fun PlanetDetail.toPlanetView(): PlanetView {
    return PlanetView(
        name = name,
        population = populationConverter(population)
    )
}

fun SpeciesDetail.toSpeciesView(): SpeciesView {
    return SpeciesView(
        name = name,
        language = language,
        homeWorld = homeWorld
    )
}

fun FilmDetail.toFilmView(): FilmView {
    return FilmView(
        title = title,
        openingCrawl = openingCrawl
    )
}