package com.mlefrapper.androidstarterkit.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mlefrapper.androidstarterkit.detail.components.GameDetailsScreen
import com.mlefrapper.core.navigation.Route

@Composable
fun DetailScreen(
    gameId: Long,
    viewModel: DetailViewModel = hiltViewModel<DetailViewModel>(),
    navController: NavController,
) {
    LaunchedEffect(key1 = Unit, block = {
        viewModel.onInit(gameId)
    })

    val state by viewModel.uiState.collectAsState()

    var savedState by remember {
        mutableStateOf(false)
    }

    state.game?.let { game ->
        GameDetailsScreen(
            game = game,
            isBookmarked = savedState,
            onShareClick = {
                // TODO LFM
            },
            onBookmarkChanged = {
                viewModel.setBookmarked(game.id, !game.isFavorites)
            },
            onBackClick = {
                navController.popBackStack()
            },
            onGameTrailerClick = {
                navController.navigate(Route.VideoPlayer(it))
            },
        )
    }
}
