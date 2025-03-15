package com.example.aplicaciondesafiospersonales.challenges.presentation.util.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
fun ChallengeComponent(title: String, category: String, progress: Float, onClick: () -> Unit) {
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
                .background(backgroundColor.copy(alpha = 0.6f))
        ) {
            // Barra de progreso oscura que se llena
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width((100.dp * progress).coerceIn(0.dp, 100.dp))
                    .clip(shape = RoundedCornerShape(10.dp, 10.dp, 10.dp, 10.dp))
                    .background(backgroundColor)
            )

            // Icono centrado
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
    ChallengeComponent(
        title = stringResource(R.string.añadir_nuevo_desafío),
        category = "Nuevo desafío",
        progress = 0f,
        onClick = onClick
    )
}

@Composable
fun ShowMoreChallengesComponent(onClick: () -> Unit) {
    ChallengeComponent(
        title = "Mostrar más",
        category = "Mostrar más",
        progress = 0f,
        onClick = onClick
    )
}

@Composable
fun getCategoryAttributes(category: String): Pair<Color, Int> {

    return when (category) {
        "Lectura" -> colorResource(id = R.color.fondo_lectura) to R.drawable.lectura
        "Mental" -> colorResource(id = R.color.fondo_mental) to R.drawable.mental
        "Personal" -> colorResource(id = R.color.fondo_personal) to R.drawable.personal
        "Cocina" -> colorResource(id = R.color.fondo_cocina) to R.drawable.cocina
        "Ejercicio" -> colorResource(id = R.color.fondo_ejercicio) to R.drawable.ejercicio
        "Estudio" -> colorResource(id = R.color.fondo_estudio) to R.drawable.estudio
        "Trabajo" -> colorResource(id = R.color.fondo_trabajo) to R.drawable.trabajo
        "Social" -> colorResource(id = R.color.fondo_social) to R.drawable.social
        "Nuevo desafío" -> colorResource(id = R.color.fondo_gris) to R.drawable.nuevo
        else -> colorResource(id = R.color.fondo_gris) to R.drawable.mas
    }
}

@Composable
fun ProgressBoxExample() {
    var progress by remember { mutableStateOf(0.5f) } // Progreso inicial: 50%

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ProgressBox(
            progress = progress,
            backgroundColor = Color(0xFFB3E5FC), // Color claro
            progressColor = Color(0xFF0288D1),  // Color oscuro
            iconResId = R.drawable.cocina // Reemplaza con tu ícono
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botones para ajustar el progreso
        Row {
            Button(onClick = { if (progress > 0f) progress -= 0.1f }) {
                Text("-")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { if (progress < 1f) progress += 0.1f }) {
                Text("+")
            }
        }
    }
}

@Composable
fun ProgressBox(
    progress: Float, // Progreso entre 0f y 1f
    backgroundColor: Color = Color(0xFFB3E5FC), // Color claro
    progressColor: Color = Color(0xFF0288D1), // Color oscuro
    iconResId: Int // Recurso del icono
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp) // Altura de la caja
            .clip(RoundedCornerShape(8.dp)) // Bordes redondeados
            .background(backgroundColor) // Fondo color claro
    ) {
        // Barra de progreso
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(progress.coerceIn(0f, 1f)) // Progreso entre 0 y 1
                .background(progressColor) // Color oscuro
        )

        // Icono centrado encima del progreso
        Icon(
            painter = painterResource(id = iconResId),
            contentDescription = "Icono de progreso",
            tint = Color.White,
            modifier = Modifier
                .size(32.dp)
                .align(Alignment.Center)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ChallengeComponentPreview() {
    ProgressBoxExample()
}
