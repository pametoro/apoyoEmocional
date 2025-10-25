package com.example.apoyoemocional

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.apoyoemocional.ui.theme.ApoyoEmocionalTheme
import com.example.apoyoemocional.view.EmocionGuardadaScreen
import com.example.apoyoemocional.view.EmocionScreen
import com.example.apoyoemocional.view.FormularioScreen
import com.example.apoyoemocional.view.PaginaInicio
import com.example.apoyoemocional.view.RecFacialScreen // IMPORTADA: Nueva pantalla de Reconocimiento Facial
import com.example.apoyoemocional.view.RecursosScreen
import com.example.apoyoemocional.view.RespiraScreen
import com.example.apoyoemocional.view.ResumenScreen
import com.example.apoyoemocional.view.screen.PerfilScreen
import com.example.apoyoemocional.viewModel.EmocionViewModel
import com.example.apoyoemocional.viewModel.InicioViewModel
import com.example.apoyoemocional.viewModel.UsuarioViewModel
import com.example.apoyoemocional.viewModel.PerfilViewModel
import com.example.apoyoemocional.viewModel.RecFacialViewModel // IMPORTADO: Nuevo ViewModel
import com.example.apoyoemocional.viewModel.RecursosViewModel
import com.example.apoyoemocional.viewModel.RespiraViewModel
import java.net.URLDecoder
import java.nio.charset.StandardCharsets
import androidx.compose.runtime.collectAsState


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ApoyoEmocionalTheme {
                val navController = rememberNavController()
                val usuarioViewModel: UsuarioViewModel = viewModel()
                val perfilViewModel: PerfilViewModel = viewModel()
                val inicioViewModel: InicioViewModel = viewModel()
                val emocionViewModel: EmocionViewModel = viewModel()
                val respiraViewModel: RespiraViewModel = viewModel()
                val recursosViewModel: RecursosViewModel = viewModel()
                val recFacialViewModel: RecFacialViewModel = viewModel() // NUEVO VIEWMODEL INSTANCIADO


                NavHost(navController = navController, startDestination = "inicio") {
                    composable("inicio") {
                        PaginaInicio(navController, inicioViewModel)
                    }
                    composable("FormularioScreen") {
                        FormularioScreen(navController, usuarioViewModel)
                    }
                    composable("PerfilScreen") {
                        // Esta ruta se mantiene como un alias que apunta a ResumenScreen
                        // Se actualiza para ser consistente con la ruta 'resumen' y pasar el nombre
                        val nombreUsuario = usuarioViewModel.estado.collectAsState().value.nombre
                        ResumenScreen(navController, usuarioViewModel, nombreUsuario)
                    }

                    // RUTA PRINCIPAL DE RESUMEN: Aquí navegamos a "emocion" y pasamos el nombre del usuario
                    composable("resumen") {
                        val nombreUsuario = usuarioViewModel.estado.collectAsState().value.nombre // Obtenemos el nombre del ViewModel
                        ResumenScreen(navController, usuarioViewModel, nombreUsuario)
                    }

                    // NUEVA RUTA para el Reconocimiento Facial
                    composable("reconocimiento") {
                        RecFacialScreen(navController = navController, viewModel = recFacialViewModel)
                    }

                    composable("perfil") {
                        PerfilScreen(navController, perfilViewModel)
                    }

                    // RUTA: Recibimos el nombre del usuario como argumento
                    composable("emocion/{nombreUsuario}") { backStackEntry ->
                        val nombre = backStackEntry.arguments?.getString("nombreUsuario") ?: "Invitado"
                        EmocionScreen(navController, emocionViewModel, nombre)
                    }

                    // RUTA: Recibimos el nombre y el texto de la emoción
                    composable("emocionGuardada/{nombreUsuario}/{emocionTexto}") { backStackEntry ->
                        val nombre = backStackEntry.arguments?.getString("nombreUsuario") ?: "Usuario Desconocido"
                        val emocion = backStackEntry.arguments?.getString("emocionTexto") ?: "No se registró emoción."
                        // Es importante decodificar la emoción porque fue codificada en EmocionScreen
                        val emocionDecoded = URLDecoder.decode(emocion, StandardCharsets.UTF_8.toString())

                        EmocionGuardadaScreen(navController, nombre, emocionDecoded)
                    }

                    composable("recursos") {
                        RecursosScreen(navController, recursosViewModel)
                    }
                    composable("Respira") {
                        RespiraScreen(navController = navController, viewModel = respiraViewModel)
                    }
                }
            }
        }
    }
}




