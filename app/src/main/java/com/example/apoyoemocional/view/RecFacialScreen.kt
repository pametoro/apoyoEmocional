package com.example.apoyoemocional.view

import android.graphics.BitmapFactory
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.apoyoemocional.viewModel.RecFacialViewModel
import com.google.mlkit.vision.common.InputImage
import com.example.apoyoemocional.R

@Composable
fun RecFacialScreen(viewModel: RecFacialViewModel) {
    val faces by viewModel.faces.collectAsState()
    val error by viewModel.error.collectAsState()
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE3F2FD))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Rostros detectados: ${faces.size}")
        error?.let {
            Text("Error: $it", color = Color.Red)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .size(250.dp)
                .padding(8.dp)
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                drawCircle(
                    color = Color.Gray,
                    radius = size.minDimension / 2.2f,
                    style = Stroke(width = 4.dp.toPx())
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.reco)
                val image = InputImage.fromBitmap(bitmap, 0)
                viewModel.processImage(image)
            },
            modifier = Modifier.align(Alignment.CenterHorizontally) // Centra el botón dentro del Column
        ) {
            Text("Detectar rostro")
        }
    }
}


