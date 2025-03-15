package com.example.aplicaciondesafiospersonales.challenges.presentation.screens.add_new_challenge_screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aplicaciondesafiospersonales.challenges.domain.model.Challenge
import com.example.aplicaciondesafiospersonales.challenges.domain.repository.ChallengesRepository
import com.example.aplicaciondesafiospersonales.util.Constant
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

   /* @RequiresApi(Build.VERSION_CODES.O)
    internal fun validateFields(
        title: String,
        category: String,
        amountToBeFulfilled: Int,
        finishDate: String
    ): MutableState<Boolean> {
        if (amountToBeFulfilled.contains("a")) return mutableStateOf(true)
        else {
            saveChallenge(title, category, amountToBeFulfilled, finishDate)
            return mutableStateOf(false)
        }
    }*/

    @RequiresApi(Build.VERSION_CODES.O)
    internal fun saveChallenge(
        title: String,
        category: String,
        amountToBeFulfilled: Int,
        finishDate: String
    ) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            val challenge = Challenge(
                title = title,
                category = category,
                amountToBeFulfilled = amountToBeFulfilled,
                amountFulfilled = Constant.INT_ZERO,
                startDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                finishDate = finishDate
            )

            challengeRepository.addChallenge(challenge)
                .onRight {
                    _state.update {
                        it.copy(challenge = challenge, isLoading = false)
                    }
                    EventBus.sendEvent(Event.Toast("Desafío guardado correctamente"))
                }
                .onLeft {
                    EventBus.sendEvent(Event.Toast("Error guardando el desafío"))
                }

        }
    }
}
