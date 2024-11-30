package com.example.rireki.data.objects

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.rireki.data.model.AddProfileViewModel
import com.example.rireki.data.model.HomeViewModel
import com.example.rireki.data.model.ListSettingsViewModel
import com.example.rireki.ui.screens.HomeScreen
import com.example.rireki.ui.screens.ListAddScreen
import com.example.rireki.ui.screens.ListOverviewScreen
import com.example.rireki.ui.screens.ListSettingsScreen
import kotlinx.serialization.Serializable

@Serializable
object HomeGraph

@Serializable
object Home

@Serializable
data class ProfileListGraph(
    val id: String
)

@Serializable
object ActiveProfileList

@Serializable
object AddProfile

@Serializable
object Settings

fun NavGraphBuilder.homeGraph(
    homeViewModel: HomeViewModel,
    settingsViewModel: ListSettingsViewModel,
    addProfileViewModel: AddProfileViewModel,
    navController: NavHostController
) {
    val navigateToList: (String) -> Unit = {
        navController.navigate(
            ProfileListGraph(
                id = it
            )
        )
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
                navigateToList = navigateToList
            )
        }
        profileListGraph(
            homeViewModel = homeViewModel,
            settingsViewModel = settingsViewModel,
            addProfileViewModel = addProfileViewModel,
            navController = navController
        )
    }
}

fun NavGraphBuilder.profileListGraph(
    homeViewModel: HomeViewModel,
    settingsViewModel: ListSettingsViewModel,
    addProfileViewModel: AddProfileViewModel,
    navController: NavHostController
) {
    val navigateBackHome: () -> Unit = {
        navController.navigate(Home)
    }

    val navigateBackList: () -> Unit = {
        navController.navigate(ActiveProfileList)
    }

    val onNavigateSettings: () -> Unit = {
        navController.navigate(Settings)
    }

    val onNavigateAdd: () -> Unit = {
        navController.navigate(AddProfile)
    }

    navigation<ProfileListGraph>(
        startDestination = ActiveProfileList
    ) {
        composable<ActiveProfileList> {
            val homeUiState by homeViewModel.uiState.collectAsState()

            ListOverviewScreen(
                homeViewModel = homeViewModel,
                homeUiState = homeUiState,
                onNavigateBack = navigateBackHome,
                onNavigateSettings = onNavigateSettings,
                onNavigateAdd = onNavigateAdd,
            )
        }
        composable<Settings> {
            val homeUiState by homeViewModel.uiState.collectAsState()

            settingsViewModel.setListSettings(
                homeViewModel.getListOfId(homeUiState.selectedListId)
            )

            val settingsCopy = settingsViewModel.uiState.value.copy()

            ListSettingsScreen(
                settingsViewModel = settingsViewModel,
                settingsCopy = settingsCopy,
                onNavigateBack = navigateBackList,
                onListDelete = {
                    settingsViewModel.unshowListDeleteDialog()
                    homeViewModel.removeList(it)
                    navController.navigate(Home) {
                        popUpTo(navController.graph.id)
                    }
                },
                modifier = Modifier
            )
        }
        composable<AddProfile> {
            ListAddScreen(
                addProfileViewModel = addProfileViewModel,
                onAddClick = {
                    name, residence ->
                        homeViewModel.addProfileToList(
                            profileName = name,
                            profileResidence = residence
                        )
                        addProfileViewModel.resetProfileInputs()
                        navigateBackList()
                },
                onNavigationBack = navigateBackList
            )
        }
    }
}