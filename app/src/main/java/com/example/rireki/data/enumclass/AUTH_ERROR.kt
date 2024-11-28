package com.example.rireki.data.enumclass

import androidx.annotation.StringRes
import com.example.rireki.R

enum class AUTH_ERROR(
    @StringRes val errorText: Int
) {
    NONE(errorText = R.string.empty),
    NO_INPUT(errorText = R.string.auth_error_no_input),
    BAD_PASSWORD(errorText = R.string.auth_error_bad_password)
}