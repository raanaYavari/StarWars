package com.raana.starwars.domain.species

import com.raana.starwars.domain.species.result.SpeciesDetail

interface SpeciesRepository {
    /**
     * Get SpeciesDetail content from remote Data Source
     *
     * @return List of SpeciesDetail objects
     */
    suspend fun getSpeciesDetail(urls: List<String>): Result<List<SpeciesDetail>>
}