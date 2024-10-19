package com.mlefrapper.androidstarterkit.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mlefrapper.androidstarterkit.data.domain.usecase.GameUseCase
import com.mlefrapper.androidstarterkit.data.vo.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private var gameUseCase: GameUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchScreenState())
    val uiState = _uiState.asStateFlow()

    private var searchJob: Job? = null

    fun onSearchQueryChanged(query: String) {
        _uiState.value = _uiState.value.copy(
            query = query,
        )

        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(500L)
            if (query.isNotEmpty()) {
                searchGames(query)
            } else {
                _uiState.update {
                    it.copy(
                        games = emptyList(),
                        error = null,
                        isLoading = false,
                    )
                }
            }
        }
    }

    fun searchGames(query: String): Job {
        return gameUseCase.searchGames(query).onEach {
            when (it) {
                is Resource.Loading -> {
                    _uiState.value = _uiState.value.copy(
                        isLoading = true,
                    )
                }
                is Resource.Error -> {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = it.message,
                    )
                }
                is Resource.Success -> {
                    _uiState.value = _uiState.value.copy(
                        games = it.data,
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}
