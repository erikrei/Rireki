package com.example.rireki

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.rireki.data.model.ActiveProfileListViewModel
import com.example.rireki.data.model.HomeViewModel
import com.example.rireki.data.model.ListSettingsViewModel
import com.example.rireki.data.objects.Authentication
import com.example.rireki.data.objects.HomeGraph
import com.example.rireki.data.objects.homeGraph
import com.example.rireki.ui.screens.AuthenticationScreen

@Composable
fun MainRireki(
    homeViewModel: HomeViewModel = viewModel(),
    activeProfileListViewModel: ActiveProfileListViewModel = viewModel(),
    settingsViewModel: ListSettingsViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = HomeGraph
    ) {
        composable<Authentication> {
            AuthenticationScreen()
        }
        homeGraph(
            homeViewModel = homeViewModel,
            activeProfileListViewModel = activeProfileListViewModel,
            settingsViewModel = settingsViewModel,
            navController = navController,
        )
    }
}