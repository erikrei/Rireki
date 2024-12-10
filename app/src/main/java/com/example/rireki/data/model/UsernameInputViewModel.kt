package com.example.rireki.data.model

import androidx.lifecycle.ViewModel
import com.example.rireki.data.state.UsernameInputUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class UsernameInputViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(UsernameInputUiState())
    val uiState: StateFlow<UsernameInputUiState> = _uiState.asStateFlow()

    fun changeFirstName(firstNameInput: String) {
        _uiState.update { it.copy(firstName = firstNameInput) }
    }

    fun changeLastName(lastNameInput: String) {
        _uiState.update { it.copy(lastName = lastNameInput) }
    }
}