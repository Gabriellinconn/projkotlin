package br.edu.up.planner.ui.screens.chaveiro

import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

object TelaDois {
    val TELA_CHAVEIRO_ROUTE = "t2a"

}


@Composable
fun ChaveiroNavHost(drawerState: DrawerState) {

    val navCtrlBottonNav = rememberNavController()
    NavHost(
        navController = navCtrlBottonNav,
        startDestination = TelaDois.TELA_CHAVEIRO_ROUTE
    ) {
        composable(TelaDois.TELA_CHAVEIRO_ROUTE) {
            TelaChavesAfazer(drawerState)
        }
    }
}



