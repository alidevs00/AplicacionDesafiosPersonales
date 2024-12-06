package com.example.aplicaciondesafiospersonales.challenges.presentation.home_screen

import com.example.aplicaciondesafiospersonales.challenges.domain.model.Challenge

data class HomeScreenViewState (
    val isLoading: Boolean = false,
    val challenges: List<Challenge> = emptyList(),
    val error : String? = null
)