package com.example.aplicaciondesafiospersonales.challenges.presentation.screens.home_screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.aplicaciondesafiospersonales.R
import com.example.aplicaciondesafiospersonales.challenges.domain.model.Challenge
import com.example.aplicaciondesafiospersonales.challenges.presentation.util.components.AddNewChallengeComponent
import com.example.aplicaciondesafiospersonales.challenges.presentation.util.components.ChallengeComponent
import com.example.aplicaciondesafiospersonales.challenges.presentation.util.components.ChallengeDetailComponent
import com.example.aplicaciondesafiospersonales.challenges.presentation.util.components.LoadingDialog

@RequiresApi(Build.VERSION_CODES.O)
@Composable
internal fun HomeScreen(
    viewModel: HomeScreenViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val selectedChallenge = remember { mutableStateOf<Challenge?>(null) }
    val showDialog = remember { mutableStateOf(false) }

    LaunchedEffect(key1 = selectedChallenge.value) {
        showDialog.value = selectedChallenge.value != null
    }

    LaunchedEffect(key1 = navController) {
        navController.currentBackStackEntry?.let { backStackEntry ->
            if (backStackEntry.destination.route == "challenge_list") {
                viewModel.getChallenges()
            }
        }
    }

    HomeScreenContent(
        state = state,
        navController = navController,
        selectedChallenge = selectedChallenge,
        showDialog = showDialog,
        viewModel = viewModel
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreenContent(
    state: HomeScreenViewState,
    navController: NavHostController,
    selectedChallenge: MutableState<Challenge?>,
    showDialog: MutableState<Boolean>,
    viewModel: HomeScreenViewModel
) {
    Column(modifier = Modifier.padding(20.dp)) {
        LoadingDialog(isLoading = state.isLoading)
        Spacer(modifier = Modifier.size(20.dp))
        Text(
            style = MaterialTheme.typography.titleLarge,
            text = stringResource(R.string.mis_desafios)
        )
        Spacer(modifier = Modifier.size(20.dp))
        LazyVerticalStaggeredGrid(
            modifier = Modifier
                .fillMaxSize(),
            columns = StaggeredGridCells.Fixed(3),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalItemSpacing = 20.dp
        ) {
            items(state.challenges) { challenge ->
                ChallengeComponent(
                    title = challenge.title,
                    category = challenge.category,
                    progress = challenge.progress,
                    onClick = { selectedChallenge.value = challenge }
                )
            }

            item {
                AddNewChallengeComponent(onClick = {
                    navController.navigate("add_new_challenge")
                })
            }
        }
        if (showDialog.value && selectedChallenge.value != null) {
            ChallengeDetailComponent(
                challenge = selectedChallenge.value!!,
                onDismiss = {
                    showDialog.value = false
                    selectedChallenge.value = null
                },
                viewModel = viewModel
            )
        }
    }
}

