package com.mlefrapper.androidstarterkit.bookmark

import com.mlefrapper.androidstarterkit.data.model.Game

data class BookmarkScreenState(
    val games: List<Game> = emptyList(),
)
