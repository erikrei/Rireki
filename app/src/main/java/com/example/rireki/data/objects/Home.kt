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
import com.example.rireki.data.model.UserViewModel
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
object ProfileListGraph

@Serializable
object ActiveProfileList

@Serializable
object AddProfile

@Serializable
object Settings

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

fun NavGraphBuilder.profileListGraph(
    homeViewModel: HomeViewModel,
    userViewModel: UserViewModel,
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
            ListOverviewScreen(
                homeViewModel = homeViewModel,
                userViewModel = userViewModel,
                onNavigateBack = navigateBackHome,
                onNavigateSettings = onNavigateSettings,
                onNavigateAdd = onNavigateAdd,
            )
        }
        composable<Settings> {
            val homeUiState by homeViewModel.uiState.collectAsState()

            val selectedList = userViewModel.getListFromId(homeUiState.selectedListId)

            settingsViewModel.setListSettings(selectedList)

            ListSettingsScreen(
                settingsViewModel = settingsViewModel,
                selectedList = selectedList,
                onNavigateBack = navigateBackList,
                onListDelete = {
                    userViewModel.removeList(listId = it) {
                        settingsViewModel.unshowListDeleteDialog()
                        navController.navigate(Home) {
                            popUpTo(navController.graph.id)
                        }
                    }
                },
                modifier = Modifier
            )
        }
        composable<AddProfile> {
            ListAddScreen(
                addProfileViewModel = addProfileViewModel,
                homeViewModel = homeViewModel,
                onAddClick = {
                    listId, profileName ->
                        userViewModel.addProfileToList(
                            listId = listId,
                            profileName = profileName,
                            resetValues = { addProfileViewModel.resetProfileInputs() },
                            navigateToOverview = navigateBackList
                        )
                },
                onNavigationBack = navigateBackList
            )
        }
    }
}