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

    fun refresh(fromPullToRefresh: Boolean) {
        _uiState.update {
            it.copy(
                isRefreshingFromPullToRefresh = fromPullToRefresh,
            )
        }
        getAllGames(fromPullToRefresh = fromPullToRefresh)
        getHotGames(fromPullToRefresh = fromPullToRefresh)
    }

    private fun getAllGames(fromPullToRefresh: Boolean) {
        gameUseCase.getAllGames(forceRefresh = fromPullToRefresh).onEach { result ->
            when (result) {
                is Resource.Error -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            isRefreshingFromPullToRefresh = false,
                            error = result.message,
                        )
                    }
                }

                is Resource.Loading -> {
                    _uiState.update {
                        it.copy(
                            isLoading = true,
                            isRefreshingFromPullToRefresh = fromPullToRefresh,
                        )
                    }
                }

                is Resource.Success -> {
                    _uiState.update {
                        it.copy(
                            games = result.data,
                            isLoading = false,
                            isRefreshingFromPullToRefresh = false,
                            error = null,
                        )
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getHotGames(fromPullToRefresh: Boolean) {
        gameUseCase.getHotGames(forceRefresh = fromPullToRefresh).onEach { result ->
            when (result) {
                is Resource.Error -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            isRefreshingFromPullToRefresh = false,
                            error = result.message,
                        )
                    }
                }

                is Resource.Loading -> {
                    _uiState.update {
                        it.copy(
                            isRefreshingFromPullToRefresh = fromPullToRefresh,
                            isLoading = true,
                        )
                    }
                }

                is Resource.Success -> {
                    _uiState.update {
                        it.copy(
                            hotGames = result.data,
                            isRefreshingFromPullToRefresh = false,
                            isLoading = false,
                            error = null,
                        )
                    }
                }
            }
        }.launchIn(viewModelScope)
    }
}
