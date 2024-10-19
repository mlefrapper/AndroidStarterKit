package com.mlefrapper.androidstarterkit.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mlefrapper.androidstarterkit.presentation.navigation.BottomBarDestination
import com.mlefrapper.androidstarterkit.presentation.navigation.BottomNavigationBar
import com.mlefrapper.androidstarterkit.presentation.navigation.DestinationsNavHost
import com.mlefrapper.androidstarterkit.ui.components.snackbar.ProvideSnackBarController
import com.mlefrapper.androidstarterkit.ui.components.snackbar.SnackBarMessageHandler
import com.mlefrapper.androidstarterkit.ui.theme.AndroidStarterKitTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()

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

                val snackBarHostState = remember { SnackbarHostState() }
                val coroutineScope = rememberCoroutineScope()

                val uiState by mainViewModel.uiState.collectAsStateWithLifecycle()

                ProvideSnackBarController(
                    snackBarHostState = snackBarHostState,
                    coroutineScope = coroutineScope,
                ) {
                    Scaffold(
                        snackbarHost = {
                            SnackbarHost(
                                hostState = snackBarHostState,
                            )
                        },
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
                        SnackBarMessageHandler(
                            snackBarMessage = uiState.snackBarMessage,
                            onDismissSnackBar = { },
                        )
                        Box(
                            modifier = Modifier.padding(it),
                        ) {
                            DestinationsNavHost(
                                navController = navController,
                                onError = { message ->
                                    mainViewModel.showMessage(message)
                                },
                            )
                        }
                    }
                }
            }
        }
    }
}
