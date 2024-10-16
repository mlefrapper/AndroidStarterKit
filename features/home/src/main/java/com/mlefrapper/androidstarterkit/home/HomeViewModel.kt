package com.mlefrapper.androidstarterkit.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mlefrapper.androidstarterkit.data.domain.usecase.GameUseCase
import com.mlefrapper.androidstarterkit.data.vo.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val gameUseCase: GameUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeScreenState())
    val uiState = _uiState.asStateFlow()

    fun onInit() {
        getAllGames()
        getHotGames()
    }

    private fun getAllGames() {
        gameUseCase.getAllGames().onEach { result ->
            when (result) {
                is Resource.Error -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            error = result.message,
                        )
                    }
                }

                is Resource.Loading -> {
                    _uiState.update {
                        it.copy(
                            isLoading = true,
                        )
                    }
                }

                is Resource.Success -> {
                    _uiState.update {
                        it.copy(
                            games = result.data,
                            isLoading = false,
                            error = null,
                        )
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getHotGames() {
        gameUseCase.getHotGames().onEach { result ->
            when (result) {
                is Resource.Error -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            error = result.message,
                        )
                    }
                }

                is Resource.Loading -> {
                    _uiState.update {
                        it.copy(
                            isLoading = true,
                        )
                    }
                }

                is Resource.Success -> {
                    _uiState.update {
                        it.copy(
                            hotGames = result.data,
                            isLoading = false,
                            error = null,
                        )
                    }
                }
            }
        }.launchIn(viewModelScope)
    }
}
