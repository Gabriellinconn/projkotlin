package br.edu.up.planner.ui.screens.chaveiro

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.edu.up.planner.ui.screens.sapataria.SapatoViewModel

@Composable
fun ChaveiroNavHost(
    //db: ChaveDatabase
    viewModel: SapatoViewModel
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "listarChaves")
    {
        composable("listarChaves") {
            TelaChavesAfazer(viewModel, navController)
        }
    }
}
