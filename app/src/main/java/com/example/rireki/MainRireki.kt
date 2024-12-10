package com.example.rireki

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.rireki.data.model.AddProfileViewModel
import com.example.rireki.data.model.HomeViewModel
import com.example.rireki.data.model.ListSettingsViewModel
import com.example.rireki.data.model.UserViewModel
import com.example.rireki.data.model.UsernameInputViewModel
import com.example.rireki.data.objects.Authentication
import com.example.rireki.data.objects.Home
import com.example.rireki.data.objects.Start
import com.example.rireki.data.objects.UsernameInput
import com.example.rireki.data.objects.homeGraph
import com.example.rireki.data.objects.startGraph
import com.example.rireki.data.objects.usernameInput
import com.example.rireki.ui.screens.AuthenticationScreen
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun MainRireki(
    db: FirebaseFirestore,
    auth: FirebaseAuth,
    homeViewModel: HomeViewModel = viewModel(),
    settingsViewModel: ListSettingsViewModel = viewModel(),
    addProfileViewModel: AddProfileViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val userViewModel = UserViewModel(
        auth = auth,
        db = db
    )

    val onSuccessAuth: () -> Unit = {
        navController.navigate(Start)
    }

    val onFailureLoadUser: () -> Unit = {
        navController.navigate(Authentication)
    }

    val onSuccessLoadUser: () -> Unit = {
        navController.navigate(Home)
    }

    val navigateToUsernameInput: () -> Unit = {
        navController.navigate(UsernameInput)
    }

    NavHost(
        navController = navController,
        startDestination = Start
    ) {
        startGraph(
            auth = auth,
            userViewModel = userViewModel,
            navigateToUsernameInput = navigateToUsernameInput,
            navigateToHome = onSuccessLoadUser,
            navigateAuthentication = onFailureLoadUser
        )
        composable<Authentication> {
            AuthenticationScreen(
                auth = auth,
                onSuccessAuth = onSuccessAuth
            )
        }
        usernameInput(
            usernameInputViewModel = UsernameInputViewModel(),
            onCompleteRegister = {
                firstName, lastName ->
                    userViewModel.registerUserInDBWithNames(
                        firstName = firstName,
                        lastName = lastName,
                        onComplete = onSuccessAuth
                    )
            }
        )
        homeGraph(
            userViewModel = userViewModel,
            homeViewModel = homeViewModel,
            settingsViewModel = settingsViewModel,
            addProfileViewModel = addProfileViewModel,
            navController = navController,
        )
    }
}