package com.example.aplicaciondesafiospersonales.challenges.presentation.util

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.aplicaciondesafiospersonales.R

class CategoryViewModel : ViewModel() {
    var colorResourceCustom = 0
    var painterResourceCustom = 0
}

@Composable
fun ChallengeComponent(title: String, category: String) {
    //selectCategory(category)
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .clip(shape = RoundedCornerShape(15.dp, 15.dp, 15.dp, 15.dp))
                .background(colorResource(R.color.fondo_lectura))
        ) {
            Icon(
                painter = painterResource(R.drawable.lectura),
                contentDescription = stringResource(id = R.string.app_name),
                modifier = Modifier
                    .size(60.dp)
                    .align(Alignment.Center)
            )
        }
        Text(textAlign = TextAlign.Center, text = title)
    }
}

@Composable
fun AddNewChallengeComponent() {
    ChallengeComponent("Añadir nuevo desafío", "Nuevo desafío")
}

@Composable
fun ShowMoreChallengesComponent() {
    ChallengeComponent("Mostrar más", "Mostrar mas")
}


/*internal fun selectCategory(category: String) {
    when (category) {
        "Lectura" -> {
            viewModel.colorResourceCustom = R.color.fondo_lectura
            viewModel.painterResourceCustom = R.drawable.lectura
        }

        "Mental" -> {
            viewModel.colorResourceCustom = R.color.fondo_mental
            viewModel.painterResourceCustom = R.drawable.mental
        }

        "Ejercicio" -> {
            viewModel.colorResourceCustom = R.color.fondo_ejercicio
            viewModel.painterResourceCustom = R.drawable.ejercicio
        }

        "Cocina" -> {
            viewModel.colorResourceCustom = R.color.fondo_cocina
            viewModel.painterResourceCustom = R.drawable.cocina
        }

        "Social" -> {
            viewModel.colorResourceCustom = R.color.fondo_social
            viewModel.painterResourceCustom = R.drawable.social
        }

        "Personal" -> {
            viewModel.colorResourceCustom = R.color.fondo_personal
            viewModel.painterResourceCustom = R.drawable.personal
        }

        "Trabajo" -> {
            viewModel.colorResourceCustom = R.color.fondo_trabajo
            viewModel.painterResourceCustom = R.drawable.trabajo
        }

        "Estudio" -> {
            viewModel.colorResourceCustom = R.color.fondo_estudio
            viewModel.painterResourceCustom = R.drawable.estudio
        }

        "Nuevo desafío" -> {
            viewModel.colorResourceCustom = R.color.fondo_gris
            viewModel.painterResourceCustom = R.drawable.nuevo
        }

        "Mostrar mas" -> {
            viewModel.colorResourceCustom = R.color.fondo_gris
            viewModel.painterResourceCustom = R.drawable.mas
        }
    }
}*/

@Preview(showBackground = true)
@Composable
fun ChallengeComponentPreview() {
    ChallengeComponent("Leer 10 libros", "Mental")
}