package com.raana.starwars.data.species

import com.raana.starwars.data.base.BaseRepositoryImpl
import com.raana.starwars.domain.species.SpeciesRepository
import com.raana.starwars.domain.species.result.SpeciesDetail
import javax.inject.Inject

/**
 * Species repository impl
 *
 * @property speciesApi
 * @constructor Create repository repository impl
 */
class SpeciesRepositoryImpl @Inject constructor(
    private val speciesApi: SpeciesApi
) : SpeciesRepository, BaseRepositoryImpl() {
    override suspend fun getSpeciesDetail(urls: List<String>): Result<List<SpeciesDetail>> {
        val species = mutableListOf<SpeciesDetail>()
        return getResult {
            urls.forEach {
                species.add(speciesApi.getSpecies(it).toDomainModel())
            }
            species
        }
    }
}