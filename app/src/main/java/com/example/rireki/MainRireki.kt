package com.example.rireki

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.rireki.data.objects.Authentication
import com.example.rireki.data.objects.Home
import com.example.rireki.ui.screens.AuthenticationScreen
import com.example.rireki.ui.screens.HomeScreen

@Composable
fun MainRireki(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Home
    ) {
        composable<Authentication> {
            AuthenticationScreen()
        }
        composable<Home> {
            HomeScreen()
        }
    }
}