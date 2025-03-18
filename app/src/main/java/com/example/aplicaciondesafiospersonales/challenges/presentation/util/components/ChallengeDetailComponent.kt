package com.example.aplicaciondesafiospersonales.challenges.presentation.util.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.aplicaciondesafiospersonales.R
import com.example.aplicaciondesafiospersonales.challenges.domain.model.Challenge
import com.example.aplicaciondesafiospersonales.challenges.domain.model.ChallengeUpdate
import com.example.aplicaciondesafiospersonales.challenges.presentation.screens.home_screen.HomeScreenViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ChallengeDetailComponent(
    challenge: Challenge,
    onDismiss: () -> Unit,
    viewModel: HomeScreenViewModel
) {
    var amountFulfilled by remember { mutableIntStateOf(challenge.amountFulfilled) }
    var progress by remember { mutableFloatStateOf(challenge.progress) }
    challenge.amountFulfilled = amountFulfilled
    challenge.progress = progress

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(15.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = challenge.title,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "antes del  ${challenge.finishDate}",
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(modifier = Modifier.size(16.dp))

                Row(
                    modifier = Modifier
                        .size(200.dp, 20.dp)
                        .clip(shape = RoundedCornerShape(15.dp, 15.dp, 15.dp, 15.dp))
                        .background(Color.LightGray),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Barra de progreso oscura que se llena
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width((200.dp * challenge.challengeProgress).coerceIn(0.dp, 200.dp))
                            .clip(shape = RoundedCornerShape(10.dp, 10.dp, 10.dp, 10.dp))
                            .background(Color.Gray)
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Text(
                        text = "${challenge.daysUntilFinishDate} días restantes",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(end = 10.dp)
                    )

                }
                Row(
                    modifier = Modifier
                        .size(200.dp, 20.dp)
                        .clip(shape = RoundedCornerShape(15.dp, 15.dp, 15.dp, 15.dp))
                        .background(Color.LightGray),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Barra de progreso oscura que se llena
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width((200.dp * progress).coerceIn(0.dp, 200.dp))
                            .clip(shape = RoundedCornerShape(10.dp, 10.dp, 10.dp, 10.dp))
                            .background(Color.Gray)
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Text(
                        text = "${challenge.amountFulfilled} de ${challenge.amountToBeFulfilled}",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(end = 10.dp)
                    )

                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Button(onClick = {
                        if (amountFulfilled > 0) {
                            amountFulfilled -= 1
                            progress = viewModel.calculateIndividualProgress(challenge,amountFulfilled)
                        }
                    }) {
                        Text("-")
                    }
                    Text(
                        text = amountFulfilled.toString(),
                        style = MaterialTheme.typography.titleLarge
                    )
                    Button(onClick = {
                        if (challenge.amountFulfilled < challenge.amountToBeFulfilled) {
                            amountFulfilled += 1
                            progress = viewModel.calculateIndividualProgress(challenge,amountFulfilled)
                        }
                    }) {
                        Text("+")
                    }
                }

                Spacer(modifier = Modifier.size(16.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Button(onClick = {
                        viewModel.deleteChallenge(
                            challenge.id,
                        )
                        onDismiss()
                    }) {
                        Text(text = stringResource(R.string.eliminar_desafío))
                    }

                    Button(onClick = {
                        viewModel.updateChallenge(
                            challenge.id,
                            ChallengeUpdate(amountFulfilled = amountFulfilled)
                        )
                        onDismiss()
                    }) {
                        Text(text = stringResource(R.string.guardar_desafío))
                    }
                }

            }
        }
    }
}