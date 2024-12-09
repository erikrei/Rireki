package com.example.rireki.data.model

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.rireki.data.dataclass.ProfileList
import com.example.rireki.data.dataclass.UserInformation
import com.example.rireki.data.state.UserUiState
import com.example.rireki.data.util.db.createListInDatabase
import com.example.rireki.data.util.db.removeListInDatabase
import com.example.rireki.data.util.db.updateListInDatabase
import com.example.rireki.data.util.getProfileList
import com.example.rireki.data.util.getProfileListFromId
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import com.google.firebase.firestore.toObjects
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class UserViewModel(
    private val uid: String?,
    private val db: FirebaseFirestore
) : ViewModel() {
    private val _uiState = MutableStateFlow(UserUiState())
    val uiState: StateFlow<UserUiState> = _uiState.asStateFlow()

    fun getUserData(
        setLoadingComplete: () -> Unit
    ) {
        if (uid == null) return
        val documentReference = db.collection("users").document(uid)

        documentReference
            .get()
            .addOnCompleteListener {
                document ->
                    val data = document.result.toObject<UserInformation>()
                    if (data != null) this.setUserInfo(data)
                    this.getListData(
                        uid = uid,
                        db = db,
                        setLoadingComplete = setLoadingComplete
                    )
            }
            .addOnFailureListener {
                Log.e("UserViewModel", it.message.toString())
            }
    }

    private fun getListData(
        uid: String?,
        db: FirebaseFirestore,
        setLoadingComplete: () -> Unit
    ) {
        if (uid == null) return
        val documentReference = db.collection("lists").whereArrayContains(
            "follower", uid
        )

        documentReference
            .get()
            .addOnCompleteListener {
                    document ->
                val data = document.result.toObjects<ProfileList>()
                this.setUserData(data)
                setLoadingComplete()
            }
    }

    fun addList(
        listName: String,
        openSnackbar: () -> Unit,
        onNameNotAvailable: () -> Unit,
        closeCreateDialog: () -> Unit
    ) {
        if (uiState.value.userData.any { it.name == listName }) {
            onNameNotAvailable()
            return
        }

        val newList = getProfileList(
            listName = listName,
            userName = "${uiState.value.userInfo.firstName} ${uiState.value.userInfo.lastName}",
            userId = uid!!
        )

        val newProfileLists = uiState.value.userData.plus(newList)

        createListInDatabase(
            db = db,
            list = newList,
            changeIdOfCreatedList = { this.changeIdOfCreatedList(it) },
            onComplete = {
                this.updateUiLists(newLists = newProfileLists)
                openSnackbar()
                closeCreateDialog()
            }
        )
    }

    fun getListFromId(listId: String): ProfileList {
        return uiState.value.userData.find {
            it.id == listId
        } ?: ProfileList()
    }

    fun removeList(
        listId: String,
        onComplete: () -> Unit,
    ) {
        val newLists = uiState.value.userData.filter {
            it.id != listId
        }

        removeListInDatabase(
            db = db,
            listId = listId,
            onComplete = {
                this.updateUiLists(newLists)
                onComplete()
            }
        )
    }

    private fun changeIdOfCreatedList(createdListId: String) {
        val newLists = uiState.value.userData.map {
            it.copy(
                id = it.id.ifEmpty { createdListId }
            )
        }

        this.updateUiLists(newLists = newLists)
    }

    fun removeProfileFromList(
        listId: String,
        profileName: String,
        unsetDialog: () -> Unit
    ) {
        val list = getProfileListFromId(
            lists = uiState.value.userData,
            listId = listId
        ) ?: return

        val updatedList = list.copy(
            profiles = list.profiles.minus(profileName)
        )

        val newLists = uiState.value.userData.map {
            it.copy(
                profiles = if (it.id == listId) it.profiles.minus(profileName) else it.profiles
            )
        }

        updateListInDatabase(
            db = db,
            list = updatedList,
            onComplete = {
                unsetDialog()
                this.updateUiLists(newLists = newLists)
            }
        )
    }

    fun addProfileToList(
        listId: String,
        profileName: String,
        resetValues: () -> Unit,
        navigateToOverview: () -> Unit,
    ) {
        val list = uiState.value.userData.find {
            it.id == listId
        } ?: return

        val updatedList = list.copy(
            profiles = list.profiles.plus(profileName)
        )

        val newLists = uiState.value.userData.map {
            it.copy(
                profiles = if (it.id == listId) it.profiles.plus(profileName) else it.profiles
            )
        }

        updateListInDatabase(
            db = db,
            list = updatedList,
            onComplete = {
                this.updateUiLists(newLists = newLists)
                resetValues()
                navigateToOverview()
            },
        )
    }

    private fun updateUiLists(newLists: List<ProfileList>) {
        _uiState.update {
            currentState ->
                currentState.copy(
                    userData = newLists
                )
        }
    }

    private fun setUserInfo(userInfo: UserInformation) {
        _uiState.update {
            currentState ->
                currentState.copy(
                    userInfo = userInfo
                )
        }
    }

    private fun setUserData(userData: List<ProfileList>) {
        _uiState.update {
            currentState ->
                currentState.copy(
                    userData = userData
                )
        }
    }
}