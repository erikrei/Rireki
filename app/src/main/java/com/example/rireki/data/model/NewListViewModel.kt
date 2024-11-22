package com.example.rireki.data.model

import androidx.lifecycle.ViewModel
import com.example.rireki.data.state.NewListUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class NewListViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(NewListUiState())
    val uiState: StateFlow<NewListUiState> = _uiState.asStateFlow()

    fun updateNameInput(nameInput: String) {
        _uiState.update {
            currentState -> currentState.copy(
                name = nameInput
            )
        }
    }

    fun toggleIsOpenDialog() {
        _uiState.update {
            currentState -> currentState.copy(
                isOpenDialog = !uiState.value.isOpenDialog
            )
        }
    }
}