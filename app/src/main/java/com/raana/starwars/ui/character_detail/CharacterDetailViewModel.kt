package com.raana.starwars.ui.character_detail

import androidx.lifecycle.viewModelScope
import com.raana.starwars.base.BaseViewModel
import com.raana.starwars.base.ModelWrapper
import com.raana.starwars.domain.character.usecase.GetCharacterDetailUseCase
import com.raana.starwars.domain.film.usecase.GetFilmDetailUseCase
import com.raana.starwars.domain.planet.usecase.GetPlanetDetailUseCase
import com.raana.starwars.domain.species.usecase.GetSpeciesDetailUseCase
import com.raana.starwars.mapper.toCharacterDetailView
import com.raana.starwars.mapper.toFilmView
import com.raana.starwars.mapper.toPlanetView
import com.raana.starwars.mapper.toSpeciesView
import com.raana.starwars.model.CharacterDetailView
import com.raana.starwars.model.FilmView
import com.raana.starwars.model.PlanetView
import com.raana.starwars.model.SpeciesView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Character detail view model
 *
 * @param getCharacterDetailUseCase
 * @param getPlanetDetailUseCase
 * @param getFilmDetailUseCase
 * @param getSpeciesDetailUseCase
 */
@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val getCharacterDetailUseCase: GetCharacterDetailUseCase,
    private val getPlanetDetailUseCase: GetPlanetDetailUseCase,
    private val getFilmDetailUseCase: GetFilmDetailUseCase,
    private val getSpeciesDetailUseCase: GetSpeciesDetailUseCase,
) : BaseViewModel<CharacterDetailViewModel.State>(State()) {

    /**
     * Get character using getCharacterDetailUseCase with url and fold function
     *
     */
    fun getCharacter(url: String) {
        viewModelScope.launch {
            updateState { copy(character = ModelWrapper.Loading()) }
            getCharacterDetailUseCase.invoke(url).fold(
                onSuccess = { character ->
                    updateState { copy(character = ModelWrapper.Success(character.toCharacterDetailView())) }
                    getPlanet(character.homeWorldUrl)
                    getSpecies(character.species)
                    getFilms(character.films)
                },
                onFailure = {
                    updateState { copy(character = ModelWrapper.Failure(throwable = it)) }
                })
        }
    }

    /**
     * Get Planet using getPlanetDetailUseCase with url and fold function
     *
     */
    fun getPlanet(url: String) {
        viewModelScope.launch {
            updateState { copy(planet = ModelWrapper.Loading()) }
            getPlanetDetailUseCase.invoke(url).fold(
                onSuccess = { planet ->
                    updateState { copy(planet = ModelWrapper.Success(planet.toPlanetView())) }
                },
                onFailure = {
                    updateState { copy(planet = ModelWrapper.Failure(throwable = it)) }
                })
        }
    }

    /**
     * Get Films using getFilmDetailUseCase with url and fold function
     *
     */
    fun getFilms(urls: List<String>) {
        viewModelScope.launch {
            updateState { copy(films = ModelWrapper.Loading()) }
            getFilmDetailUseCase.invoke(urls).fold(
                onSuccess = { films ->
                    updateState {
                        copy(films = ModelWrapper.Success(
                            films.map { it.toFilmView() }
                        ))
                    }
                },
                onFailure = {
                    updateState { copy(films = ModelWrapper.Failure(throwable = it)) }
                })
        }
    }
    /**
     * Get Species  using getSpeciesDetailUseCase with url and fold function
     *
     */
    fun getSpecies(urls: List<String>) {
        viewModelScope.launch {
            updateState { copy(species = ModelWrapper.Loading()) }
            getSpeciesDetailUseCase.invoke(urls).fold(
                onSuccess = { species ->
                    updateState {
                        copy(species = ModelWrapper.Success(
                            species.map { it.toSpeciesView() }
                        ))
                    }
                },
                onFailure = {
                    updateState { copy(species = ModelWrapper.Failure(throwable = it)) }
                })
        }
    }

    // UI state of CharacterDetailScreen
    data class State(
        val character: ModelWrapper<CharacterDetailView> = ModelWrapper.None(),
        val planet: ModelWrapper<PlanetView> = ModelWrapper.None(),
        val films: ModelWrapper<List<FilmView>> = ModelWrapper.None(),
        val species: ModelWrapper<List<SpeciesView>> = ModelWrapper.None(),
    )
}