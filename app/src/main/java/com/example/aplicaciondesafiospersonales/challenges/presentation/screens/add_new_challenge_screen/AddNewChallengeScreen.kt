package com.example.aplicaciondesafiospersonales.challenges.presentation.screens.add_new_challenge_screen

import FieldComponent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.aplicaciondesafiospersonales.R

@RequiresApi(Build.VERSION_CODES.O)
@Composable
internal fun AddNewChallengeScreen(
    viewModel: AddNewChallengeScreenViewModel = hiltViewModel(),
    navController: NavHostController
) {
    AddNewChallengeScreenContent(
        navController = navController,
        viewModel = viewModel
    )
}


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNewChallengeScreenContent(
    navController: NavHostController,
    viewModel: AddNewChallengeScreenViewModel
) {
    // Estados para cada campo de texto
    val titleState = remember { mutableStateOf("") }
    val categoryState = remember { mutableStateOf("") }
    val amountState = remember { mutableStateOf("") }
    val endDateState = remember { mutableStateOf("") }

    // Opciones para el desplegable
    val categories =
        listOf("Lectura", "Ejercicio", "Estudio", "Mental", "Personal", "Social", "Trabajo")

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Nuevo desafío", style = MaterialTheme.typography.titleLarge) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFFA8F0C1),
                    titleContentColor = Color.Black,
                    navigationIconContentColor = Color.Black
                )
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color(0xFFA8F0C1))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Color.White,
                        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                    )
            ) {
                // Contenido desplazable
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 36.dp, vertical = 24.dp)
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(45.dp)
                ) {
                    FieldComponent(
                        title = stringResource(R.string.field_titulo),
                        textState = titleState
                    )
                    FieldComponent(
                        title = stringResource(R.string.field_categoria),
                        textState = categoryState,
                        isDropdown = true,
                        options = categories
                    )
                    FieldComponent(
                        title = stringResource(R.string.field_cantidad_a_cumplir),
                        textState = amountState
                    )
                    FieldComponent(
                        title = stringResource(R.string.field_fecha_de_finalizacion),
                        textState = endDateState,
                        isDateField = true
                    )
                }

                // Botón fijo en la parte inferior
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Button(
                        onClick = {
                            viewModel.saveChallenge(
                                title = titleState.value,
                                category = categoryState.value,
                                amountToBeFulfilled = amountState.value,
                                finishDate = endDateState.value
                            )
                            navController.popBackStack()
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFA8F0C1)),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        shape = RoundedCornerShape(5.dp)
                    ) {
                        Text(
                            "Guardar",
                            color = Color.Black,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
        }
    }
}

















