package com.mlefrapper.androidstarterkit.home

import com.mlefrapper.androidstarterkit.data.model.Game

data class HomeScreenState(
    val games: List<Game> = emptyList(),
    val hotGames: List<Game> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshingFromPullToRefresh: Boolean = false,
    val error: String? = null,
)
