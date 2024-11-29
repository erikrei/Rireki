package com.example.rireki.data.model

import androidx.lifecycle.ViewModel
import com.example.rireki.data.dataclass.ProfileList
import com.example.rireki.data.dummyLists
import com.example.rireki.data.enumclass.LIST_CREATE_ERROR
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

    fun setSelectedList(selectedId: String) {
        _uiState.update {
            currentState ->
                currentState.copy(
                    selectedListId = selectedId
                )
        }
    }

    fun setProfileToRemoveFromList(listId: String, profileName: String) {
        val newProfileLists = uiState.value.lists.map {
            it.copy(
                showRemoveProfile = listId == it.id,
                removeProfile = if (it.id == listId) profileName else it.removeProfile
            )
        }

        this.updateLists(newProfileLists)
    }

    fun unsetShowProfileRemove() {
        val newProfileLists = uiState.value.lists.map {
            it.copy(
                showRemoveProfile = false,
                removeProfile = ""
            )
        }

        this.updateLists(newProfileLists)
    }

    private fun updateLists(newLists: List<ProfileList>) {
        _uiState.update {
            currentState ->
                currentState.copy(
                    lists = newLists
                )
        }
    }

    fun removeProfileFromList(listId: String, profileName: String) {
        val newProfilesOfList = uiState.value.lists.map {
            val newSelectedProfiles = it.profiles.filter { profile -> profile.name != profileName }
            it.copy(
                profiles = if (it.id == listId) newSelectedProfiles else it.profiles,
                showRemoveProfile = false,
                removeProfile = ""
            )
        }

        this.updateLists(newProfilesOfList)
    }

    private fun setErrorMessage(error: Int) {
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

    fun removeList(listId: String) {
        _uiState.update {
            currentState ->
                currentState.copy(
                    lists = uiState.value.lists.filter {
                        it.id != listId
                    }
                )
        }
    }

    fun getListOfId(id: String): ProfileList {
        return uiState.value.lists.find {
            it.id == id
        } ?: ProfileList()
    }

    fun addList(newList: ProfileList, openSnackbar: () -> Unit) {
        val nameAvailable = uiState.value.lists.none {
            it.name == newList.name
        }

        if (!nameAvailable) {
            this.setErrorMessage(1)
            return
        }

        _uiState.update {
                currentState -> currentState.copy(
                    lists = uiState.value.lists.plus(newList),
                    newListName = ""
                )
        }
        this.toggleIsOpenDialog()
        openSnackbar()
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
                    isOpenDialog = !uiState.value.isOpenDialog
                )
        }
    }
}