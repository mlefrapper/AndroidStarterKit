package com.mlefrapper.androidstarterkit.presentation.navigation

import com.mlefrapper.androidstarterkit.data.model.Game
import kotlinx.serialization.Serializable

@Serializable
sealed class Route {
    @Serializable
    data object Home : Route()

    @Serializable
    data object Search : Route()

    @Serializable
    data object Bookmark : Route()

    @Serializable
    data class GameDetails(val game: Game) : Route()
}
