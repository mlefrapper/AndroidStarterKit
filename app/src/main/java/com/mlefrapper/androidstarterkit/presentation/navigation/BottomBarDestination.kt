package com.mlefrapper.androidstarterkit.presentation.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.mlefrapper.androidstarterkit.ui.R
import com.mlefrapper.core.navigation.Route

enum class BottomBarDestination(
    val route: Route,
    @StringRes val labelResId: Int,
    @DrawableRes val iconResId: Int,
) {
    Home(
        route = Route.Home,
        labelResId = R.string.title_home,
        iconResId = R.drawable.ic_ghost,
    ),
    Search(
        route = Route.Search,
        labelResId = R.string.title_search,
        iconResId = R.drawable.ic_search,
    ),
    Bookmark(
        route = Route.Bookmark,
        labelResId = R.string.title_bookmark,
        iconResId = R.drawable.ic_bookmark,
    ),
}
