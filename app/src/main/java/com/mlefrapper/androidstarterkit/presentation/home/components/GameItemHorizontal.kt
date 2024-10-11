package com.mlefrapper.androidstarterkit.presentation.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.mlefrapper.androidstarterkit.components.Gap
import com.mlefrapper.androidstarterkit.domain.model.Game
import com.mlefrapper.androidstarterkit.ui.theme.Primary80
import com.mlefrapper.androidstarterkit.utils.NetworkImage

@Composable
fun GameItemHorizontal(
    game: Game,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .width(140.dp)
            .clickable {
                // TODO LFM
            },
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        NetworkImage(
            url = game.backgroundImage,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(5.dp)),
        )
        Gap(size = 12.dp)
        Text(
            text = game.name,
            style = MaterialTheme.typography.bodyLarge,
            color = Primary80,
            textAlign = TextAlign.Center,
        )
        Gap(size = 8.dp)
        RatingBar(
            rating = game.rating.toFloat(),
            modifier = Modifier.height(10.dp),
        )
    }
}
