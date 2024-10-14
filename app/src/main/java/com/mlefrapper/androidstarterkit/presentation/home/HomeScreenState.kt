package com.mlefrapper.androidstarterkit.presentation.home

import com.mlefrapper.androidstarterkit.domain.model.Game

data class HomeScreenState(
    val games: List<Game> = emptyList(),
    val hotGames: List<Game> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
