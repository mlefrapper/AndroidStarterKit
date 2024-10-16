package com.mlefrapper.androidstarterkit.presentation.home.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mlefrapper.androidstarterkit.bookmark.BookmarkScreen
import com.mlefrapper.androidstarterkit.home.HomeScreen
import com.mlefrapper.androidstarterkit.presentation.navigation.Route
import com.mlefrapper.androidstarterkit.search.SearchScreen

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
        /*
        composable<Route.GameDetails> { backStackEntry ->
            DetailScreen(game = backStackEntry.toRoute())
        }
         */
    }
}
