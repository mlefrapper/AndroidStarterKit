package com.mlefrapper.androidstarterkit.bookmark

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mlefrapper.androidstarterkit.bookmark.components.NoBookmarksScreen
import com.mlefrapper.androidstarterkit.ui.R
import com.mlefrapper.androidstarterkit.ui.components.GameItem
import com.mlefrapper.androidstarterkit.ui.theme.Neutral50
import com.mlefrapper.androidstarterkit.ui.theme.Primary50
import com.mlefrapper.core.navigation.Route

@Composable
fun BookmarkScreen(
    viewModel: BookmarkViewModel = hiltViewModel(),
    navController: NavController,
) {
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(
        key1 = Unit,
        block = {
            viewModel.onInit()
        },
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(horizontal = 24.dp),
    ) {
        Column(
            modifier = Modifier.padding(
                vertical = 24.dp,
            ),
        ) {
            Text(
                text = stringResource(
                    id = R.string.title_bookmark,
                ),
                style = MaterialTheme.typography.headlineMedium,
                color = Primary50,
            )
            Text(
                text = stringResource(
                    id = R.string.label_bookmark_description,
                ),
                style = MaterialTheme.typography.bodyMedium,
                color = Neutral50,
            )
        }

        if (state.games.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.White),
                contentPadding = PaddingValues(vertical = 24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                items(
                    items = state.games,
                    key = { it.id },
                ) {
                    GameItem(
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
        } else {
            NoBookmarksScreen()
        }
    }
}
