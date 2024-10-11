package com.mlefrapper.androidstarterkit.presentation.search

import com.mlefrapper.androidstarterkit.domain.model.Game

data class SearchScreenState(
    val games: List<Game> = emptyList(),
    val query: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
)
