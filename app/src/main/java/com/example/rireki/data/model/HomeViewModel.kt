package com.example.rireki.data.model

import androidx.lifecycle.ViewModel
import com.example.rireki.data.enumclass.LIST_CREATE_ERROR
import com.example.rireki.data.state.HomeUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HomeViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    fun setSelectedList(selectedId: String) {
        _uiState.update { currentState ->
            currentState.copy(
                selectedListId = selectedId
            )
        }
    }

    fun setErrorMessage(error: Int) {
        val errorEnum: LIST_CREATE_ERROR = when(error) {
            1 -> LIST_CREATE_ERROR.NAME_NOT_AVAILABLE
            else -> LIST_CREATE_ERROR.NONE
        }

        _uiState.update {
            currentState ->
                currentState.copy(
                    error = errorEnum
                )
        }
    }

    fun updateNameInput(nameInput: String) {
        _uiState.update {
                currentState -> currentState.copy(
                    newListName = nameInput,
                    error = LIST_CREATE_ERROR.NONE
                )
        }
    }

    fun toggleIsOpenDialog() {
        _uiState.update {
                currentState -> currentState.copy(
                    isOpenDialog = !uiState.value.isOpenDialog,
                    newListName = ""
                )
        }
    }
}