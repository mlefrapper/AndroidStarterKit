package com.mlefrapper.androidstarterkit.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mlefrapper.androidstarterkit.data.domain.usecase.GameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val gameUseCase: GameUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(BookmarkScreenState())
    val uiState = _uiState.asStateFlow()

    fun onInit() {
        gameUseCase.getAllBookmarkedGames().onEach {
            _uiState.value = _uiState.value.copy(
                games = it,
            )
        }.launchIn(viewModelScope)
    }
}
