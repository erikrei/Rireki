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
import com.example.rireki.data.objects.Authentication
import com.example.rireki.data.objects.Home
import com.example.rireki.data.objects.HomeGraph
import com.example.rireki.data.objects.homeGraph
import com.example.rireki.ui.screens.AuthenticationScreen
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

@Composable
fun MainRireki(
    firebaseUser: FirebaseUser?,
    auth: FirebaseAuth,
    userViewModel: UserViewModel = viewModel(),
    homeViewModel: HomeViewModel = viewModel(),
    settingsViewModel: ListSettingsViewModel = viewModel(),
    addProfileViewModel: AddProfileViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val onSuccessLogin: () -> Unit = {
        navController.navigate(Home)
    }

    NavHost(
        navController = navController,
        startDestination = HomeGraph
    ) {
        composable<Authentication> {
            AuthenticationScreen(
                auth = auth,
                onSuccessLogin = onSuccessLogin
            )
        }
        homeGraph(
            homeViewModel = homeViewModel,
            settingsViewModel = settingsViewModel,
            addProfileViewModel = addProfileViewModel,
            navController = navController,
        )
    }
}