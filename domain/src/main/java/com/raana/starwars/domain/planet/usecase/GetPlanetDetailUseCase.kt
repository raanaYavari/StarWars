package com.raana.starwars.domain.planet.usecase

import com.raana.starwars.domain.base.BaseUseCase
import com.raana.starwars.domain.planet.PlanetRepository
import com.raana.starwars.domain.planet.result.PlanetDetail
import javax.inject.Inject
/**
 * Get PlanetDetail use case
 *
 * @property repository PlanetRepository to connect to Data Layer and get response from Data Sources
 */
class GetPlanetDetailUseCase @Inject constructor(private val repository: PlanetRepository) :
    BaseUseCase<String, Result<PlanetDetail>>() {
    override suspend fun invoke(param: String): Result<PlanetDetail> =
        repository.getPlanetDetail(param)

}