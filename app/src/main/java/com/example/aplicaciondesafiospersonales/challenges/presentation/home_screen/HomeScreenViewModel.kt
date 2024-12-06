package com.example.aplicaciondesafiospersonales.challenges.presentation.home_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aplicaciondesafiospersonales.challenges.domain.repository.ChallengesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val challengeRepository: ChallengesRepository //use case?
) : ViewModel() {

    private val _state = MutableStateFlow(HomeScreenViewState())
    val state = _state.asStateFlow()

    init {
        getChallenges()
    }

    private fun getChallenges() {
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }
            challengeRepository.getChallenges()
            _state.update {
                it.copy(challenges = challengeRepository.getChallenges().challenges)
            }
            _state.update {
                it.copy(isLoading = false)
            }
        }
    }
}