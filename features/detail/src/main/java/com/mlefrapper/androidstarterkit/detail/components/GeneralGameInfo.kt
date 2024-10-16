package com.mlefrapper.androidstarterkit.detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mlefrapper.androidstarterkit.data.model.Game
import com.mlefrapper.androidstarterkit.data.utils.ConverterDate
import com.mlefrapper.androidstarterkit.data.utils.convertDateTo
import com.mlefrapper.androidstarterkit.ui.R
import com.mlefrapper.androidstarterkit.ui.components.Gap
import com.mlefrapper.androidstarterkit.ui.components.RatingBar
import com.mlefrapper.androidstarterkit.ui.components.TagGroup
import com.mlefrapper.androidstarterkit.ui.theme.Accent10
import com.mlefrapper.androidstarterkit.ui.theme.Accent50
import com.mlefrapper.androidstarterkit.ui.theme.Neutral40
import com.mlefrapper.androidstarterkit.ui.theme.Neutral50

@Composable
fun GeneralGameInfo(
    game: Game,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(36.dp),
        ) {
            Column {
                Text(
                    text = stringResource(
                        id = R.string.metascore,
                    ),
                    style = MaterialTheme.typography.bodyMedium,
                    color = Neutral40,
                )
                Gap(size = 8.dp)
                Box(
                    modifier = Modifier
                        .size(size = 36.dp)
                        .clip(
                            shape = RoundedCornerShape(
                                size = 8.dp,
                            ),
                        )
                        .background(Accent10),
                ) {
                    Text(
                        text = if (game.metacritic != 0) game.metacritic.toString() else "-",
                        style = MaterialTheme.typography.titleSmall,
                        color = Accent50,
                        modifier = Modifier.align(Alignment.Center),
                    )
                }
            }
            Column {
                Text(
                    text = stringResource(
                        id = R.string.rating,
                    ),
                    style = MaterialTheme.typography.bodyMedium,
                    color = Neutral40,
                )
                Gap(size = 8.dp)
                RatingBar(
                    rating = game.rating.toFloat(),
                    modifier = Modifier
                        .height(height = 16.dp),
                )
            }
        }
        Gap(size = 24.dp)
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(
                space = 40.dp,
            ),
        ) {
            Column {
                Text(
                    text = stringResource(
                        id = R.string.released,
                    ),
                    style = MaterialTheme.typography.bodyMedium,
                    color = Neutral40,
                )
                Gap(
                    size = 8.dp,
                )
                Text(
                    text = game.released.convertDateTo(
                        desireFormat = ConverterDate.FULL_DATE,
                    ),
                    style = MaterialTheme.typography.bodyMedium,
                    color = Neutral50,
                )
            }
            Column {
                Text(
                    text = stringResource(
                        id = R.string.genre,
                    ),
                    style = MaterialTheme.typography.bodyMedium,
                    color = Neutral40,
                )
                Gap(size = 8.dp)
                TagGroup(tag = game.genres)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GeneralGameInfoPreview() {
    com.mlefrapper.androidstarterkit.ui.theme.AndroidStarterKitTheme {
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

        GeneralGameInfo(game = sampleGame)
    }
}
