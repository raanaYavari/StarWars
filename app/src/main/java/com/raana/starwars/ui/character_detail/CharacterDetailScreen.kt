package com.raana.starwars.ui.character_detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarHost
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.raana.starwars.R
import com.raana.starwars.base.ModelWrapper
import com.raana.starwars.base.state
import com.raana.starwars.ui.component.AppTopBar
import com.raana.starwars.ui.component.TopBarNavigationType
import com.raana.starwars.model.CharacterView
import com.raana.starwars.ui.character_detail.component.CharacterDetailItem
import com.raana.starwars.ui.character_detail.component.FilmList
import com.raana.starwars.ui.character_detail.component.PlanetDetailItem
import com.raana.starwars.ui.character_detail.component.SpeciesList
import com.raana.starwars.ui.theme.red

@Composable
fun CharacterDetailScreen(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    character: CharacterView,
    viewModel: CharacterDetailViewModel,
    navigateBack: () -> Unit
) {

    val state by viewModel.state()
    // error message to show when cannot fetch data
    val errorMessage = stringResource(id = R.string.error)

    //Load the Character Detail with given url
    LaunchedEffect(Unit) {
        viewModel.getCharacter(character.url)
    }

    // run suspend functions in the scope of a composable and handle states
    LaunchedEffect(state.character is ModelWrapper.Failure) {
        (state.character as? ModelWrapper.Failure)?.let { result ->
            // show error as an snack bar in case of error
            scaffoldState.snackbarHostState.showSnackbar(
                message = result.throwable.message ?: errorMessage,
            )
        }
    }
    LaunchedEffect(state.planet is ModelWrapper.Failure) {
        (state.planet as? ModelWrapper.Failure)?.let { result ->
            // show error as an snack bar in case of error
            scaffoldState.snackbarHostState.showSnackbar(
                message = result.throwable.message ?: errorMessage,
            )
        }
    }
    LaunchedEffect(state.species is ModelWrapper.Failure) {
        (state.species as? ModelWrapper.Failure)?.let { result ->
            // show error as an snack bar in case of error
            scaffoldState.snackbarHostState.showSnackbar(
                message = result.throwable.message ?: errorMessage,
            )
        }
    }
    LaunchedEffect(state.films is ModelWrapper.Failure) {
        (state.films as? ModelWrapper.Failure)?.let { result ->
            // show error as an snack bar in case of error
            scaffoldState.snackbarHostState.showSnackbar(
                message = result.throwable.message ?: errorMessage,
            )
        }
    }


    Scaffold(
        scaffoldState = scaffoldState,
        // top app bar content section, here showing sync with server and logout part
        topBar = {
            AppTopBar(
                navigationType = TopBarNavigationType.BACK,
                title = character.name,
                onNavigationClick = {
                    navigateBack()
                },
            )
        },
        // Customizing snack bar Host to for error messages
        snackbarHost = {
            // reuse default SnackHost to have default animation and timing handling
            SnackbarHost(it) { data ->
                // custom snack with the custom colors
                Snackbar(
                    backgroundColor = red,
                    contentColor = MaterialTheme.colors.onError,
                    snackbarData = data
                )
            }
        },
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .verticalScroll(rememberScrollState())
        ) {
            // show Linear Progress Indicator when content state is in Loading state
            if (state.character is ModelWrapper.Loading ||
                state.planet is ModelWrapper.Loading ||
                state.species is ModelWrapper.Loading ||
                state.films is ModelWrapper.Loading
            ) {
                LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
            }
            // Character detail item
            state.character.data?.let { characterData ->
                CharacterDetailItem(character = characterData)
            }
            // Planet detail item
            state.planet.data?.let { planetData ->
                PlanetDetailItem(planet = planetData)
            }
            // Species detail item
            state.species.data?.let { species ->
                SpeciesList(species)
            }
            // Films detail item
            state.films.data?.let { films ->
                FilmList(films)
            }
        }
    }

}



