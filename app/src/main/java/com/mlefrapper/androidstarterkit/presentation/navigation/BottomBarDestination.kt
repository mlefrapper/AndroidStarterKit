package com.mlefrapper.androidstarterkit.presentation.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.mlefrapper.androidstarterkit.R

enum class BottomBarDestination(
    val route: String,
    @StringRes val labelResId: Int,
    @DrawableRes val iconResId: Int,
) {
    Home(
        route = "home",
        labelResId = R.string.title_home,
        iconResId = R.drawable.ic_ghost,
    ),
    Search(
        route = "search",
        labelResId = R.string.title_search,
        iconResId = R.drawable.ic_search,
    ),
    Bookmark(
        route = "bookmark",
        labelResId = R.string.title_bookmark,
        iconResId = R.drawable.ic_bookmark,
    ),
}
