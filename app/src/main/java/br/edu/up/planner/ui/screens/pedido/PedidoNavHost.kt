package br.edu.up.planner.ui.screens.pedido

import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.edu.up.planner.TelaPedidos


object TelaQuatro {
    val TELA_PEDIDOS_ROUTE = "t4a"

}


@Composable
fun PedidoNavHost(drawerState: DrawerState) {

    val navCtrlBottonNav = rememberNavController()
    NavHost(
        navController = navCtrlBottonNav,
        startDestination = TelaQuatro.TELA_PEDIDOS_ROUTE
    ) {
        composable(TelaQuatro.TELA_PEDIDOS_ROUTE) {
            TelaPedidos(drawerState)
        }
    }
}



