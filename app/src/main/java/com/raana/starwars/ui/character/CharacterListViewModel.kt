package com.raana.starwars.ui.character

import androidx.lifecycle.viewModelScope
import com.raana.starwars.base.BaseViewModel
import com.raana.starwars.base.ModelWrapper
import com.raana.starwars.domain.character.result.CharacterDetail
import com.raana.starwars.domain.character.usecase.GetCharacterListUseCase
import com.raana.starwars.mapper.toCharacterView
import com.raana.starwars.model.CharacterView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Character list view model
 *
 * @property getCharacterListUseCase use case for getting list of Characters
 */
@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val getCharacterListUseCase: GetCharacterListUseCase
) : BaseViewModel<CharacterListViewModel.State>(State()) {

    init {
        getCharacterList(currentState.searchText)
    }

    /**
     * Get character list using getCharacterListUseCase with searchText and fold function
     *
     */
    fun getCharacterList(searchText: String) {
        updateState { copy(searchText = searchText) }
        viewModelScope.launch {
            updateState { copy(characters = ModelWrapper.Loading()) }
            getCharacterListUseCase.invoke(currentState.searchText).fold(
                onSuccess = { characterList ->
                    updateState {
                        copy(characters = ModelWrapper.Success(
                            characterList.map { it.toCharacterView() }
                        ))
                    }
                },
                onFailure = {
                    updateState { copy(characters = ModelWrapper.Failure(throwable = it)) }
                })
        }
    }
    /**
     * refresh character list using getCharacterListUseCase with searchText and fold function
     *
     */
    fun refreshCharacterList() {
        getCharacterList(currentState.searchText)
    }

    // UI state of CharacterListScreen
    data class State(
        val characters: ModelWrapper<List<CharacterView>> = ModelWrapper.None(),
        var searchText: String = "",
    )
}