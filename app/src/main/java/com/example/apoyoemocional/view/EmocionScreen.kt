package com.example.apoyoemocional.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.apoyoemocional.viewModel.EmocionViewModel
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import java.net.URLEncoder
import java.nio.charset.StandardCharsets


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmocionScreen(navController: NavController, viewModel: EmocionViewModel, nombre: String) {
    // CORRECCIÓN CLAVE: Usamos el argumento 'nombre' para inicializar el ViewModel
    LaunchedEffect(nombre) {
        // Actualizamos el nombre de usuario en el ViewModel con el argumento recibido de la navegación.
        viewModel.actualizarNombre(nombre)
    }

    val estado by viewModel.estado.collectAsState()
    val fondoPastel = Color(0xFFE3F2FD) // Azul cielo pastel
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = {SnackbarHost(snackbarHostState)},
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = estado.titulo,
                            fontSize = 30.sp ,
                            style = MaterialTheme.typography.titleLarge,
                            color = Color(0xFF03A9F4)
                        )
                    }
                },
                navigationIcon = {
                    Button(onClick = { navController.popBackStack() }) {
                        Text("<")
                    }
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
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Top
            ) {
                // Aquí usamos la descripción del estado (si es necesario)
                Text(text = estado.descripcion, style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(26.dp))

                // Muestra "Hola, [Nombre del Usuario]"
                Text(
                    text = "Hola, ${estado.nombreUsuario}",
                    style = MaterialTheme.typography.headlineSmall
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "¿Cómo te sientes hoy?",
                    style = MaterialTheme.typography.bodyLarge
                )

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = estado.emocionTexto,
                    onValueChange = { viewModel.actualizarEmocion(it) },
                    label = { Text("Escribe tus emociones") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = {
                        val isSaved = viewModel.guardarEmocion()
                        if (isSaved) {
                            val nombreEncoded = URLEncoder.encode(
                                estado.nombreUsuario,
                                StandardCharsets.UTF_8.toString()
                            )
                            val emocionEncoded = URLEncoder.encode(
                                estado.emocionTexto,
                                StandardCharsets.UTF_8.toString()
                            )

                            navController.navigate("emocionGuardada/$nombreEncoded/$emocionEncoded")
                        } else {
                            scope.launch {
                                snackbarHostState.showSnackbar("Por favor, escribe tus emociones antes de guardar.")
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ){
                    Text("Guardar")
                }
                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = { navController.navigate("recursos") },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Ver recursos emocionales")


                }
            }
        }


    }
}




//val videoUri = Uri.parse("https://www.example.com/video.mp4") // Usa tu URL o archivo local

//VideoPlayer(
//videoUri = videoUri,
//modifier = Modifier
//.fillMaxWidth()
//.height(200.dp)
//)
