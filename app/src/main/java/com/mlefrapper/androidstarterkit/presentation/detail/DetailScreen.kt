package com.mlefrapper.androidstarterkit.presentation.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mlefrapper.androidstarterkit.R
import com.mlefrapper.androidstarterkit.components.Gap
import com.mlefrapper.androidstarterkit.domain.model.Game
import com.mlefrapper.androidstarterkit.presentation.detail.components.GamePoster
import com.mlefrapper.androidstarterkit.presentation.detail.components.GeneralGameInfo
import com.mlefrapper.androidstarterkit.presentation.detail.components.Screenshots
import com.mlefrapper.androidstarterkit.presentation.home.components.TagGroup
import com.mlefrapper.androidstarterkit.ui.theme.AndroidStarterKitTheme
import com.mlefrapper.androidstarterkit.ui.theme.Neutral40
import com.mlefrapper.androidstarterkit.ui.theme.Neutral50
import com.mlefrapper.androidstarterkit.ui.theme.Primary70

@Composable
fun DetailScreen(
    game: Game,
    viewModel: DetailViewModel = hiltViewModel(),
) {
    val context = LocalContext.current

    LaunchedEffect(key1 = Unit, block = {
        // viewModel.onInit(game)
    })

    var savedState by remember {
        mutableStateOf(game.isFavorites)
    }

    val state by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(
                state = rememberScrollState(),
            ),
    ) {
        state.game?.let { game ->
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy((-30).dp),
            ) {
                GamePoster(game = game)
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(
                            shape = RoundedCornerShape(
                                topStart = 20.dp,
                                topEnd = 20.dp,
                            ),
                        )
                        .background(
                            color = Color.White,
                        )
                        .padding(
                            all = 24.dp,
                        ),
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.Top,
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Text(
                            text = game.name,
                            style = MaterialTheme.typography.titleLarge,
                            color = Primary70,
                            modifier = Modifier.weight(1F),
                        )
                        Gap(
                            size = 8.dp,
                        )
                        Icon(
                            imageVector = if (savedState) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                            contentDescription = null,
                            tint = Primary70,
                            modifier = Modifier
                                .size(32.dp)
                                .padding(top = 4.dp)
                                .clickable(
                                    onClick = {
                                        savedState = !savedState
                                    },
                                ),
                        )
                    }
                    Gap(
                        size = 8.dp,
                    )
                    GeneralGameInfo(
                        game = game,
                    )
                    Gap(
                        size = 24.dp,
                    )
                    Text(
                        text = stringResource(
                            id = R.string.description,
                        ),
                        style = MaterialTheme.typography.titleMedium,
                        color = Primary70,
                    )
                    Gap(
                        size = 8.dp,
                    )
                    Text(
                        text = game.description.ifBlank { "-" },
                        style = MaterialTheme.typography.labelSmall,
                        color = Neutral50,
                    )
                    Gap(
                        size = 24.dp,
                    )
                    Screenshots(
                        urls = game.shortScreenshots,
                    )
                    if (game.tags.isNotEmpty()) {
                        Gap(
                            size = 24.dp,
                        )
                        Text(
                            text = stringResource(id = R.string.tag),
                            style = MaterialTheme.typography.bodyMedium,
                            color = Neutral40,
                        )
                        Gap(
                            size = 8.dp,
                        )
                        TagGroup(
                            tag = game.tags.take(8),
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    AndroidStarterKitTheme {
        val sampleGame = Game(
            id = 123,
            slug = "sample-game",
            name = "Sample Game",
            released = "2023-10-27",
            tba = false,
            backgroundImage = "https://via.placeholder.com/150",
            rating = 4.5,
            ratingTop = 5,
            ratingsCount = 1000,
            reviewsTextCount = 500,
            added = 200,
            metacritic = 85,
            playtime = 10,
            suggestionsCount = 50,
            updated = "2023-10-28",
            reviewsCount = 250,
            saturatedColor = "#FF0000",
            dominantColor = "#0000FF",
            parentPlatforms = listOf("PC", "PlayStation"),
            genres = listOf("Action", "Adventure"),
            stores = listOf("Steam", "PlayStation Store"),
            tags = listOf("Singleplayer", "Multiplayer"),
            esrbRating = "Teen",
            shortScreenshots = listOf(
                "https://via.placeholder.com/100",
                "https://via.placeholder.com/100"
            ),
            isFavorites = true,
            description = "This is a sample game description.",
            trailerUrl = "https://www.youtube.com/watch?v=dQw4w9WgXcQ",
        )

        DetailScreen(game = sampleGame)
    }
}
