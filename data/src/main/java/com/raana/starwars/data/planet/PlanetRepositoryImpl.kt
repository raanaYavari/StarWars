package com.raana.starwars.data.planet

import com.raana.starwars.data.base.BaseRepositoryImpl
import com.raana.starwars.domain.planet.PlanetRepository
import com.raana.starwars.domain.planet.result.PlanetDetail
import javax.inject.Inject

/**
 * Planet repository impl
 *
 * @property planetApi
 * @constructor Create repository repository impl
 */
class PlanetRepositoryImpl @Inject constructor(
    private val planetApi: PlanetApi
) : PlanetRepository, BaseRepositoryImpl() {
    override suspend fun getPlanetDetail(url: String): Result<PlanetDetail> {
        return getResult {
            planetApi.getPlanet(url).toDomainModel()
        }
    }
}