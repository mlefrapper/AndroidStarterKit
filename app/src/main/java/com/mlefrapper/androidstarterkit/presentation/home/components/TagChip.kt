package com.mlefrapper.androidstarterkit.presentation.home.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mlefrapper.androidstarterkit.ui.theme.Accent10
import com.mlefrapper.androidstarterkit.ui.theme.Accent50
import com.mlefrapper.androidstarterkit.ui.theme.AndroidStarterKitTheme

@Composable
fun TagChip(name: String, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(
            size = 100.dp
        ),
        color = Accent10,
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.bodyMedium,
            color = Accent50,
            modifier = Modifier.padding(
                vertical = 3.dp,
                horizontal = 12.dp,
            ),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TagChipPreview() {
    AndroidStarterKitTheme {
        TagChip(name = "Sample Tag")
    }
}
