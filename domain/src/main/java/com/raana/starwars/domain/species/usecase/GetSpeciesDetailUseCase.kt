package com.raana.starwars.domain.species.usecase

import com.raana.starwars.domain.base.BaseUseCase
import com.raana.starwars.domain.planet.result.PlanetDetail
import com.raana.starwars.domain.species.SpeciesRepository
import com.raana.starwars.domain.species.result.SpeciesDetail
import javax.inject.Inject
/**
 * Get SpeciesDetail use case
 *
 * @property repository SpeciesRepository to connect to Data Layer and get response from Data Sources
 */
class GetSpeciesDetailUseCase @Inject constructor(private val repository: SpeciesRepository) :
    BaseUseCase<List<String>, Result<List<SpeciesDetail>>>() {
    override suspend fun invoke(param: List<String>): Result<List<SpeciesDetail>> =
        repository.getSpeciesDetail(param)
}