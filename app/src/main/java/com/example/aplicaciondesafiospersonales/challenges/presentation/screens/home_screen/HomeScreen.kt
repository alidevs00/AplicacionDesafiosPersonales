package com.example.aplicaciondesafiospersonales.challenges.presentation.screens.home_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.aplicaciondesafiospersonales.challenges.presentation.util.components.AddNewChallengeComponent
import com.example.aplicaciondesafiospersonales.challenges.presentation.util.components.ChallengeComponent
import com.example.aplicaciondesafiospersonales.challenges.presentation.util.components.LoadingDialog
import com.example.aplicaciondesafiospersonales.challenges.presentation.util.components.MyTopAppBar

@Composable
internal fun HomeScreen(
    viewModel: HomeScreenViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    LaunchedEffect(key1 = navController) {
        navController.currentBackStackEntry?.let { backStackEntry ->
            if (backStackEntry.destination.route == "challenge_list") {
                viewModel.getChallenges()
            }
        }
    }
    HomeScreenContent(state = state, navController = navController)
}

@Composable
fun HomeScreenContent(
    state: HomeScreenViewState,
    navController: NavHostController
) {
    LoadingDialog(isLoading = state.isLoading)

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            MyTopAppBar(title = "Desafíos")
        }
    ) { paddingValues ->
        LazyVerticalStaggeredGrid(
            modifier = Modifier
                .padding(top = paddingValues.calculateTopPadding())
                .fillMaxSize(),
            columns = StaggeredGridCells.Fixed(3),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalItemSpacing = 40.dp
        ) {
            items(state.challenges) { challenge ->
                ChallengeComponent(
                    title = challenge.title,
                    category = challenge.category,
                    onClick = { navController.navigate("view_challenge") }
                )
            }

            item {
                AddNewChallengeComponent(onClick = {
                    navController.navigate("add_new_challenge")
                })
            }
        }
    }
}
