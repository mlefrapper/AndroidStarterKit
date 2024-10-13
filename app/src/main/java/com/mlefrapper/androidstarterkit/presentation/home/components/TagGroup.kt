package com.mlefrapper.androidstarterkit.presentation.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mlefrapper.androidstarterkit.ui.theme.AndroidStarterKitTheme

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TagGroup(
    tag: List<String>,
    modifier: Modifier = Modifier,
    isLimited: Boolean = false,
) {
    val limitedGenres = remember(tag) {
        if (tag.size >= 3) 3 else tag.size
    }

    FlowRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(
            space = 8.dp
        ),
        verticalArrangement = Arrangement.spacedBy(
            space = 8.dp
        ),
    ) {
        repeat(if (isLimited) limitedGenres else tag.size) {
            TagChip(name = tag[it])
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TagGroupPreview() {
    AndroidStarterKitTheme {
        TagGroup(
            tag = listOf("Action", "Adventure", "RPG", "Puzzle"),
            isLimited = true,
        )
    }
}
