package com.mlefrapper.androidstarterkit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mlefrapper.androidstarterkit.presentation.home.components.DestinationsNavHost
import com.mlefrapper.androidstarterkit.presentation.navigation.BottomBarDestination
import com.mlefrapper.androidstarterkit.presentation.navigation.BottomNavigationBar
import com.mlefrapper.androidstarterkit.ui.theme.AndroidStarterKitTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(
            window,
            false,
        )
        setContent {
            AndroidStarterKitTheme {
                val navController = rememberNavController()

                val currentRoute =
                    navController.currentBackStackEntryAsState().value?.destination?.route
                val bottomBarRoutes = BottomBarDestination.entries.map {
                    it.route::class.qualifiedName
                }
                val shouldShowBottomBar = currentRoute in bottomBarRoutes

                Scaffold(
                    bottomBar = {
                        if (shouldShowBottomBar) {
                            BottomNavigationBar(
                                navController = navController,
                                items = BottomBarDestination.entries,
                                onItemClick = {
                                    navController.navigate(it.route) {
                                        popUpTo(
                                            navController
                                                .graph
                                                .findStartDestination()
                                                .id,
                                        ) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                },
                            )
                        }
                    },
                ) {
                    Box(
                        modifier = Modifier.padding(it),
                    ) {
                        DestinationsNavHost(
                            navController = navController,
                        )
                    }
                }
            }
        }
    }
}
