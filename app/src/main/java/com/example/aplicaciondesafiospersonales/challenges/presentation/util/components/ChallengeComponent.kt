package com.example.aplicaciondesafiospersonales.challenges.presentation.util.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.aplicaciondesafiospersonales.R

@Composable
fun ChallengeComponent(title: String, category: String, onClick: () -> Unit) {
    val (backgroundColor, iconRes) = getCategoryAttributes(category)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .clickable(onClick = onClick),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .clip(shape = RoundedCornerShape(15.dp, 15.dp, 15.dp, 15.dp))
                .background(backgroundColor)
        ) {
            Icon(
                painter = painterResource(iconRes),
                contentDescription = stringResource(id = R.string.app_name),
                modifier = Modifier
                    .size(60.dp)
                    .align(Alignment.Center)
            )
        }
        Spacer(modifier = Modifier.size(5.dp))
        Box(
            modifier = Modifier
                .width(100.dp)
                .height(50.dp)
        ) {
            Text(
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                text = title,
                modifier = Modifier.fillMaxWidth(),
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}


@Composable
fun AddNewChallengeComponent(onClick: () -> Unit) {
    ChallengeComponent("Añadir nuevo desafío", "Nuevo desafío", onClick)
}

@Composable
fun ShowMoreChallengesComponent(onClick: () -> Unit) {
    ChallengeComponent("Mostrar más", "Mostrar más", onClick)
}

@Composable
fun getCategoryAttributes(category: String): Pair<Color, Int> {
    return when (category) {
        "Lectura" -> colorResource(id = R.color.fondo_lectura) to R.drawable.lectura
        "Mental" -> colorResource(id = R.color.fondo_lectura) to R.drawable.mental
        "Personal" -> colorResource(id = R.color.fondo_mental) to R.drawable.personal
        "Cocina" -> colorResource(id = R.color.fondo_personal) to R.drawable.cocina
        "Ejercicio" -> colorResource(id = R.color.fondo_cocina) to R.drawable.ejercicio
        "Estudio" -> colorResource(id = R.color.fondo_ejercicio) to R.drawable.estudio
        "Trabajo" -> colorResource(id = R.color.fondo_estudio) to R.drawable.trabajo
        "Social" -> colorResource(id = R.color.fondo_trabajo) to R.drawable.social
        "Nuevo desafío" -> colorResource(id = R.color.fondo_gris) to R.drawable.nuevo
        else -> colorResource(id = R.color.fondo_gris) to R.drawable.mas
    }
}

@Preview(showBackground = true)
@Composable
fun ChallengeComponentPreview() {
    ChallengeComponent("Leer 10 libros", "Mental", onClick = {// Acción simulada para la preview
        println("Botón presionado en la preview")
    })
}