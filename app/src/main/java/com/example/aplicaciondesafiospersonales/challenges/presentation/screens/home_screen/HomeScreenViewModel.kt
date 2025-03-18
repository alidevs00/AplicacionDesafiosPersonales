package com.example.aplicaciondesafiospersonales.challenges.presentation.screens.home_screen

import android.os.Build
import androidx.annotation.RequiresApi
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
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
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
                        it.copy(
                            challenges = result.challenges.calculateProgress().daysUntilFinishDate()
                        )
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

    internal fun updateChallenge(id: String, fieldsToUpdate: ChallengeUpdate) {
        viewModelScope.launch {
            challengeRepository.updateChallenge(id, fieldsToUpdate)

            val updatedChallenges = _state.value.challenges.map { challenge ->
                if (challenge.id == id) {
                    challenge.copy(amountFulfilled = fieldsToUpdate.amountFulfilled)
                } else {
                    challenge
                }
            }.calculateProgress().daysUntilFinishDate()

            _state.update {
                it.copy(challenges = updatedChallenges)
            }
        }
    }

    internal fun deleteChallenge(id: String) {
        viewModelScope.launch {
            challengeRepository.deleteChallenge(id)
            getChallenges()
        }

    }

    private fun List<Challenge>.calculateProgress(): List<Challenge> =
        this.map { challenge ->
            challenge.copy(
                progress = calculateProgress(
                    challenge.amountFulfilled,
                    challenge.amountToBeFulfilled
                )
            )
        }

    fun calculateIndividualProgress(challenge: Challenge, newAmountFulfilled: Int): Float =
        calculateProgress(newAmountFulfilled, challenge.amountToBeFulfilled)


    private fun calculateProgress(amountFulfilled: Int, amountToBeFulfilled: Int): Float =
        (amountFulfilled / amountToBeFulfilled.toFloat()).coerceIn(
            0f,
            1f
        )


    private fun List<Challenge>.daysUntilFinishDate(): List<Challenge> {
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        val today = LocalDate.now()

        return this.map { challenge ->
            val startDate = LocalDate.parse(challenge.startDate, formatter)
            val endDate = LocalDate.parse(challenge.finishDate, formatter)
            challenge.copy(
                daysUntilFinishDate = ChronoUnit.DAYS.between(today, endDate).toInt(),
                challengeProgress = (ChronoUnit.DAYS.between(
                    startDate,
                    today
                ) / ChronoUnit.DAYS.between(startDate, endDate).toFloat()).coerceIn(
                    0f,
                    1f
                )
            )
        }
    }
}
