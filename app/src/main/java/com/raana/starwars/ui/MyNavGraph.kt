package com.raana.starwars.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.raana.starwars.model.CharacterView
import com.raana.starwars.ui.Destinations.CHARACTER_DETAIL_ROUTE
import com.raana.starwars.ui.Destinations.CHARACTER_LIST_ROUTE
import com.raana.starwars.ui.character.CharacterListScreen
import com.raana.starwars.ui.character_detail.CharacterDetailScreen

/**
 * Destinations used in the ([App]).
 */
const val deeplinkUri = "https://www.starwars.com"

object Destinations {
    const val CHARACTER_LIST_ROUTE = "character_list"
    const val CHARACTER_DETAIL_ROUTE = "character_detail"

}


@Composable
fun MyNavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = CHARACTER_LIST_ROUTE,
) {

    val actions = remember(navController) { MainActions(navController) }

    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        composable(
            CHARACTER_DETAIL_ROUTE,
            deepLinks = listOf(navDeepLink { uriPattern = "$deeplinkUri/detail" }),
            arguments = listOf(
                navArgument("character") {
                    nullable = true
                    type = NavType.ParcelableType(CharacterView::class.java)
                },
            )

        ) {
            navController.previousBackStackEntry?.savedStateHandle?.get<CharacterView>(
                "character"
            )?.let {
                CharacterDetailScreen(
                    character = it,
                    viewModel = hiltViewModel(),
                ){
                    navController.popBackStack()
                }
            }
        }

        composable(CHARACTER_LIST_ROUTE) {
            CharacterListScreen(viewModel = hiltViewModel()) {
                actions.navigateToCharacterDetail(it)
            }
        }
    }
}

/**
 * Models the navigation actions in the app.
 */
class MainActions(navController: NavHostController) {

    val navigateToCharacterDetail: (CharacterView) -> Unit = { character ->
        navController.currentBackStackEntry?.savedStateHandle?.apply {
            set("character", character)
        }
        navController.navigate(CHARACTER_DETAIL_ROUTE)
    }

}

