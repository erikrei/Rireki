package com.example.rireki.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.rireki.R
import com.example.rireki.data.enumclass.START_LOADING_TYPE
import com.example.rireki.data.model.StartViewModel
import com.google.firebase.auth.FirebaseAuth

@Composable
fun StartScreen(
    startViewModel: StartViewModel = viewModel(),
    auth: FirebaseAuth,
    navigateToHome: () -> Unit,
    navigateAuthentication: () -> Unit,
    modifier: Modifier = Modifier
) {
    val startUiState by startViewModel.uiState.collectAsState()
    val currentUser = auth.currentUser

    LaunchedEffect(currentUser) {
        Log.i("StartScreen", "LOADING LAUNCHED EFFECT...")
        startViewModel.initProgress(
            isLoggedIn = currentUser != null,
            navigateToHome = navigateToHome,
            navigateAuthentication = navigateAuthentication
        )
    }

    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(
                dimensionResource(id = R.dimen.start_component_spacing)
            ),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .align(Alignment.Center)
        ) {
            StartScreenLogo()
            StartScreenProgressBar(
                loadingType = startUiState.loadingType
            )
            StartScreenInformationText(
                loadingType = startUiState.loadingType
            )
        }
    }
}

@Composable
fun StartScreenLogo(
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(id = R.drawable.logo_text),
        contentDescription = null,
        colorFilter = ColorFilter.tint(
            color = MaterialTheme.colorScheme.primary
        ),
        modifier = modifier
            .size(160.dp, 90.dp)
    )
}

@Composable
fun StartScreenProgressBar(
    loadingType: START_LOADING_TYPE,
    modifier: Modifier = Modifier
) {
    val progress = loadingType.progress

    LinearProgressIndicator(
        progress = progress,
        modifier = modifier
    )
}

@Composable
fun StartScreenInformationText(
    loadingType: START_LOADING_TYPE,
    modifier: Modifier = Modifier
) {
    val loadingText = stringResource(id = loadingType.loadingText)

    Text(
        text = "$loadingText ...",
        color = MaterialTheme.colorScheme.primary,
        modifier = modifier
    )
}