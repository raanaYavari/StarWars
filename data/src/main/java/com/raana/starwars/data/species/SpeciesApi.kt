package com.raana.starwars.data.species

import com.raana.starwars.data.planet.model.PlanetDetailNetwork
import com.raana.starwars.data.species.model.SpeciesDetailNetwork
import retrofit2.http.GET
import retrofit2.http.Url

interface SpeciesApi {
    /**
     * Get Species content by given url from server
     *
     * @return Contents as SpeciesDetailNetwork Objects
     */
    @GET
    suspend fun getSpecies(@Url url: String): SpeciesDetailNetwork
}