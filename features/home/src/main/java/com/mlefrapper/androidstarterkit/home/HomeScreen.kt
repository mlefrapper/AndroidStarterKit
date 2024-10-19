package com.mlefrapper.androidstarterkit.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.containerColor
import androidx.compose.material3.pulltorefresh.pullToRefresh
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.mlefrapper.androidstarterkit.home.components.GameItemHorizontal
import com.mlefrapper.androidstarterkit.home.components.SectionTitle
import com.mlefrapper.androidstarterkit.home.components.TopBar
import com.mlefrapper.androidstarterkit.ui.R
import com.mlefrapper.androidstarterkit.ui.components.GameItem
import com.mlefrapper.androidstarterkit.ui.components.Gap
import com.mlefrapper.core.navigation.Route

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel<HomeViewModel>(),
    navController: NavHostController,
    onError: (String) -> Unit = {},
) {
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(
        key1 = Unit,
        block = {
            viewModel.refresh(fromPullToRefresh = false)
        },
    )
    state.error?.let(onError)

    val pullRefreshState = rememberPullToRefreshState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pullToRefresh(
                state = pullRefreshState,
                isRefreshing = state.isRefreshingFromPullToRefresh,
                onRefresh = {
                    viewModel.refresh(fromPullToRefresh = true)
                },
            ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
        ) {
            LazyColumn(
                contentPadding = PaddingValues(vertical = 8.dp),
            ) {
                item {
                    TopBar()
                }
                if (state.isRefreshingFromPullToRefresh) {
                    item {
                        Indicator(
                            state = pullRefreshState,
                            isRefreshing = true,
                            containerColor = Color.White,
                            modifier = Modifier
                                .align(CenterHorizontally)
                                .fillMaxWidth()
                                .wrapContentSize(Alignment.TopCenter),
                        )
                    }
                }
                item {
                    Gap(height = 16.dp)
                    SectionTitle(
                        title = stringResource(id = R.string.hot_games),
                    )
                }
                item {
                    Gap(height = 16.dp)
                    LazyRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        contentPadding = PaddingValues(horizontal = 24.dp),
                    ) {
                        items(
                            items = state.hotGames,
                            key = { it.id },
                        ) {
                            GameItemHorizontal(
                                game = it,
                                onItemClick = { gameId ->
                                    navController.navigate(
                                        route = Route.GameDetails(
                                            gameId = gameId,
                                        ),
                                    )
                                },
                            )
                        }
                    }
                }
                item {
                    Gap(height = 24.dp)
                    SectionTitle(
                        title = stringResource(
                            id = R.string.popular_games,
                        ),
                    )
                    Gap(height = 8.dp)
                }
                items(
                    items = state.games,
                    key = { it.id },
                ) {
                    GameItem(
                        game = it,
                        modifier = Modifier.padding(
                            horizontal = 24.dp,
                            vertical = 8.dp,
                        ),
                        onItemClick = { gameId ->
                            navController.navigate(
                                route = Route.GameDetails(
                                    gameId = gameId,
                                ),
                            )
                        },
                    )
                }
                item {
                    Gap(height = 8.dp)
                }
            }
        }
    }
}
