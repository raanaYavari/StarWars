package com.raana.starwars.domain.planet

import com.raana.starwars.domain.film.FilmRepository
import com.raana.starwars.domain.film.result.FilmDetail
import com.raana.starwars.domain.film.usecase.GetFilmDetailUseCase
import com.raana.starwars.domain.mock.MockDatabase
import com.raana.starwars.domain.planet.result.PlanetDetail
import com.raana.starwars.domain.planet.usecase.GetPlanetDetailUseCase
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GetPlanetDetailUseCaseTest {
    private lateinit var mockPlanetRepository: PlanetRepository
    private lateinit var getPlanetDetailUseCase: GetPlanetDetailUseCase

    @Before
    fun setUp() {
        mockPlanetRepository = mockk(relaxed = true)
        getPlanetDetailUseCase = GetPlanetDetailUseCase(mockPlanetRepository)
    }

    @Test
    fun getPlanetUseCaseShouldReturnPlanet() {
        val planet = MockDatabase.provideMockPlanetDetail()
        var response: PlanetDetail?
        coEvery {
            getPlanetDetailUseCase.invoke("")
        } returns Result.success(planet)
            .also { response = it.getOrNull() }

        Assert.assertNotNull(response)
    }
}