package com.mlefrapper.androidstarterkit.presentation.home.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.mlefrapper.androidstarterkit.domain.model.Game
import com.mlefrapper.androidstarterkit.presentation.bookmark.BookmarkScreen
import com.mlefrapper.androidstarterkit.presentation.detail.DetailScreen
import com.mlefrapper.androidstarterkit.presentation.home.HomeScreen
import com.mlefrapper.androidstarterkit.presentation.search.SearchScreen
import kotlinx.serialization.Serializable

@Composable
fun DestinationsNavHost(
    navController: androidx.navigation.NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = Route.Search,
        modifier = modifier,
    ) {
        composable<Route.Home> { HomeScreen() }
        composable<Route.Search> { SearchScreen() }
        composable<Route.Bookmark> { BookmarkScreen() }
        composable<Route.GameDetails> { backStackEntry ->
            DetailScreen(game = backStackEntry.toRoute())
        }
    }
}

@Serializable
sealed class Route {
    @Serializable
    data object Home : Route()

    @Serializable
    data object Search : Route()

    @Serializable
    data object Bookmark : Route()

    @Serializable
    data class GameDetails(val game: Game) : Route()
}
