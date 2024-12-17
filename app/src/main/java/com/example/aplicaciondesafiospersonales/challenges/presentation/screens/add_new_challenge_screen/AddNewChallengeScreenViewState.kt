package com.example.aplicaciondesafiospersonales.challenges.presentation.screens.add_new_challenge_screen

import com.example.aplicaciondesafiospersonales.challenges.domain.model.Challenge

data class AddNewChallengeScreenViewState (
    val isLoading: Boolean = false,
    var challenge: Challenge? = null,
    val error : String? = null
)