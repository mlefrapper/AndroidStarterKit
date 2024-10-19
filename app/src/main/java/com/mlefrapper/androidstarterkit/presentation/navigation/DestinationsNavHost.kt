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
import com.mlefrapper.androidstarterkit.videoplayer.VideoPlayerScreen
import com.mlefrapper.core.navigation.Route

@Composable
fun DestinationsNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    onError: (String) -> Unit = {},
) {
    NavHost(
        navController = navController,
        startDestination = Route.Search,
        modifier = modifier,
    ) {
        composable<Route.Home> {
            HomeScreen(
                navController = navController,
                onError = onError,
            )
        }
        composable<Route.Search> {
            SearchScreen(
                navController = navController,
                onError = onError,
            )
        }
        composable<Route.Bookmark> {
            BookmarkScreen(
                navController = navController,
            )
        }
        composable<Route.GameDetails> {
            val gameId = it.arguments?.getLong("gameId")
            gameId?.let { id ->
                DetailScreen(
                    gameId = id,
                    navController = navController,
                )
            }
        }
        composable<Route.VideoPlayer> {
            val url = it.arguments?.getString("url")
            url?.let {
                VideoPlayerScreen(
                    url = url,
                )
            }
        }
    }
}
