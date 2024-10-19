package com.mlefrapper.androidstarterkit.presentation.main

import androidx.annotation.StringRes
import androidx.compose.material3.SnackbarDuration
import androidx.lifecycle.ViewModel
import com.mlefrapper.androidstarterkit.ui.components.snackbar.SnackBarMessage
import com.mlefrapper.androidstarterkit.ui.components.snackbar.UserMessage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(MainUiState())
    val uiState = _uiState.asStateFlow()

    fun showMessage(
        @StringRes snackBarMessageResId: Int,
    ) {
        showMessage(
            UserMessage.from(
                resId = snackBarMessageResId,
            ),
        )
    }

    fun showMessage(
        snackBarMessage: String,
    ) {
        showMessage(
            UserMessage.from(
                value = snackBarMessage,
            ),
        )
    }

    private fun showMessage(
        userMessage: UserMessage,
    ) {
        _uiState.update {
            it.copy(
                snackBarMessage = SnackBarMessage.from(
                    userMessage = userMessage,
                    actionLabelMessage = null,
                    withDismissAction = true,
                    duration = SnackbarDuration.Short,
                    onSnackBarResult = {},
                ),
            )
        }
    }
}
