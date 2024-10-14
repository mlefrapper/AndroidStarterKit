package com.mlefrapper.androidstarterkit.presentation.home.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mlefrapper.androidstarterkit.presentation.bookmark.BookmarkScreen
import com.mlefrapper.androidstarterkit.presentation.home.HomeScreen
import com.mlefrapper.androidstarterkit.presentation.navigation.BottomBarDestination
import com.mlefrapper.androidstarterkit.presentation.search.SearchScreen

@Composable
fun DestinationsNavHost(
    navController: androidx.navigation.NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = "Search",
        modifier = modifier,
    ) {
        composable(
            route = BottomBarDestination.Home.route,
        ) { HomeScreen() }
        composable(
            route = BottomBarDestination.Search.route,
        ) { SearchScreen() }
        composable(
            route = BottomBarDestination.Bookmark.route,
        ) { BookmarkScreen() }
    }
}
