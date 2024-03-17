package com.raana.starwars.domain.film

import com.raana.starwars.domain.character.CharacterRepository
import com.raana.starwars.domain.character.result.CharacterDetail
import com.raana.starwars.domain.character.usecase.GetCharacterListUseCase
import com.raana.starwars.domain.film.result.FilmDetail
import com.raana.starwars.domain.film.usecase.GetFilmDetailUseCase
import com.raana.starwars.domain.mock.MockDatabase
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GetFilmDetailUseCaseTest {
    private lateinit var mockFilmRepository: FilmRepository
    private lateinit var getFilmDetailUseCase: GetFilmDetailUseCase

    @Before
    fun setUp() {
        mockFilmRepository = mockk(relaxed = true)
        getFilmDetailUseCase = GetFilmDetailUseCase(mockFilmRepository)
    }

    @Test
    fun getFilmListUseCaseShouldReturnFilms() {
        val films = listOf(
            MockDatabase.provideMockFilmDetail()
        )
        var response: List<FilmDetail>?
        coEvery {
            getFilmDetailUseCase.invoke(listOf())
        } returns Result.success(films)
            .also { response = it.getOrNull() }

        Assert.assertNotNull(response)
    }
}