package com.example.apoyoemocional.viewModel


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.apoyoemocional.model.VideoRespiracion

class RespiraViewModel : ViewModel() {
    private val _estado = mutableStateOf(
        VideoRespiracion(
            titulo = "Ejercicio de respiración",
            url = "https://www.youtube.com/watch?v=adpR2UQTElk"
        )
    )
    val estado: State<VideoRespiracion> = _estado
}

