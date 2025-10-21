package com.example.apoyoemocional.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.apoyoemocional.viewModel.EmocionViewModel
import androidx.compose.material3.MediumTopAppBar


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmocionScreen(navController: NavController, viewModel: EmocionViewModel) {
    val estado by viewModel.estado.collectAsState()

    Scaffold(
        topBar = {
            MediumTopAppBar(
                title = { Text("Tu espacio emocional") }
            )
        }
    ){ innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = "Hola, ${estado.nombreUsuario}",
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "¿Cómo te sientes hoy?",
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = estado.emocionTexto,
                onValueChange = { viewModel.actualizarEmocion(it) },
                label = { Text("Escribe tus emociones") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(onClick = {
                // Aquí podrías guardar, mostrar resumen o navegar
            }) {
                Text("Guardar")
            }
        }
    }
}
