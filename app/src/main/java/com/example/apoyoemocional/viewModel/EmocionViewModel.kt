package com.example.apoyoemocional.viewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.example.apoyoemocional.model.EmocionUIState


class EmocionViewModel  : ViewModel() {
    private val _estado = MutableStateFlow(EmocionUIState())
    val estado: StateFlow<EmocionUIState> = _estado

    fun actualizarNombre(nombre: String) {
        _estado.value = _estado.value.copy(nombreUsuario = nombre)
    }

    fun actualizarEmocion(texto: String) {
        _estado.value = _estado.value.copy(emocionTexto = texto)
    }
}
