package com.example.apoyoemocional.repository

import android.net.Uri
import com.example.apoyoemocional.model.PerfilDeUsuario

class PerfilRepositorio {
    private var perfilActual = PerfilDeUsuario(
        id = 1,
        nombre = "Usuario",
        imagenUri = null
    )

    fun getProfile(): PerfilDeUsuario = perfilActual

    fun updateImage(uri: Uri?) {
        perfilActual = perfilActual.copy(imagenUri = uri)
    }
}