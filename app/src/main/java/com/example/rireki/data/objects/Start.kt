package com.example.rireki.data.objects

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.rireki.ui.screens.StartScreen
import com.google.firebase.auth.FirebaseAuth
import kotlinx.serialization.Serializable

@Serializable
object Start

fun NavGraphBuilder.startGraph(
    auth: FirebaseAuth,
    navigateToHome: () -> Unit,
    navigateAuthentication: () -> Unit
) {
    composable<Start> {
        StartScreen(
            auth = auth,
            navigateToHome = navigateToHome,
            navigateAuthentication = navigateAuthentication
        )
    }
}