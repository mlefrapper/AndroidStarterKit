package com.mlefrapper.androidstarterkit.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.mlefrapper.androidstarterkit.search.components.NoSearchResultsScreen
import com.mlefrapper.androidstarterkit.search.components.SearchHelpScreen
import com.mlefrapper.androidstarterkit.ui.R
import com.mlefrapper.androidstarterkit.ui.components.GameItem
import com.mlefrapper.androidstarterkit.ui.components.Gap
import com.mlefrapper.androidstarterkit.ui.theme.Neutral40
import com.mlefrapper.androidstarterkit.ui.theme.Neutral5
import com.mlefrapper.androidstarterkit.ui.theme.Primary50
import com.mlefrapper.core.navigation.Route

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel<SearchViewModel>(),
    navController: NavHostController,
    onError: (String) -> Unit = {},
) {
    val state by viewModel.uiState.collectAsState()

    state.error?.let(onError)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(
                vertical = 24.dp,
                horizontal = 24.dp,
            ),
    ) {
        Text(
            text = stringResource(
                id = R.string.what_games_are_you_looking_for,
            ),
            style = MaterialTheme.typography.headlineMedium,
            color = Primary50,
        )
        Gap(size = 24.dp)
        TextField(
            value = state.query,
            onValueChange = {
                viewModel.onSearchQueryChanged(it)
            },
            textStyle = MaterialTheme.typography.bodyLarge,
            placeholder = {
                Text(
                    text = stringResource(
                        id = R.string.search,
                    ),
                    color = Neutral40,
                    style = MaterialTheme.typography.bodyLarge,
                )
            },
            leadingIcon = {
                Image(
                    painter = painterResource(
                        id = R.drawable.ic_search,
                    ),
                    contentDescription = null,
                    modifier = Modifier.size(
                        size = 32.dp,
                    ),
                )
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Neutral5,
                unfocusedContainerColor = Neutral5,
                disabledContainerColor = Neutral5,
                cursorColor = Color.Black,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledLabelColor = Color.Blue,
            ),
            maxLines = 1,
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .clip(
                    shape = RoundedCornerShape(size = 12.dp),
                ),
        )
        Gap(size = 12.dp)
        if (state.games.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(vertical = 24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                items(
                    items = state.games,
                    key = { it.id },
                ) {
                    val game = it
                    GameItem(
                        game = game,
                        onItemClick = {
                            navController.navigate(
                                route = Route.GameDetails(
                                    gameId = game.id,
                                ),
                            )
                        },
                    )
                }
            }
        } else {
            if (state.query.isNotEmpty()) {
                NoSearchResultsScreen()
            } else {
                SearchHelpScreen()
            }
        }
    }
}
