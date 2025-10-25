package com.example.apoyoemocional.viewModel

import androidx.lifecycle.ViewModel
import com.example.apoyoemocional.model.RecFacialModel
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.face.Face
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class RecFacialViewModel : ViewModel() {
    private val _faces = MutableStateFlow<List<Face>>(emptyList())
    val faces: StateFlow<List<Face>> = _faces

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun processImage(image: InputImage) {
        RecFacialModel.detectFaces(
            image,
            onResult = { detectedFaces -> _faces.value = detectedFaces },
            onError = { e -> _error.value = e.message }
        )
    }
}

