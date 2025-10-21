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
import com.example.apoyoemocional.view.EmocionScreen
import com.example.apoyoemocional.view.FormularioScreen
import com.example.apoyoemocional.view.PaginaInicio
import com.example.apoyoemocional.view.ResumenScreen
import com.example.apoyoemocional.view.screen.PerfilScreen
import com.example.apoyoemocional.viewModel.EmocionViewModel
import com.example.apoyoemocional.viewModel.InicioViewModel
import com.example.apoyoemocional.viewModel.UsuarioViewModel
import com.example.apoyoemocional.viewModel.PerfilViewModel

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

                NavHost(navController = navController, startDestination = "inicio") {
                    composable("inicio") {
                        PaginaInicio(navController, inicioViewModel)
                    }
                    composable("FormularioScreen") {
                        FormularioScreen(navController, usuarioViewModel)
                    }
                    composable("resumen") {
                        ResumenScreen(usuarioViewModel)
                    }
                    composable("perfil") {
                        PerfilScreen(perfilViewModel)
                    }
                    composable("emocion") { EmocionScreen(navController, emocionViewModel) }

                    }
                }
            }
        }
    }





