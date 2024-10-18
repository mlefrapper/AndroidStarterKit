package com.mlefrapper.androidstarterkit.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mlefrapper.androidstarterkit.bookmark.BookmarkScreen
import com.mlefrapper.androidstarterkit.detail.DetailScreen
import com.mlefrapper.androidstarterkit.home.HomeScreen
import com.mlefrapper.androidstarterkit.search.SearchScreen
import com.mlefrapper.core.navigation.Route

@Composable
fun DestinationsNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = Route.Search,
        modifier = modifier,
    ) {
        composable<Route.Home> { HomeScreen(navController = navController) }
        composable<Route.Search> { SearchScreen(navController = navController) }
        composable<Route.Bookmark> { BookmarkScreen() }
        composable<Route.GameDetails> {
            val gameId = it.arguments?.getLong("gameId")
            gameId?.let { id ->
                DetailScreen(
                    gameId = id,
                    navController = navController,
                )
            }
        }
    }
}
