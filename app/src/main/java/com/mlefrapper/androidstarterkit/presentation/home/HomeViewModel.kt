package com.mlefrapper.androidstarterkit.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mlefrapper.androidstarterkit.domain.usecase.GetAllGamesUseCase
import com.mlefrapper.androidstarterkit.domain.usecase.GetHotGamesUseCase
import com.mlefrapper.core.vo.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(HomeScreenState())
    val uiState = _uiState.asStateFlow()

    @Inject
    lateinit var getAllGamesUseCase: GetAllGamesUseCase

    @Inject
    lateinit var getHotGamesUseCase: GetHotGamesUseCase

    fun onInit() {
        getAllGames()
        getHotGames()
    }

    private fun getAllGames() {
        getAllGamesUseCase.execute().onEach { result ->
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
        getHotGamesUseCase.execute().onEach { result ->
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
