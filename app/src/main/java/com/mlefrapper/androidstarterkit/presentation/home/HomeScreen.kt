package com.mlefrapper.androidstarterkit.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mlefrapper.androidstarterkit.components.Gap
import com.mlefrapper.androidstarterkit.presentation.home.components.GameItem
import com.mlefrapper.androidstarterkit.presentation.home.components.GameItemHorizontal
import com.mlefrapper.androidstarterkit.presentation.home.components.SectionTitle
import com.mlefrapper.androidstarterkit.presentation.home.components.TopBar

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(key1 = Unit, block = {
        viewModel.onInit()
    })

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
    ) {
        LazyColumn(contentPadding = PaddingValues(vertical = 8.dp)) {
            item {
                TopBar()
            }
            item {
                Gap(size = 16.dp)
                SectionTitle(title = "Hot Games")
            }
            item {
                Gap(vertical = 16.dp)
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(horizontal = 24.dp),
                ) {
                    items(items = state.hotGames, key = { it.id }) {
                        GameItemHorizontal(game = it)
                    }
                }
            }
            item {
                Gap(vertical = 24.dp)
                SectionTitle(title = "Popular Games")
                Gap(vertical = 8.dp)
            }
            items(items = state.games, key = { it.id }) {
                GameItem(
                    game = it,
                    modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp),
                    onEvent = { game ->
                        // TODO LFM
                    },
                )
            }
            item {
                Gap(vertical = 8.dp)
            }
        }
    }
}
