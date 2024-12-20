package com.example.rireki.data.objects

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.rireki.data.model.UserViewModel
import com.example.rireki.ui.screens.StartScreen
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.serialization.Serializable

@Serializable
object Start

fun NavGraphBuilder.startGraph(
    auth: FirebaseAuth,
    userViewModel: UserViewModel,
    navigateToUsernameInput: () -> Unit,
    navigateToHome: () -> Unit,
    navigateAuthentication: () -> Unit
) {
    composable<Start> {
        StartScreen(
            auth = auth,
            navigateToUsernameInput = navigateToUsernameInput,
            navigateToHome = navigateToHome,
            navigateAuthentication = navigateAuthentication,
            userViewModel = userViewModel,
        )
    }
}