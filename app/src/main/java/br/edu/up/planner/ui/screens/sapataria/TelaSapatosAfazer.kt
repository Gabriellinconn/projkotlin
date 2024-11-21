package br.edu.up.planner.ui.screens.sapataria

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.edu.up.planner.ui.screens.util.SECTopBar
import kotlinx.coroutines.launch

// Rotas
object SapatosRota {
    const val TELA_LISTAR_SAPATOS_ROTA = "listar_sapatos"
    const val TELA_INCLUIR_SAPATOS_ROTA = "incluir_sapatos"
}



// Tela principal
@Composable
fun TelaSapatosAfazer(drawerState: DrawerState, navController: NavHostController) {
    val serviçosSapatos = mutableListOf(
        Sapato(pago = true, nomeSapato = "Tênis Nike", preco = 18.00, formaPagamento = 1, par = false, concluido = false, descricao = "Reparar sola", id = 1),
        Sapato(pago = false, nomeSapato = "Tênis Adidas", preco = 50.00, formaPagamento = 4, par = true, concluido = true, descricao = "Trocar cadarço", id = 2)
    )

    val navCtrlSapatos = rememberNavController()

    Scaffold(
        topBar = { SECTopBar(drawerState) },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                NavHost(
                    navController = navCtrlSapatos,
                    startDestination = SapatosRota.TELA_LISTAR_SAPATOS_ROTA
                ) {
                    composable(SapatosRota.TELA_LISTAR_SAPATOS_ROTA) {
                        TelaListagemSapatos(sapatosAfazer = serviçosSapatos)
                    }
                    composable(SapatosRota.TELA_INCLUIR_SAPATOS_ROTA) {
                        TelaIncluirSapatos(navController = navCtrlSapatos, viewModel = SapatoViewModel(
                            viewModel()
                        )
                        )
                    }
                }
            }
        },
        floatingActionButton = { FloatButton(navController = navCtrlSapatos) }
    )
}

// Tela de listagem
@Composable
fun TelaListagemSapatos(sapatosAfazer: List<Sapato>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 16.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(sapatosAfazer) { sapato ->
            SapatoCard(sapatoAfazer = sapato)
        }
    }
}

// Card de um sapato
@Composable
fun SapatoCard(sapatoAfazer: Sapato) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = sapatoAfazer.nomeSapato,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 5.dp)
            )
            Text(
                text = sapatoAfazer.descricao,
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom = 5.dp)
            )
            Text(
                text = "ID: #${sapatoAfazer.id}",
                fontSize = 14.sp,
                modifier = Modifier.padding(bottom = 5.dp)
            )
            Text(
                text = if (sapatoAfazer.par) "Par" else "Um pé",
                fontSize = 16.sp
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "R$ ${sapatoAfazer.preco}",
                    fontSize = 16.sp
                )
                Text(
                    text = when (sapatoAfazer.formaPagamento) {
                        1 -> "Dinheiro"
                        2 -> "Cartão"
                        3 -> "Pix"
                        else -> "Não informado"
                    },
                    fontSize = 16.sp
                )
                Text(
                    text = if (sapatoAfazer.pago) "Pago" else "Não pago",
                    color = if (sapatoAfazer.concluido) Color.Green else Color.Red,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Text(
                text = if (sapatoAfazer.concluido) "Concluído" else "Pendente",
                color = if (sapatoAfazer.concluido) Color.Green else Color.Yellow,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

// Tela de inclusão
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaIncluirSapatos(navController: NavController, viewModel: SapatoViewModel, sapatoId: Int? =null) {

    //                    var id: Int? = null,
//                val nomeSapato: String,
//                val descricao: String,
//                val concluido: Boolean = false,
//                val preco: Double,
//                val formaPagamento: Int,
//                val par: Boolean = false

    var nomeSapato by remember { mutableStateOf("") }
    var descricao by remember { mutableStateOf("") }
    var concluido by remember { mutableStateOf((false)) }
    var preco by remember { mutableStateOf((0.0)) }
    var formaPagamento by remember { mutableStateOf(0) }
    var par by remember { mutableStateOf(false) }
    var pago by remember { mutableStateOf((false)) }


    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Adicionar Pedido", fontSize = 24.sp, color = Color.Black)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = nomeSapato,
            onValueChange = { nomeSapato = it },
            label = { Text("Nome do sapato") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = descricao,
            onValueChange = { descricao = it },
            label = { Text("Tipo de Sapato") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            coroutineScope.launch {

                // Adiciona o sapato
                val sapatoSalvar = Sapato(
                    id = sapatoId ?: 0, // Se sapatoId for nulo, atribui 0 (ou qualquer outro valor default)
                    nomeSapato = nomeSapato,
                    descricao = descricao,
                    concluido = concluido,
                    preco = preco,
                    formaPagamento = formaPagamento,
                    par = par,
                    pago = pago


                )
                // Salvar o sapato no repositório (futuramente)
                // Exemplo: viewModel.gravar(sapatoSalvar)
                viewModel.gravar(sapatoSalvar)
                navController.popBackStack()
            }
        }) {
            Text("Adicionar Pedido")
        }
    }
}

// Botão flutuante
@Composable
fun FloatButton(navController: NavController) {
    FloatingActionButton(onClick = {
        navController.navigate(SapatosRota.TELA_INCLUIR_SAPATOS_ROTA)
    }) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Adicionar"
        )
    }}
