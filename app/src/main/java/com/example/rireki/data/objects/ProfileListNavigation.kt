package com.example.rireki.data.objects

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.toRoute
import com.example.rireki.data.model.AddProfileViewModel
import com.example.rireki.data.model.HomeViewModel
import com.example.rireki.data.model.ListSettingsViewModel
import com.example.rireki.data.model.UserViewModel
import com.example.rireki.ui.screens.ListAddScreen
import com.example.rireki.ui.screens.ListOverviewScreen
import com.example.rireki.ui.screens.ListSettingsScreen
import com.example.rireki.ui.screens.ProfileViewScreen
import kotlinx.serialization.Serializable

@Serializable
object ActiveProfileList

@Serializable
object AddProfile

@Serializable
object Settings

@Serializable
data class ProfileView(
    val listId: String,
    val name: String
)

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

    val onProfileClick: (String, String) -> Unit = {
        listId, profileName ->
            navController.navigate(ProfileView(
                listId = listId,
                name = profileName
            ))
    }

    navigation<ProfileListGraph>(
        startDestination = ActiveProfileList
    ) {
        composable<ActiveProfileList> {
            ListOverviewScreen(
                homeViewModel = homeViewModel,
                userViewModel = userViewModel,
                onProfileClick = onProfileClick,
                onNavigateBack = navigateBackHome,
                onNavigateSettings = onNavigateSettings,
                onNavigateAdd = onNavigateAdd,
            )
        }
        composable<ProfileView> {
            val profileToView = it.toRoute<ProfileView>()

            ProfileViewScreen(
                userViewModel = userViewModel,
                profileToView = profileToView,
                onNavigateBack = navigateBackList
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
                        listId, profile ->
                            userViewModel.addProfileToList(
                                listId = listId,
                                profile = profile,
                                resetValues = { addProfileViewModel.resetProfileInputs() },
                                navigateToOverview = navigateBackList
                            )
                },
                onNavigationBack = navigateBackList
            )
        }
    }
}