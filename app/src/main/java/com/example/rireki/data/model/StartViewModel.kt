package com.example.rireki.data.model

import androidx.lifecycle.ViewModel
import com.example.rireki.data.enumclass.START_LOADING_TYPE
import com.example.rireki.data.state.StartUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class StartViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(StartUiState())
    val uiState: StateFlow<StartUiState> = _uiState.asStateFlow()

    suspend fun initProgress(
        isLoggedIn: Boolean,
        navigateToHome: () -> Unit,
        navigateAuthentication: () -> Unit,
        loadData: () -> Unit
    ) {
        delay(1000)
        if (isLoggedIn) {
            setProgress(START_LOADING_TYPE.LOAD)
            delay(1000)
            loadData()
            delay(1000)
            navigateToHome()
        } else {
            setProgress(START_LOADING_TYPE.NOT_LOGGED_IN)
            delay(1000)
            setProgress(START_LOADING_TYPE.REDIRECT_TO_AUTH)
            delay(1000)
            navigateAuthentication()
        }
    }

    fun setProgress(
        loadingType: START_LOADING_TYPE
    ) {
        _uiState.update {
            currentState ->
                currentState.copy(
                    loadingType = loadingType
                )
        }
    }
}