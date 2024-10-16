package com.mlefrapper.androidstarterkit.home

import com.mlefrapper.androidstarterkit.data.model.Game

sealed class HomeScreenEvent {
    data class NavigateToDetailScreen(val game: Game) : HomeScreenEvent()
}
