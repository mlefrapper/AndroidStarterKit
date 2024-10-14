package com.mlefrapper.androidstarterkit.presentation.bookmark

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class BookmarkViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(BookmarkScreenState())
    val uiState = _uiState.asStateFlow()
}
