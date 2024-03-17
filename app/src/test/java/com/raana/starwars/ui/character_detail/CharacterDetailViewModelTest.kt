package com.raana.starwars.ui.character_detail

import com.raana.starwars.base.ModelWrapper
import com.raana.starwars.domain.character.usecase.GetCharacterDetailUseCase
import com.raana.starwars.domain.character.usecase.GetCharacterListUseCase
import com.raana.starwars.domain.film.usecase.GetFilmDetailUseCase
import com.raana.starwars.domain.planet.usecase.GetPlanetDetailUseCase
import com.raana.starwars.domain.species.usecase.GetSpeciesDetailUseCase
import com.raana.starwars.mapper.toCharacterView
import com.raana.starwars.rule.MainDispatcherRule
import com.raana.starwars.ui.mock.MockDatabase
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class CharacterDetailViewModelTest {
    private lateinit var characterDetailViewModel: CharacterDetailViewModel
    private lateinit var mockGetCharacterDetailUseCase: GetCharacterDetailUseCase
    private lateinit var mockGetSpeciesDetailUseCase: GetSpeciesDetailUseCase
    private lateinit var mockGetPlanetDetailUseCase: GetPlanetDetailUseCase
    private lateinit var mockGetFilmDetailUseCase: GetFilmDetailUseCase

    @get: Rule
    val mainDispatcherRule = MainDispatcherRule()


    @Before
    fun setup() {
        mockGetCharacterDetailUseCase = mockk(relaxed = true)
        mockGetSpeciesDetailUseCase = mockk(relaxed = true)
        mockGetFilmDetailUseCase = mockk(relaxed = true)
        mockGetPlanetDetailUseCase = mockk(relaxed = true)
        characterDetailViewModel =
            CharacterDetailViewModel(
                mockGetCharacterDetailUseCase,
                mockGetPlanetDetailUseCase,
                mockGetFilmDetailUseCase,
                mockGetSpeciesDetailUseCase
            )
    }

    @Test
    fun getCharacter_isSuccess_updatesCharacterStateWithModelWrapperSuccess() {
        val url = MockDatabase.provideMockCharacterDetail().url
        coEvery {
            mockGetCharacterDetailUseCase.invoke(url)
        } returns Result.success(
            MockDatabase.provideMockCharacterDetail()
        )
        characterDetailViewModel.getCharacter(url)
        val response = characterDetailViewModel.currentState.character

        Assert.assertTrue(response is ModelWrapper.Success)
        Assert.assertNotNull((response as ModelWrapper.Success).result)
    }
    @Test
    fun getPlanet_isSuccess_updatesPlanetStateWithModelWrapperSuccess() {
        val url = MockDatabase.provideMockCharacterDetail().homeWorldUrl
        coEvery {
            mockGetPlanetDetailUseCase.invoke(url)
        } returns Result.success(
            MockDatabase.provideMockPlanetDetail()
        )
        characterDetailViewModel.getPlanet(url)
        val response = characterDetailViewModel.currentState.planet

        Assert.assertTrue(response is ModelWrapper.Success)
        Assert.assertNotNull((response as ModelWrapper.Success).result)
    }
    @Test
    fun getFilms_isSuccess_updatesFilmsStateWithModelWrapperSuccess() {
        val urls = MockDatabase.provideMockCharacterDetail().films
        coEvery {
            mockGetFilmDetailUseCase.invoke(urls)
        } returns Result.success(
           arrayListOf(MockDatabase.provideMockFilmDetail())
        )
        characterDetailViewModel.getFilms(urls)
        val response = characterDetailViewModel.currentState.films

        Assert.assertTrue(response is ModelWrapper.Success)
        Assert.assertNotNull((response as ModelWrapper.Success).result)
    }
    @Test
    fun getSpecies_isSuccess_updatesSpeciesStateWithModelWrapperSuccess() {
        val urls = MockDatabase.provideMockCharacterDetail().species
        coEvery {
            mockGetSpeciesDetailUseCase.invoke(urls)
        } returns Result.success(
           arrayListOf(MockDatabase.provideMockSpeciesDetail())
        )
        characterDetailViewModel.getSpecies(urls)
        val response = characterDetailViewModel.currentState.species

        Assert.assertTrue(response is ModelWrapper.Success)
        Assert.assertNotNull((response as ModelWrapper.Success).result)
    }

}