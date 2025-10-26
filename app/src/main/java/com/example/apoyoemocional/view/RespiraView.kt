package com.example.apoyoemocional.view

import android.net.Uri
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.apoyoemocional.R
import com.example.apoyoemocional.view.components.VideoPlayer
import com.example.apoyoemocional.viewModel.RespiraViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RespiraScreen(navController: NavController, viewModel: RespiraViewModel) {
    val estado by viewModel.estado
    val fondoPastel = Color(0xFFE3F2FD)

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = estado.titulo,
                        style = MaterialTheme.typography.headlineSmall,
                        color = Color(0xFF03A9F4)
                    )
                },
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
                .fillMaxSize()
                .background(fondoPastel)
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.descarga_yoga),
                contentDescription = "persona haciendo yoga",
                modifier = Modifier
                    .size(180.dp)
                    .padding(bottom = 25.dp)
            )

            Text(
                text = "Respira profundamente y sigue las instrucciones del video",
                color = Color.DarkGray,
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 25.sp
            )

            Spacer(modifier = Modifier.height(24.dp))

            RespiracionAnimada() // 🔄 Animación de respiración

            Spacer(modifier = Modifier.height(24.dp))

            val videoUri = Uri.parse(estado.url)

            VideoPlayer(
                videoUri = videoUri,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(onClick = {
                navController.navigate("inicio") {
                    popUpTo(0)
                }
            }) {
                Text("Cerrar sesión")
            }
        }
    }
}

@Composable
fun RespiracionAnimada() {
    val infiniteTransition = rememberInfiniteTransition()
    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.3f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2000),
            repeatMode = RepeatMode.Reverse
        )
    )

    var texto by remember { mutableStateOf("Inhala") }

    // Alterna el texto cada 2 segundos
    LaunchedEffect(Unit) {
        while (true) {
            texto = if (texto == "Inhala") "Exhala" else "Inhala"
            kotlinx.coroutines.delay(2000)
        }
    }

    Box(
        modifier = Modifier
            .size(120.dp)
            .scale(scale)
            .background(Color(0xFFB3E5FC), shape = MaterialTheme.shapes.medium),
        contentAlignment = Alignment.Center
    ) {
        Text(texto, fontSize = 18.sp, color = Color.White)
    }
}

