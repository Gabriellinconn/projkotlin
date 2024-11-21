package br.edu.up.planner

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import br.edu.up.planner.ui.screens.financas.TelaFinancas
import br.edu.up.planner.ui.screens.chaveiro.ChaveiroNavHost
import br.edu.up.planner.ui.screens.pedido.PedidoNavHost
import br.edu.up.planner.ui.screens.sapataria.SapatariaNavHost
import kotlinx.coroutines.launch

object SECRotas {
    val TELA_SAPATOS_ROTA = "tela_um"
    val TELA_CHAVES_ROTA = "tela_dois"
    val TELA_FINANCAS_ROTA = "tela_tres"

}

@Composable
fun SECNavDrawer(){

    val drawerState = rememberDrawerState(
        initialValue = DrawerValue.Closed)

    val navCtrlDrawer = rememberNavController()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent(navCtrlDrawer, drawerState)
        },
        content = {
            NavHost(
                navController = navCtrlDrawer,
                startDestination = SECRotas.TELA_CHAVES_ROTA)
            {
                composable(SECRotas.TELA_SAPATOS_ROTA) {
                    SapatariaNavHost(drawerState)
                }
                composable(SECRotas.TELA_CHAVES_ROTA) {
                    ChaveiroNavHost(drawerState)
                }
                composable(SECRotas.TELA_FINANCAS_ROTA) {
                    TelaFinancas(drawerState)
                }

            }
        }
    )
}

@Composable
private fun DrawerContent(
    navController: NavController,
    drawerState: DrawerState
    ) {

    val coroutineScope = rememberCoroutineScope()

    val currentBack by navController.currentBackStackEntryAsState()
    val rotaAtual = currentBack?.destination?.route ?: SECRotas.TELA_CHAVES_ROTA

    val ehRotaUm = rotaAtual == SECRotas.TELA_SAPATOS_ROTA
    val ehRotaDois = rotaAtual == SECRotas.TELA_CHAVES_ROTA
    val ehRotaTres = rotaAtual == SECRotas.TELA_FINANCAS_ROTA


    Column(
        modifier = Modifier
            .width(300.dp)
            .background(Color.White)
            .padding(30.dp)
            .fillMaxHeight()
    ) {
        Spacer(modifier = Modifier.height(70.dp))

        TextButton(
            colors = ButtonDefaults.buttonColors(
                containerColor = getColorMenu(ehRotaUm)
            ),
            onClick = {
                navController.navigate(SECRotas.TELA_SAPATOS_ROTA)
                coroutineScope.launch {
                    drawerState.close()
                }
            }) {
            Icon(
                painter = painterResource(id = R.drawable.sapato),
                contentDescription = "s",
                modifier = Modifier.size(80.dp).
                padding(10.dp),
                tint = getColorTexto(ehRotaUm)
            )
            Text(text = "Sapataria", fontSize = 30.sp,
                color = getColorTexto(ehRotaUm))
        }

        TextButton(
            colors = ButtonDefaults.buttonColors(
                containerColor = getColorMenu(ehRotaDois)
            ),
            onClick = {
                navController.navigate(SECRotas.TELA_CHAVES_ROTA)
                coroutineScope.launch {
                    drawerState.close()
                }
            }) {
            Icon(
                //imageVector = Icons.Default.Call,
                painter = painterResource(id = R.drawable.chave),
                contentDescription = "c",
                modifier = Modifier.size(80.dp).
                padding(10.dp),
                tint = getColorTexto(ehRotaDois)
            )
            Text(text = "Chaveiro", fontSize = 30.sp,
                color = getColorTexto(ehRotaDois))
        }
        TextButton(
            colors = ButtonDefaults.buttonColors(
                containerColor = getColorMenu(ehRotaTres)
            ),
            onClick = {
                navController.navigate(SECRotas.TELA_FINANCAS_ROTA)
                coroutineScope.launch {
                    drawerState.close()
                }
            }) {
            Icon(
                //imageVector = Icons.Default.Call,
                painter = painterResource(id = R.drawable.cifrao),
                contentDescription = "f",
                modifier = Modifier.size(80.dp).
                padding(10.dp),
                tint = getColorTexto(ehRotaTres)
            )
            Text(text = "Finanças", fontSize = 30.sp,
                color = getColorTexto(ehRotaTres))
        }

        }
    }


fun getColorMenu(estaSelecionada: Boolean): Color {
    if (estaSelecionada){
        return Color.Yellow
    } else {
        return Color.Transparent
    }
}

fun getColorTexto(estaSelecionada: Boolean): Color {
    if (estaSelecionada){
        return Color.Black
    } else {
        return Color.DarkGray
    }
}
