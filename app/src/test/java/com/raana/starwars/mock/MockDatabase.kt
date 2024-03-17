package com.raana.starwars.ui.mock

import com.raana.starwars.domain.character.result.CharacterDetail
import com.raana.starwars.domain.film.result.FilmDetail
import com.raana.starwars.domain.planet.result.PlanetDetail
import com.raana.starwars.domain.species.result.SpeciesDetail

object MockDatabase {


    object height {
        val cm = "180"
        val feet = "5.91"
    }

    object population {
        val unknown = "unknown"
        val populationUnformated = "1000000"
        val populationFormatted = "1.000,000"
    }
    fun provideMockCharacterDetail(): CharacterDetail {
        return CharacterDetail(
            url = "https://swapi.dev/api/people/1/",
            name = "Luke Skywalker",
            height = "172",
            birthYear = "19BBY",
            species = arrayListOf(
                "https://swapi.dev/api/species/1/"
            ),
            films = arrayListOf(
                "https://swapi.dev/api/films/1/",
                "https://swapi.dev/api/films/2/",
                "https://swapi.dev/api/films/3/",
                "https://swapi.dev/api/films/6/"
            ),
            homeWorldUrl = "https://swapi.dev/api/planets/1/"
        )
    }

    fun provideMockPlanetDetail(): PlanetDetail {
        return PlanetDetail(
            name = "Droid",
            homeWorldUrl = "https://swapi.dev/api/planets/9/",
            population = "123"
        )
    }

    fun provideMockFilmDetail(): FilmDetail {
        return FilmDetail(
            title = "A New Hope",
            openingCrawl = "It is a period of civil war.\r\nRebel spaceships, striking\r\nfrom a hidden base, have won\r\ntheir first victory against\r\nthe evil Galactic Empire.\r\n\r\nDuring the battle, Rebel\r\nspies managed to steal secret\r\nplans to the Empire's\r\nultimate weapon, the DEATH\r\nSTAR, an armored space\r\nstation with enough power\r\nto destroy an entire planet.\r\n\r\nPursued by the Empire's\r\nsinister agents, Princess\r\nLeia races home aboard her\r\nstarship, custodian of the\r\nstolen plans that can save her\r\npeople and restore\r\nfreedom to the galaxy...."
        )
    }

    fun provideMockSpeciesDetail(): SpeciesDetail {
        return SpeciesDetail(
            name = "Droid",
            language = "n/a",
            homeWorld = null
        )
    }
}