package com.mlefrapper.androidstarterkit.detail.components

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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mlefrapper.androidstarterkit.data.model.Game
import com.mlefrapper.androidstarterkit.ui.R
import com.mlefrapper.androidstarterkit.ui.components.Gap
import com.mlefrapper.androidstarterkit.ui.components.TagGroup
import com.mlefrapper.androidstarterkit.ui.theme.AndroidStarterKitTheme
import com.mlefrapper.androidstarterkit.ui.theme.Neutral40
import com.mlefrapper.androidstarterkit.ui.theme.Neutral50
import com.mlefrapper.androidstarterkit.ui.theme.Primary70

@Composable
fun GameDetailsScreen(
    game: Game,
    onShareClick: (Long) -> Unit = {},
    onBackClick: () -> Unit = {},
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(
                state = rememberScrollState(),
            ),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy((-30).dp),
        ) {
            GamePoster(
                game = game,
                onShareClick = {
                    onShareClick.invoke(game.id)
                },
                onBackClick = {
                    onBackClick.invoke()
                },
            )
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
                    .padding(all = 24.dp),
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
                    Gap(size = 8.dp)
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = null,
                        tint = Primary70,
                        modifier = Modifier
                            .size(32.dp)
                            .padding(top = 4.dp)
                            .clickable(
                                onClick = {
                                    // savedState = !savedState
                                },
                            ),
                    )
                }
                Gap(size = 8.dp)
                GeneralGameInfo(game = game)
                Gap(size = 24.dp)
                Text(
                    text = stringResource(
                        id = R.string.description,
                    ),
                    style = MaterialTheme.typography.titleMedium,
                    color = Primary70,
                )
                Gap(size = 8.dp)
                Text(
                    text = game.description.ifBlank { "-" },
                    style = MaterialTheme.typography.labelSmall,
                    color = Neutral50,
                )
                Gap(size = 24.dp)
                Screenshots(
                    urls = game.shortScreenshots,
                )
                if (game.tags.isNotEmpty()) {
                    Gap(size = 24.dp)
                    Text(
                        text = stringResource(id = R.string.tag),
                        style = MaterialTheme.typography.bodyMedium,
                        color = Neutral40,
                    )
                    Gap(size = 8.dp)
                    TagGroup(tag = game.tags.take(8))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GameDetailsScreenPreview() {
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
                "https://via.placeholder.com/100",
            ),
            isFavorites = true,
            description = "This is a sample game description.",
            trailerUrl = "https://www.youtube.com/watch?v=dQw4w9WgXcQ",
        )

        GameDetailsScreen(game = sampleGame)
    }
}
