package com.mlefrapper.androidstarterkit.presentation.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mlefrapper.androidstarterkit.R
import com.mlefrapper.androidstarterkit.components.Gap
import com.mlefrapper.androidstarterkit.ui.theme.Neutral10
import com.mlefrapper.androidstarterkit.ui.theme.Neutral40
import com.mlefrapper.androidstarterkit.ui.theme.Primary50

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel<SearchViewModel>(),
) {
    val state by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = Color.White
            )
            .padding(
                horizontal = 24.dp
            ),
    ) {
        Gap(
            size = 48.dp
        )
        Text(
            text = stringResource(
                id = R.string.what_games_are_you_looking_for
            ),
            style = MaterialTheme.typography.headlineMedium,
            color = Primary50,
        )
        Gap(
            size = 24.dp
        )
        TextField(
            value = state.query,
            onValueChange = {
            },
            textStyle = MaterialTheme.typography.bodyLarge,
            placeholder = {
                Text(
                    text = stringResource(
                        id = R.string.search
                    ),
                    color = Neutral40,
                    style = MaterialTheme.typography.bodyLarge,
                )
            },
            leadingIcon = {
                Image(
                    painter = painterResource(
                        id = R.drawable.ic_search
                    ),
                    contentDescription = null,
                    modifier = Modifier.size(
                        size = 32.dp
                    ),
                )
            },
            trailingIcon = {
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Neutral10,
                unfocusedContainerColor = Neutral10,
                disabledContainerColor = Neutral10,
                cursorColor = Color.Black,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledLabelColor = Color.Blue,
            ),
            maxLines = 1,
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .clip(
                    shape = RoundedCornerShape(
                        size = 12.dp
                    )
                ),
        )
        Gap(
            size = 12.dp
        )
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                vertical = 24.dp
            ),
            verticalArrangement = Arrangement.spacedBy(
                space = 16.dp
            ),
        ) {
        }
    }
}
