package com.example.aplicaciondesafiospersonales.challenges.presentation.screens.home_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aplicaciondesafiospersonales.challenges.domain.model.ApiError
import com.example.aplicaciondesafiospersonales.challenges.domain.model.Challenge
import com.example.aplicaciondesafiospersonales.challenges.domain.model.ChallengeUpdate
import com.example.aplicaciondesafiospersonales.challenges.domain.repository.ChallengesRepository
import com.example.aplicaciondesafiospersonales.util.Event
import com.example.aplicaciondesafiospersonales.util.EventBus
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
                .onRight { result ->
                    _state.update {
                        it.copy(challenges = result.challenges.calculateProgress())
                    }
                }
                .onLeft { error ->
                    when (error.error) {
                        ApiError.NetworkError -> EventBus.sendEvent(Event.Toast("Error de conexión cargando la base de datos"))
                        ApiError.UnknownError -> EventBus.sendEvent(Event.Toast("Error cargando la base de datos"))
                    }
                }

            _state.update {
                it.copy(isLoading = false)
            }
        }
    }

    private fun List<Challenge>.calculateProgress(): List<Challenge> =
        this.map { challenge ->
            challenge.copy(
                progress = (challenge.amountFulfilled / challenge.amountToBeFulfilled.toFloat()).coerceIn(0f, 1f)
            )
        }

    internal fun updateChallenge(id: String, fieldsToUpdate: ChallengeUpdate) {
        viewModelScope.launch {
            challengeRepository.updateChallenge(id, fieldsToUpdate)

            val updatedChallenges = _state.value.challenges.map { challenge ->
                if (challenge.id == id) {
                    challenge.copy(amountFulfilled = fieldsToUpdate.amountFulfilled)
                } else {
                    challenge
                }
            }.calculateProgress()
            _state.update {
                it.copy(challenges = updatedChallenges)
            }
        }
    }
}
