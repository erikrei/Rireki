package com.example.rireki.data.model

import androidx.lifecycle.ViewModel
import com.example.rireki.data.state.UserUiState
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class UserViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(UserUiState())
    val uiState: StateFlow<UserUiState> = _uiState.asStateFlow()

    fun setUser(user: FirebaseUser?) {
        _uiState.update {
            currentState ->
                currentState.copy(
                    user = user
                )
        }
    }
}