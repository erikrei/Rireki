package com.example.rireki.data.model

import androidx.lifecycle.ViewModel
import com.example.rireki.data.Profile
import com.example.rireki.data.ProfileList
import com.example.rireki.data.state.HomeUiState
import com.example.rireki.data.state.ListSettingsUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.time.LocalDate


val profiles: List<List<Profile>> = listOf(
    listOf(
        Profile(
            name = "Erik"
        ),
        Profile(
            name = "Julia"
        ),
        Profile(
            name = "Teresa"
        ),
        Profile(
            name = "Pia"
        ),
        Profile(
            name = "Leonie"
        ),
        Profile(
            name = "Gil"
        ),
        Profile(
            name = "Melanie"
        ),
        Profile(
            name = "Viktor"
        ),
        Profile(
            name = "Fabian"
        ),
        Profile(
            name = "Lara"
        ),
        Profile(
            name = "Fabiano"
        )
    ),
    listOf(
        Profile(
            name = "Rosa"
        ),
        Profile(
            name = "Georg"
        )
    )
)

val settings: List<ListSettingsUiState> = listOf(
    ListSettingsUiState(
        listId = "1",
        listName = "Jana's Bekanntschaft",
        admins = listOf("531413", "312513", "123125")
    ),
    ListSettingsUiState(
        listId = "2",
        listName = "Erik's Verwandschaft",
        admins = listOf("23151")
    )
)

val dummyLists: List<ProfileList> = listOf(
    ProfileList(id = "1", name = "Jana's Bekanntschaft", createdAt = LocalDate.of(2024, 11, 19), createdFrom = "Jana", profiles = profiles[0], settings = settings[0]),
    ProfileList(id = "2", name = "Erik's Verwandschaft", createdAt = LocalDate.of(2024, 7, 8), createdFrom = "Erik", profiles = profiles[1], settings = settings[1]),
)


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

    fun addList(newList: ProfileList) {
        val nameAvailable = uiState.value.lists.none {
            it.name == newList.name
        }

        if (nameAvailable) {
            _uiState.update {
                    currentState -> currentState.copy(
                        lists = uiState.value.lists.plus(newList),
                        newListName = ""
                    )
            }
            this.toggleIsOpenDialog()
        }
    }

    fun updateNameInput(nameInput: String) {
        _uiState.update {
                currentState -> currentState.copy(
                    newListName = nameInput
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