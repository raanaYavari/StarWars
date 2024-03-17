package com.raana.starwars.domain.planet

import com.raana.starwars.domain.planet.result.PlanetDetail

interface PlanetRepository {
    /**
     * Get PlanetDetail content from remote Data Source
     *
     * @return PlanetDetail objects
     */
    suspend fun getPlanetDetail(url:String): Result<PlanetDetail>
}