package com.example.rireki

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.rireki.data.objects.Authentication
import com.example.rireki.ui.screens.AuthenticationScreen

@Composable
fun MainRireki(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Authentication
    ) {
        composable<Authentication> {
            AuthenticationScreen()
        }
    }
}