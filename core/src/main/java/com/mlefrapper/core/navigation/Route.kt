package com.mlefrapper.core.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Route {

    abstract val route: String

    @Serializable
    data object Home : Route() {
        override val route: String
            get() = "home"
    }

    @Serializable
    data object Search : Route() {
        override val route: String
            get() = "search"
    }

    @Serializable
    data object Bookmark : Route() {
        override val route: String
            get() = "bookmark"
    }

    @Serializable
    data class GameDetails(val gameId: Long) : Route() {
        override val route: String
            get() = "game_details/$gameId"
    }
}
