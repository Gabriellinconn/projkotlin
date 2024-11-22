package br.edu.up.planner.ui.screens.sapataria

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun SapatariaNavHost(viewModel: SapatoViewModel) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = SapatosRota.TELA_LISTAR_SAPATOS_ROTA
    ) {
        composable(SapatosRota.TELA_LISTAR_SAPATOS_ROTA) {
            TelaSapatosAfazer(viewModel = viewModel, navController = navController)
        }
        composable(SapatosRota.TELA_INCLUIR_SAPATOS_ROTA) {
            TelaIncluirSapatos(navController = navController)
        }
    }
}
