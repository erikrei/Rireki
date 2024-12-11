package com.example.rireki.data.model

import androidx.lifecycle.ViewModel
import com.example.rireki.data.state.AddProfileUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AddProfileViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(AddProfileUiState())
    val uiState: StateFlow<AddProfileUiState> = _uiState.asStateFlow()

    fun changeProfileName(nameInput: String) {
        _uiState.update {
            currentState ->
                currentState.copy(
                    name = nameInput
                )
        }
    }

    fun changeProfileResidence(residenceInput: String) {
        _uiState.update {
            currentState ->
                currentState.copy(
                    residency = residenceInput
                )
        }
    }

    fun changeProfileAge(ageInput: String) {
        _uiState.update {
                currentState ->
                    currentState.copy(
                        age = ageInput
                    )
        }
    }

    fun changeProfileDescription(descriptionInput: String) {
        _uiState.update {
            currentState ->
                currentState.copy(
                    description = descriptionInput
                )
        }
    }

    fun resetProfileInputs() {
        _uiState.update {
            currentState ->
                currentState.copy(
                    name = "",
                    residency = "",
                    age = "",
                    description = ""
                )
        }
    }
}