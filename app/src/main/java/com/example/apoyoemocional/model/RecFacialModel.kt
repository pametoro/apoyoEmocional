// Ruta: com.example.apoyoemocional.model.FaceInfo.kt
package com.example.apoyoemocional.model

data class FaceInfo(
    val boundingBox: String,
    val smilingProbability: Float?,
    val leftEyeOpenProbability: Float?,
    val rightEyeOpenProbability: Float?
)
