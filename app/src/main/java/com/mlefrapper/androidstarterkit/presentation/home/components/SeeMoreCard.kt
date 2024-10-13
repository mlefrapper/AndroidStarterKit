package com.mlefrapper.androidstarterkit.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mlefrapper.androidstarterkit.R
import com.mlefrapper.androidstarterkit.ui.theme.Neutral20
import com.mlefrapper.androidstarterkit.ui.theme.Neutral50

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
fun SeeMoreCard(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .clip(
                shape = RoundedCornerShape(
                    size = 100.dp
                )
            )
            .border(
                width = 1.dp,
                color = Neutral20,
                shape = RoundedCornerShape(
                    size = 100.dp
                )
            )
            .background(Color.White)
            .clickable { },
    ) {
        Text(
            text = stringResource(id = R.string.see_more),
            style = MaterialTheme.typography.bodySmall,
            color = Neutral50,
            modifier = Modifier.padding(
                vertical = 4.dp,
                horizontal = 8.dp
            ),
        )
    }
}
