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
import com.example.apoyoemocional.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaginaInicio(navController: NavController, viewModel: InicioViewModel) {
    val estado by viewModel.estado.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(estado.titulo)},
                navigationIcon = {
                    Button(onClick = { navController.popBackStack() }) {
                        Text("<")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_apoyo_emocional),
                contentDescription = "Logo de Apoyo Emocional",
                modifier = Modifier
                    .size(150.dp)
                    .padding(bottom = 24.dp)
            )

            Text(text = estado.descripcion, style = MaterialTheme.typography.bodyLarge)

            Spacer(modifier = Modifier.height(24.dp))

            if (estado.mostrarBoton) {
                Button(onClick = {
                    navController.navigate("FormularioScreen")
                }) {
                    Text("Registrate")
                }
                Spacer(modifier = Modifier.height(16.dp))

                Button(onClick = {
                    navController.navigate("Emocion")
                }) {
                    Text("Ir al perfil")
                }
            }
        }
    }
}
