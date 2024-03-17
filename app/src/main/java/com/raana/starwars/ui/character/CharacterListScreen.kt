package com.raana.starwars.ui.character

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarHost
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.raana.starwars.R
import com.raana.starwars.base.ModelWrapper
import com.raana.starwars.base.state
import com.raana.starwars.ui.component.AppTopBar
import com.raana.starwars.ui.component.TopBarNavigationType
import com.raana.starwars.model.CharacterView
import com.raana.starwars.ui.character.component.CharacterItem
import com.raana.starwars.ui.character.component.SearchView
import com.raana.starwars.ui.component.Shimmer
import com.raana.starwars.ui.theme.red

/**
 * Screen to show list of content retrieved from api
 *
 * @param viewModel
 */
@Composable
fun CharacterListScreen(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    viewModel: CharacterListViewModel,
    navigateToDetail: (CharacterView) -> Unit,
) {
    val state by viewModel.state()
    val focusManager = LocalFocusManager.current
    Scaffold(
        scaffoldState = scaffoldState,
        // top app bar content section, here showing sync with server and logout part
        topBar = {
            AppTopBar(
                navigationType = TopBarNavigationType.NONE,
                title = stringResource(id = R.string.app_name)
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
    ) { paddingValues ->
        var searchText by remember { mutableStateOf(TextFieldValue(state.searchText)) }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.surface)
                .padding(paddingValues)
        ) {
            SearchView(
                searchText = searchText,
                onSearchTextChange = {
                    // fetch character content by search query
                    searchText = it
                    viewModel.getCharacterList(it.text)
                },
            )
            val swipeRefreshState = rememberSwipeRefreshState(false)
            SwipeRefresh(state = swipeRefreshState, onRefresh = {
                // on wipe refresh, read the character content
                viewModel.refreshCharacterList()
            }) {
                //Scrollable column
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colors.surface)
                ) {

                    when (state.characters) {

                        // show Error when Character List state is in Failure state
                        is ModelWrapper.Failure -> {
                            item {
                                Text(
                                    text = (state.characters as ModelWrapper.Failure<List<CharacterView>>).throwable.message
                                        ?: stringResource(id = R.string.error)
                                )
                            }
                        }

                        is ModelWrapper.Loading -> {
                            // show Shimmer when Character List state is in Loading state
                            items(20) {
                                Shimmer(
                                    width = 240.dp,
                                    height = 56.dp,
                                    shape = MaterialTheme.shapes.small,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(4.dp)
                                )
                            }
                        }

                        is ModelWrapper.None -> {
                        }

                        is ModelWrapper.Success -> {
                            // show list of Character items fetched
                            state.characters.data?.let { characters ->
                                items(characters) { character ->
                                    CharacterItem(character = character) {
                                        focusManager.clearFocus()
                                        navigateToDetail(character)
                                    }
                                    Divider(
                                        color = MaterialTheme.colors.background,
                                        startIndent = 4.dp
                                    )
                                }
                            }
                        }
                    }

                }
            }
        }

    }
}
