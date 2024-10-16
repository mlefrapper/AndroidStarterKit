package com.mlefrapper.androidstarterkit.ui.components.snackbar

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.SnackbarVisuals
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.staticCompositionLocalOf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Immutable
interface SnackBarController {

    fun showMessage(
        message: String,
        actionLabel: String? = null,
        withDismissAction: Boolean = false,
        duration: SnackbarDuration = SnackbarDuration.Short,
        onSnackBarResult: (SnackbarResult) -> Unit = {},
    )

    fun showMessage(
        snackBarVisuals: SnackbarVisuals,
        onSnackBarResult: (SnackbarResult) -> Unit = {},
    )
}

@Stable
fun SnackBarController(
    snackBarHostState: SnackbarHostState,
    coroutineScope: CoroutineScope,
): SnackBarController = SnackBarControllerImpl(
    snackBarHostState = snackBarHostState,
    coroutineScope = coroutineScope,
)

@Composable
fun ProvideSnackBarController(
    snackBarHostState: SnackbarHostState,
    coroutineScope: CoroutineScope,
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalSnackBarController provides SnackBarController(
            snackBarHostState = snackBarHostState,
            coroutineScope = coroutineScope,
        ),
        content = content,
    )
}

@Composable
fun SnackBarMessageHandler(
    snackBarMessage: SnackBarMessage?,
    onDismissSnackBar: () -> Unit,
    snackBarController: SnackBarController = LocalSnackBarController.current,
) {
    if (snackBarMessage == null) return

    when (snackBarMessage) {
        is SnackBarMessage.Text -> {
            val message = snackBarMessage.userMessage.asString()
            val actionLabel = snackBarMessage.actionLabelMessage?.asString()

            LaunchedEffect(snackBarMessage, onDismissSnackBar) {
                snackBarController.showMessage(
                    message = message,
                    actionLabel = actionLabel,
                    withDismissAction = snackBarMessage.withDismissAction,
                    duration = snackBarMessage.duration,
                    onSnackBarResult = snackBarMessage.onSnackBarResult,
                )

                onDismissSnackBar()
            }
        }

        is SnackBarMessage.Visuals -> {
            LaunchedEffect(
                snackBarMessage,
                onDismissSnackBar,
            ) {
                snackBarController.showMessage(
                    snackBarVisuals = snackBarMessage.snackBarVisuals,
                    onSnackBarResult = snackBarMessage.onSnackBarResult,
                )

                onDismissSnackBar()
            }
        }
    }
}

@Immutable
private class SnackBarControllerImpl(
    private val snackBarHostState: SnackbarHostState,
    private val coroutineScope: CoroutineScope,
) : SnackBarController {

    override fun showMessage(
        message: String,
        actionLabel: String?,
        withDismissAction: Boolean,
        duration: SnackbarDuration,
        onSnackBarResult: (SnackbarResult) -> Unit,
    ) {
        coroutineScope.launch {
            snackBarHostState.showSnackbar(
                message = message,
                actionLabel = actionLabel,
                withDismissAction = withDismissAction,
                duration = duration,
            ).let(onSnackBarResult)
        }
    }

    override fun showMessage(
        snackBarVisuals: SnackbarVisuals,
        onSnackBarResult: (SnackbarResult) -> Unit,
    ) {
        coroutineScope.launch {
            snackBarHostState.showSnackbar(visuals = snackBarVisuals).let(onSnackBarResult)
        }
    }
}

val LocalSnackBarController = staticCompositionLocalOf<SnackBarController> {
    error("No SnackbarController provided.")
}
