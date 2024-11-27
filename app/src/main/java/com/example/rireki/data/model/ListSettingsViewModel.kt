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

    fun changeAdminAdd(adminInput: String) {
        _uiState.update {
            currentState ->
                currentState.copy(
                    adminAdd = adminInput
                )
        }
    }

    fun showAdminAddBottomSheet() {
        _uiState.update {
            currentState ->
                currentState.copy(
                    expandedAdminAddBottomSheet = true
                )
        }
    }

    fun unshowAdminAddBottomSheet() {
        _uiState.update {
            currentState ->
                currentState.copy(
                    expandedAdminAddBottomSheet = false
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

    fun deleteAdminFromList() {
        _uiState.update {
            currentState ->
                currentState.copy(
                    admins = uiState.value.admins.minus(uiState.value.adminDeleteSelected),
                )
        }
        this.unshowAdminRemoveDialog()
    }

    fun showAdminRemoveDialog(admin: String) {
        _uiState.update {
            currentState ->
                currentState.copy(
                    expandedAdminRemove = true,
                    adminDeleteSelected = admin
                )
        }
    }

    fun unshowAdminRemoveDialog() {
        _uiState.update {
            currentState ->
                currentState.copy(
                    expandedAdminRemove = false,
                    adminDeleteSelected = ""
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

    fun showDropdown() {
        _uiState.update {
            currentState ->
                currentState.copy(
                    expandedDropdown = true
                )
        }
    }

    fun unshowDropdown() {
        _uiState.update {
            currentState ->
                currentState.copy(
                    expandedDropdown = false
                )
        }
    }
}