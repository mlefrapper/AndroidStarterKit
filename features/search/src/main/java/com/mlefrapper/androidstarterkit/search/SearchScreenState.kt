package com.mlefrapper.androidstarterkit.search

import com.mlefrapper.androidstarterkit.data.model.Game
import com.mlefrapper.core.util.StringUtils.Companion.EMPTY_STRING

data class SearchScreenState(
    val games: List<Game> = emptyList(),
    val query: String = EMPTY_STRING,
    val isLoading: Boolean = false,
    val error: String? = null,
)
