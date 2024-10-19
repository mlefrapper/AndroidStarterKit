package com.mlefrapper.androidstarterkit.search.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.mlefrapper.androidstarterkit.ui.R
import com.mlefrapper.androidstarterkit.ui.theme.AndroidStarterKitTheme
import com.mlefrapper.androidstarterkit.ui.theme.Neutral50

@Composable
fun SearchHelpScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = stringResource(id = R.string.search_field_empty),
            style = MaterialTheme.typography.bodyMedium,
            color = Neutral50,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SearchHelpScreenPreview() {
    AndroidStarterKitTheme {
        SearchHelpScreen()
    }
}
