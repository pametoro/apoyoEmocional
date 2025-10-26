package com.example.apoyoemocional.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.apoyoemocional.viewModel.InicioViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.graphics.Color
import com.example.apoyoemocional.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaginaInicio(navController: NavController, viewModel: InicioViewModel) {
    val estado by viewModel.estado.collectAsState()
    val fondoPastel = Color(0xFFE3F2FD) // Azul cielo pastel

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(estado.titulo)
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(fondoPastel)
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(16.dp)
                    .fillMaxSize()
                     .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo_apoyo_emocional),
                    contentDescription = "Logo de Apoyo Emocional",
                    modifier = Modifier
                        .size(280.dp)
                        .padding(bottom = 8.dp)
                )
                Text(
                    text = estado.descripcion,
                    color = Color.DarkGray, // aca se cambia el color
                    modifier = Modifier.padding(30.dp)
                )
                Spacer(modifier = Modifier.height(10.dp))
                if (estado.mostrarBoton) {
                    Button(onClick = {
                        navController.navigate("FormularioScreen")
                    }) {
                        Text("Registrate")
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Button(
                        onClick = {
                        navController.navigate("perfil/Pamela")
                    }) {
                        Text("Ir al perfil")
                        }
                    Spacer(modifier = Modifier.height(10.dp))
                    Button(
                        onClick = { navController.navigate("reconocimiento") }) {
                            Text("Iniciar reconocimiento facial")
                        }
                    }
                }
            }
        }
    }

