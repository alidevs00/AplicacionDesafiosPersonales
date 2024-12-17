package com.example.aplicaciondesafiospersonales.challenges.presentation.screens.add_new_challenge_screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aplicaciondesafiospersonales.challenges.domain.model.Challenge
import com.example.aplicaciondesafiospersonales.challenges.domain.repository.ChallengesRepository
import com.example.aplicaciondesafiospersonales.util.Event
import com.example.aplicaciondesafiospersonales.util.EventBus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class AddNewChallengeScreenViewModel @Inject constructor(
    private val challengeRepository: ChallengesRepository //use case?
) : ViewModel() {

    private val _state = MutableStateFlow(AddNewChallengeScreenViewState())

    @RequiresApi(Build.VERSION_CODES.O)
    fun saveChallenge(
        title: String,
        category: String,
        amountToBeFulfilled: String,
        finishDate: String
    ) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            val challenge = Challenge(
                title = title,
                category = category,
                amountToBeFulfilled = amountToBeFulfilled,
                amountFulfilled = "0",
                startDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                finishDate = finishDate
            )
            challengeRepository.addChallenge(challenge)

            _state.update {
                it.copy(challenge = challenge, isLoading = false)
            }
            EventBus.sendEvent(Event.Toast("Desafío guardado correctamente"))
        }
    }
}
