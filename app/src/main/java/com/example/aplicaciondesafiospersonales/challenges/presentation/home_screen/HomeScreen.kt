package com.example.aplicaciondesafiospersonales.challenges.presentation.home_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.aplicaciondesafiospersonales.challenges.presentation.util.ChallengeComponent
import com.example.aplicaciondesafiospersonales.challenges.presentation.util.LoadingDialog
import com.example.aplicaciondesafiospersonales.challenges.presentation.util.MyTopAppBar

@Composable
internal fun HomeScreen(
    viewModel: HomeScreenViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    HomeScreenContent(state = state)
}

@Composable
fun HomeScreenContent(
    state: HomeScreenViewState
) {
    LoadingDialog(isLoading = state.isLoading)
    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            MyTopAppBar(title = "Desafíos")
        }
    ) {
        LazyVerticalStaggeredGrid(
            modifier = Modifier.padding(top = it.calculateTopPadding()),
            columns = StaggeredGridCells.Fixed(3),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalItemSpacing = 10.dp
        ) {
            items(state.challenges){challenge ->
                ChallengeComponent(challenge.title,challenge.category)
            }
        }
    }
}