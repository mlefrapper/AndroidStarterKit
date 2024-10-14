package com.mlefrapper.androidstarterkit.presentation.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mlefrapper.androidstarterkit.components.Gap
import com.mlefrapper.androidstarterkit.domain.model.Game
import com.mlefrapper.androidstarterkit.ui.theme.AndroidStarterKitTheme
import com.mlefrapper.androidstarterkit.ui.theme.Neutral40
import com.mlefrapper.androidstarterkit.ui.theme.Neutral50
import com.mlefrapper.androidstarterkit.ui.theme.Neutral60
import com.mlefrapper.androidstarterkit.ui.theme.Primary80
import com.mlefrapper.androidstarterkit.ui.theme.Yellow
import com.mlefrapper.androidstarterkit.utils.ConverterDate
import com.mlefrapper.androidstarterkit.utils.NetworkImage
import com.mlefrapper.androidstarterkit.utils.convertDateTo

@Composable
fun GameItem(
    game: Game,
    modifier: Modifier = Modifier,
    onEvent: (Game) -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(120.dp)
            .clickable {
                onEvent(game)
            },
    ) {
        NetworkImage(
            url = game.backgroundImage,
            modifier = Modifier
                .fillMaxHeight()
                .width(
                    width = 85.dp,
                )
                .clip(
                    shape = RoundedCornerShape(
                        5.dp,
                    ),
                ),
        )
        Gap(
            size = 16.dp,
        )
        Column(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                text = game.name,
                style = MaterialTheme.typography.bodyLarge,
                color = Primary80,
            )
            Gap(
                size = 8.dp,
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = null,
                    tint = Yellow,
                    modifier = Modifier.size(12.dp),
                )
                Gap(
                    size = 2.dp,
                )
                Text(
                    text = "${game.rating}/5",
                    style = MaterialTheme.typography.labelMedium,
                    color = Neutral60,
                )
            }
            Gap(
                size = 8.dp,
            )
            TagGroup(
                tag = game.genres,
                isLimited = true,
            )
            Gap(
                size = 8.dp,
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = null,
                    tint = Neutral40,
                    modifier = Modifier.size(12.dp),
                )
                Gap(
                    size = 4.dp,
                )
                Text(
                    text = game.released.convertDateTo(ConverterDate.FULL_DATE),
                    style = MaterialTheme.typography.labelSmall,
                    color = Neutral50,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GameItemPreview() {
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
        shortScreenshots = listOf("https://via.placeholder.com/100", "https://via.placeholder.com/100"),
        isFavorites = true,
        description = "This is a sample game description.",
        trailerUrl = "https://www.youtube.com/watch?v=dQw4w9WgXcQ",
    )

    AndroidStarterKitTheme {
        GameItem(
            game = sampleGame,
            onEvent = {},
        )
    }
}
