package com.example.rireki.data.state

import com.example.rireki.data.enumclass.START_LOADING_TYPE

data class StartUiState(
    val loadingType: START_LOADING_TYPE = START_LOADING_TYPE.INIT
)
