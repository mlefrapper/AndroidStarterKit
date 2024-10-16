package com.mlefrapper.androidstarterkit.ui.components.snackbar

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.SnackbarVisuals

sealed interface SnackBarMessage {

    data class Text(
        val userMessage: UserMessage,
        val actionLabelMessage: UserMessage? = null,
        val withDismissAction: Boolean = false,
        val duration: SnackbarDuration = SnackbarDuration.Short,
        val onSnackBarResult: (SnackbarResult) -> Unit = {},
    ) : SnackBarMessage

    data class Visuals(
        val snackBarVisuals: SnackbarVisuals,
        val onSnackBarResult: (SnackbarResult) -> Unit = {},
    ) : SnackBarMessage

    companion object {

        fun from(
            userMessage: UserMessage,
            actionLabelMessage: UserMessage? = null,
            withDismissAction: Boolean = false,
            duration: SnackbarDuration = SnackbarDuration.Short,
            onSnackBarResult: (SnackbarResult) -> Unit = {},
        ) = Text(
            userMessage = userMessage,
            actionLabelMessage = actionLabelMessage,
            withDismissAction = withDismissAction,
            duration = duration,
            onSnackBarResult = onSnackBarResult,
        )

        fun from(
            snackBarVisuals: SnackbarVisuals,
            onSnackBarResult: (SnackbarResult) -> Unit,
        ) = Visuals(
            snackBarVisuals = snackBarVisuals,
            onSnackBarResult = onSnackBarResult,
        )
    }
}
