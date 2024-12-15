package com.example.aplicaciondesafiospersonales.challenges.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.aplicaciondesafiospersonales.challenges.presentation.screens.add_new_challenge_screen.AddNewChallengeScreen
import com.example.aplicaciondesafiospersonales.challenges.presentation.screens.home_screen.HomeScreen

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "challenge_list") {
        composable("challenge_list") {
            HomeScreen(navController = navController)
        }
        composable("add_new_challenge") {
            AddNewChallengeScreen(navController = navController)
        }
    }
}