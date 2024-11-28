package com.example.rireki.data.objects

import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.toRoute
import com.example.rireki.R
import com.example.rireki.data.model.ActiveProfileListViewModel
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
data class ActiveProfileList(
    val id: String
)

@Serializable
object AddProfile

@Serializable
object Settings

fun NavGraphBuilder.homeGraph(
    homeViewModel: HomeViewModel,
    activeProfileListViewModel: ActiveProfileListViewModel,
    settingsViewModel: ListSettingsViewModel,
    addProfileViewModel: AddProfileViewModel,
    navController: NavHostController
) {
    val navigateToList: (String) -> Unit = {
        navController.navigate(ProfileListGraph(id = it))
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
            activeProfileListViewModel = activeProfileListViewModel,
            homeViewModel = homeViewModel,
            settingsViewModel = settingsViewModel,
            addProfileViewModel = addProfileViewModel,
            navController = navController
        )
    }
}

fun NavGraphBuilder.profileListGraph(
    homeViewModel: HomeViewModel,
    activeProfileListViewModel: ActiveProfileListViewModel,
    settingsViewModel: ListSettingsViewModel,
    addProfileViewModel: AddProfileViewModel,
    navController: NavHostController
) {
    val navigateBackHome: () -> Unit = {
        navController.navigate(Home)
    }

    val navigateBackList: () -> Unit = {
        navController.navigate(ActiveProfileList(
            id = activeProfileListViewModel.uiState.value.profileList.id
        ))
    }

    val onNavigateSettings: () -> Unit = {
        navController.navigate(Settings)
    }

    val onNavigateAdd: () -> Unit = {
        navController.navigate(AddProfile)
    }

    navigation<ProfileListGraph>(
        startDestination = ActiveProfileList(
            id = activeProfileListViewModel.uiState.value.profileList.id
        )
    ) {
        composable<ActiveProfileList> {
            val selectedListId = it.toRoute<ActiveProfileList>().id
            activeProfileListViewModel.setActiveProfileList(homeViewModel.getListOfId(selectedListId))

            ListOverviewScreen(
                activeProfileListViewModel = activeProfileListViewModel,
                selectedList = activeProfileListViewModel.uiState.value.profileList,
                onNavigateBack = navigateBackHome,
                onNavigateSettings = onNavigateSettings,
                onNavigateAdd = onNavigateAdd
            )
        }
        composable<Settings> {
            settingsViewModel.setListSettings(
                activeProfileListViewModel.uiState.value.profileList
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
                    .padding(
                        horizontal = dimensionResource(id = R.dimen.settings_horizontal_padding),
                        vertical = dimensionResource(id = R.dimen.settings_vertical_padding)
                    )
            )
        }
        composable<AddProfile> {
            ListAddScreen(
                addProfileViewModel = addProfileViewModel,
                onAddClick = {
                    name, residence ->
                        addProfileViewModel.resetProfileInputs()
                        navigateBackList()
                },
                onNavigationBack = navigateBackList
            )
        }
    }
}