package com.mlefrapper.androidstarterkit.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mlefrapper.androidstarterkit.detail.components.GameDetailsScreen

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

    state.game?.let { game ->
        GameDetailsScreen(
            game = game,
            onShareClick = {
                // TODO LFM
            },
            onBackClick = {
                navController.popBackStack()
            },
        )
    }
}
