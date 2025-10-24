package com.example.apoyoemocional.viewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.example.apoyoemocional.model.InicioUIState

class InicioViewModel : ViewModel() {
    private val _estado = MutableStateFlow(InicioUIState())
    val estado: StateFlow<InicioUIState> = _estado

    fun ocultarBoton() {
        _estado.value = _estado.value.copy(mostrarBoton = false)
    }

    fun actualizarDescripcion(texto: String) {
        _estado.value = _estado.value.copy(descripcion = texto)
    }
}
