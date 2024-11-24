package com.example.rireki.data.objects

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.toRoute
import com.example.rireki.data.model.HomeViewModel
import com.example.rireki.ui.screens.HomeScreen
import com.example.rireki.ui.screens.ListOverviewScreen
import kotlinx.serialization.Serializable

@Serializable
object HomeGraph

@Serializable
object Home

@Serializable
data class ProfileListGraph(
    val id: String
)

fun NavGraphBuilder.homeGraph(
    homeViewModel: HomeViewModel,
    navController: NavHostController
) {
    val navigateToList: (String) -> Unit = {
        navController.navigate(ProfileListGraph(id = it))
    }

    val navigateBack: () -> Unit = {
        navController.navigate(Home)
    }

    navigation<HomeGraph>(
        startDestination = Home
    ) {
        composable<Home> {
            HomeScreen(
                homeViewModel = homeViewModel,
                navigateToList = navigateToList
            )
        }
        composable<ProfileListGraph> {
            val profileListId = it.toRoute<ProfileListGraph>().id
            val selectedList = homeViewModel.getListOfId(profileListId)

            ListOverviewScreen(
                selectedList = selectedList,
                onNavigateBack = navigateBack
            )
        }
    }
}
