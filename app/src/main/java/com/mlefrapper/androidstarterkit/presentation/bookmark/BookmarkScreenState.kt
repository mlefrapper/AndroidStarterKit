package com.mlefrapper.androidstarterkit.presentation.bookmark

import com.mlefrapper.androidstarterkit.domain.model.Game

data class BookmarkScreenState(
    val games: List<Game> = emptyList(),
)
