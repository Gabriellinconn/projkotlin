package br.edu.up.planner.ui.screens.sapataria

import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

object TelaUm {
    val TELA_SAPATARIA_ROUTE = "t1a"
    val TELA_PAGAMENTOS_ROUTE = "t1b"
    val TELA_REALIZADOS_ROUTE = "t1c"
}


@Composable
fun SapatariaNavHost(drawerState: DrawerState) {

    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = TelaUm.TELA_SAPATARIA_ROUTE
    ) {
        composable(TelaUm.TELA_SAPATARIA_ROUTE) {
            TelaSapatosAfazer(drawerState, navController)
        }
        composable(TelaUm.TELA_PAGAMENTOS_ROUTE) {
            TelaPagamentos(drawerState, navController)
        }
        composable(TelaUm.TELA_REALIZADOS_ROUTE) {
            TelaRealizados(drawerState, navController)
        }


    }
}



