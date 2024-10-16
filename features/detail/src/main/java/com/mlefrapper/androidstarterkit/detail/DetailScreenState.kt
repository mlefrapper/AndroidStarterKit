package com.mlefrapper.androidstarterkit.detail

import com.mlefrapper.androidstarterkit.data.model.Game

data class DetailScreenState(
    val game: Game? = null,
    val trailerUrl: String? = null,
    val isLoading: Boolean = false,
    val shareSheetGame: Game? = null,
    val error: String? = null,
)
