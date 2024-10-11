package com.mlefrapper.androidstarterkit.presentation.home

import com.mlefrapper.androidstarterkit.domain.model.Game

sealed class HomeScreenEvent {
    data class NavigateToDetailScreen(val game: Game) : HomeScreenEvent()
}
