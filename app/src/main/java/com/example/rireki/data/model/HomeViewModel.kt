package com.example.rireki.data.model

import androidx.lifecycle.ViewModel
import com.example.rireki.data.dummyLists
import com.example.rireki.data.state.HomeUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HomeViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        _uiState.update {
            currentState -> currentState.copy(
                lists = dummyLists
            )
        }
    }


}