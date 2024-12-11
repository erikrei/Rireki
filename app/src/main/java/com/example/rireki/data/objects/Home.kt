package com.example.rireki.data.objects

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.rireki.data.model.AddProfileViewModel
import com.example.rireki.data.model.HomeViewModel
import com.example.rireki.data.model.ListSettingsViewModel
import com.example.rireki.data.model.UserViewModel
import com.example.rireki.ui.screens.HomeScreen
import kotlinx.serialization.Serializable

@Serializable
object HomeGraph

@Serializable
object Home

@Serializable
object ProfileListGraph

fun NavGraphBuilder.homeGraph(
    homeViewModel: HomeViewModel,
    userViewModel: UserViewModel,
    settingsViewModel: ListSettingsViewModel,
    addProfileViewModel: AddProfileViewModel,
    navController: NavHostController
) {
    val navigateToList: (String) -> Unit = {
        navController.navigate(ProfileListGraph)
        homeViewModel.setSelectedList(
            selectedId = it
        )
    }

    navigation<HomeGraph>(
        startDestination = Home,
    ) {
        composable<Home> {
            HomeScreen(
                homeViewModel = homeViewModel,
                userViewModel = userViewModel,
                navigateToList = navigateToList
            )
        }
        profileListGraph(
            homeViewModel = homeViewModel,
            settingsViewModel = settingsViewModel,
            addProfileViewModel = addProfileViewModel,
            userViewModel = userViewModel,
            navController = navController
        )
    }
}