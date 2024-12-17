package com.example.aplicaciondesafiospersonales.challenges.presentation.screens.home_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aplicaciondesafiospersonales.challenges.domain.model.Challenge
import com.example.aplicaciondesafiospersonales.challenges.domain.repository.ChallengesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val challengeRepository: ChallengesRepository
) : ViewModel() {

    private val _state = MutableStateFlow(HomeScreenViewState())
    val state = _state.asStateFlow()

    init {
        getChallenges()
    }

    internal fun getChallenges() {
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }
            challengeRepository.getChallenges()
            _state.update {
                it.copy(challenges = challengeRepository.getChallenges().challenges.calculateProgress())
            }
            _state.update {
                it.copy(isLoading = false)
            }
        }
    }

    private fun List<Challenge>.calculateProgress(): List<Challenge> =
        this.map { challenge ->
            challenge.copy(
                progress =
                if (challenge.amountFulfilled.toFloat() > 0) {
                    val progressCalculated =
                        (challenge.amountFulfilled.toFloat() / challenge.amountToBeFulfilled.toFloat())
                            .coerceIn(
                                0f,
                                1f
                            )
                    progressCalculated
                } else {
                    0f
                }
            )
        }
}