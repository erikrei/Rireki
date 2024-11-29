package com.example.rireki.data.model

import androidx.lifecycle.ViewModel
import com.example.rireki.data.dataclass.ProfileList
import com.example.rireki.data.state.ListSettingsUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ListSettingsViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ListSettingsUiState())
    val uiState: StateFlow<ListSettingsUiState> = _uiState.asStateFlow()

    fun showPrivacyDialog() {
        _uiState.update {
            currentState ->
                currentState.copy(
                    expandedDropdown = true
                )
        }
    }

    fun unshowPrivacyDialog() {
        _uiState.update {
            currentState ->
                currentState.copy(
                    expandedDropdown = false
                )
        }
    }

    fun showListDeleteDialog() {
        _uiState.update {
            currentState ->
                currentState.copy(
                    expandedListDelete = true
                )
        }
    }

    fun unshowListDeleteDialog() {
        _uiState.update {
            currentState ->
                currentState.copy(
                    expandedListDelete = false
                )
        }
    }

    fun setListSettings(list: ProfileList) {
        _uiState.update {
            currentState ->
                currentState.copy(
                    listId = list.id,
                    listName = list.name,
                    listPrivacy = list.settings.listPrivacy,
                    admins = list.settings.admins
                )
        }
    }

    fun changeListName(listNameInput: String) {
        _uiState.update {
            currentState ->
                currentState.copy(
                    listName = listNameInput
                )
        }
    }

    fun setListPrivacy(privacy: String) {
        _uiState.update {
            currentState ->
                currentState.copy(
                    listPrivacy = privacy,
                    expandedDropdown = false,
                )
        }
    }
}