package com.mlefrapper.androidstarterkit.detail.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mlefrapper.androidstarterkit.data.model.Game
import com.mlefrapper.androidstarterkit.ui.R
import com.mlefrapper.androidstarterkit.ui.components.Gap
import com.mlefrapper.androidstarterkit.ui.theme.Primary70
import com.mlefrapper.androidstarterkit.ui.utils.NetworkImage

@Composable
fun GamePoster(
    game: Game,
    modifier: Modifier = Modifier,
    onShareClick: (Long) -> Unit = {},
    onBackClick: () -> Unit = {},
    onGameTrailerClick: (String) -> Unit = {},
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(300.dp),
    ) {
        NetworkImage(
            url = game.backgroundImage,
            modifier = Modifier.fillMaxSize(),
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.2F)),
        )
        Row(
            modifier = Modifier
                .padding(
                    horizontal = 24.dp,
                    vertical = 18.dp,
                )
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .size(24.dp)
                    .clickable(
                        onClick = {
                            onBackClick.invoke()
                        },
                    ),
            )
            Icon(
                imageVector = Icons.Default.Share,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .size(24.dp)
                    .clickable(
                        onClick = {
                            onShareClick.invoke(game.id)
                        },
                    ),
            )
        }
        if (!game.trailerUrl.isNullOrEmpty()) {
            Column(
                modifier = Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .background(Color.White, CircleShape)
                        .clickable {
                            game.trailerUrl?.let(onGameTrailerClick)
                        },
                ) {
                    Image(
                        painter = painterResource(
                            id = R.drawable.ic_play,
                        ),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(Primary70),
                        modifier = Modifier
                            .size(24.dp)
                            .align(Alignment.Center),
                    )
                }
                Gap(size = 8.dp)
                Text(
                    text = stringResource(
                        id = R.string.action_play_trailer,
                    ),
                    style = MaterialTheme.typography.titleSmall,
                    color = Color.White,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GamePosterPreview() {
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

    com.mlefrapper.androidstarterkit.ui.theme.AndroidStarterKitTheme {
        GamePoster(
            game = sampleGame,
            onShareClick = {},
            onBackClick = {},
        )
    }
}
