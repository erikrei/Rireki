package com.example.rireki.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.rireki.R
import com.example.rireki.data.model.UserViewModel
import com.example.rireki.data.objects.ProfileView
import com.example.rireki.ui.components.ProfileViewContainer
import com.example.rireki.ui.components.ProfileViewNotFound
import com.example.rireki.ui.components.ProfileViewTopBar

@Composable
fun ProfileViewScreen(
    userViewModel: UserViewModel = viewModel(),
    profileToView: ProfileView,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val userUiState by userViewModel.uiState.collectAsState()
    val profileList = userUiState.userData.find { it.id == profileToView.listId }
    val profile = profileList?.profiles?.find { it.name == profileToView.name }

    Scaffold(
        topBar = { ProfileViewTopBar(
            profileName = profile?.name ?: stringResource(id = R.string.profile_view_profile_not_found_title),
            onNavigateBack = onNavigateBack
        ) },
        modifier = modifier
    ) { paddingValues ->
        val profileViewModifier = Modifier.padding(paddingValues)
        if (profile != null)
            ProfileViewContainer(
                profile = profile,
                modifier = profileViewModifier
            )
        else ProfileViewNotFound(modifier = profileViewModifier)
    }
}