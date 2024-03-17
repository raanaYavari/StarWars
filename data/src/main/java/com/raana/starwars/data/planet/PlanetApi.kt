package com.raana.starwars.data.planet

import com.raana.starwars.data.planet.model.PlanetDetailNetwork
import retrofit2.http.GET
import retrofit2.http.Url

interface PlanetApi {
    /**
     * Get Planet by given url content from server
     *
     * @return Contents as PlanetDetailNetwork Objects
     */
    @GET
    suspend fun getPlanet(@Url url: String): PlanetDetailNetwork
}