package com.mlefrapper.androidstarterkit

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

    private fun showMessage(
        @StringRes snackBarMessageResId: Int,
    ) = _uiState.update {
        it.copy(
            snackBarMessage = SnackBarMessage.from(
                userMessage = UserMessage.from(
                    resId = snackBarMessageResId,
                ),
                actionLabelMessage = null,
                withDismissAction = true,
                duration = SnackbarDuration.Short,
                onSnackBarResult = {},
            ),
        )
    }
}
