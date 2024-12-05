package com.example.rireki.data.enumclass

import androidx.annotation.StringRes
import com.example.rireki.R

enum class START_LOADING_TYPE(
    @StringRes val loadingText: Int,
    val progress: Float
) {
    INIT(
        loadingText = R.string.start_init,
        progress = .25f
    ),
    NOT_LOGGED_IN(
        loadingText = R.string.start_not_logged_in,
        progress = .5f
    ),
    REDIRECT_TO_AUTH(
        loadingText = R.string.start_redirect,
        progress = 1f
    ),
    LOAD(
        loadingText = R.string.start_load,
        progress = .75f
    ),
    COMPLETE(
        loadingText = R.string.start_complete,
        progress = 1f
    )
}