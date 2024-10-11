package com.mlefrapper.androidstarterkit.presentation.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mlefrapper.androidstarterkit.ui.theme.AndroidStarterKitTheme
import com.mlefrapper.androidstarterkit.ui.theme.Primary50

@Composable
fun SectionTitle(
    title: String,
    modifier: Modifier = Modifier,
    isShowMore: Boolean = false,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                horizontal = 24.dp,
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleSmall,
            color = Primary50,
        )
        if (isShowMore) SeeMoreCard()
    }
}

@Preview(showBackground = true)
@Composable
fun SectionTitlePreview() {
    AndroidStarterKitTheme {
        SectionTitle(
            title = "Sample Section Title",
            isShowMore = true,
        )
    }
}
