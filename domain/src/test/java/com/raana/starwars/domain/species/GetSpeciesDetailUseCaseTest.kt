package com.raana.starwars.domain.species

import com.raana.starwars.domain.mock.MockDatabase
import com.raana.starwars.domain.planet.PlanetRepository
import com.raana.starwars.domain.planet.result.PlanetDetail
import com.raana.starwars.domain.planet.usecase.GetPlanetDetailUseCase
import com.raana.starwars.domain.species.result.SpeciesDetail
import com.raana.starwars.domain.species.usecase.GetSpeciesDetailUseCase
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GetSpeciesDetailUseCaseTest {
    private lateinit var mockSpeciesRepository: SpeciesRepository
    private lateinit var getSpeciesDetailUseCase: GetSpeciesDetailUseCase

    @Before
    fun setUp() {
        mockSpeciesRepository = mockk(relaxed = true)
        getSpeciesDetailUseCase = GetSpeciesDetailUseCase(mockSpeciesRepository)
    }

    @Test
    fun getPlanetUseCaseShouldReturnPlanet() {
        val species = listOf(MockDatabase.provideMockSpeciesDetail())
        var response: List<SpeciesDetail>?
        coEvery {
            getSpeciesDetailUseCase.invoke(listOf())
        } returns Result.success(species)
            .also { response = it.getOrNull() }

        Assert.assertNotNull(response)
    }

}