package com.mlefrapper.androidstarterkit.detail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mlefrapper.androidstarterkit.ui.R
import com.mlefrapper.androidstarterkit.ui.components.Gap
import com.mlefrapper.androidstarterkit.ui.theme.Primary70
import com.mlefrapper.androidstarterkit.ui.utils.NetworkImage

@Composable
fun Screenshots(
    urls: List<String>,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(
                id = R.string.screenshots,
            ),
            style = MaterialTheme.typography.titleMedium,
            color = Primary70,
        )
        Gap(size = 8.dp)
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
        ) {
            items(
                items = urls,
                key = { it },
            ) {
                NetworkImage(
                    url = it,
                    modifier = Modifier
                        .fillParentMaxWidth()
                        .height(height = 200.dp),
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenshotsPreview() {
    com.mlefrapper.androidstarterkit.ui.theme.AndroidStarterKitTheme {
        Screenshots(
            urls = listOf(
                "https://example.com/screenshot1.jpg",
                "https://example.com/screenshot2.jpg",
                "https://example.com/screenshot3.jpg",
            ),
        )
    }
}
