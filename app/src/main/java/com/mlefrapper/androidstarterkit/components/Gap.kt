package com.mlefrapper.androidstarterkit.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ColumnScope.Gap(size: Dp) {
    Gap(
        height = size,
    )
}

@Composable
fun RowScope.Gap(size: Dp) {
    Gap(
        width = size,
    )
}

@Composable
fun Gap(
    width: Dp = 0.dp,
    height: Dp = 0.dp,
) {
    Spacer(
        Modifier.size(
            width = width,
            height = height,
        ),
    )
}
