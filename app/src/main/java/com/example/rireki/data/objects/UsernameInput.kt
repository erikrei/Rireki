package com.example.rireki.data.objects

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.rireki.data.model.UsernameInputViewModel
import com.example.rireki.ui.screens.UsernameInputScreen
import kotlinx.serialization.Serializable

@Serializable
object UsernameInput

fun NavGraphBuilder.usernameInput(
    usernameInputViewModel: UsernameInputViewModel,
    onCompleteRegister: (String, String) -> Unit
) {
    composable<UsernameInput> {
        UsernameInputScreen(
            onCompleteRegister = onCompleteRegister,
            usernameInputViewModel = usernameInputViewModel
        )
    }
}