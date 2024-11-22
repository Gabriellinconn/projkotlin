package br.edu.up.planner.ui.screens.sapataria

import Sapato
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
import kotlinx.coroutines.launch

// Rotas
object SapatosRota {
    const val TELA_LISTAR_SAPATOS_ROTA = "listar_sapatos"
    const val TELA_INCLUIR_SAPATOS_ROTA = "incluir_sapatos"
}

// Tela principal
@Composable
fun TelaSapatosAfazer(navController: NavHostController, viewModel: SapatoViewModel) {
    val sapatos by viewModel.sapatos.collectAsState()

    val serviçosSapatos = remember {
        mutableListOf(
            Sapato(pago = true, nomeSapato = "Tênis Nike", preco = 18.00, formaPagamento = 1, par = false, concluido = false, descricao = "Reparar sola", sapatoId = 1),
            Sapato(pago = false, nomeSapato = "Tênis Adidas", preco = 50.00, formaPagamento = 4, par = true, concluido = true, descricao = "Trocar cadarço", sapatoId = 2)
        )
    }

    Scaffold(
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                NavHost(
                    navController = navController,
                    startDestination = SapatosRota.TELA_LISTAR_SAPATOS_ROTA
                ) {
                    composable(SapatosRota.TELA_LISTAR_SAPATOS_ROTA) {
                        TelaListagemSapatos(sapatosAfazer = serviçosSapatos)
                    }
                    composable(SapatosRota.TELA_INCLUIR_SAPATOS_ROTA) {
                        TelaIncluirSapatos(navController = navController)
                    }
                }
            }
        },
        floatingActionButton = { FloatButton(navController = navController) }
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
                text = "ID: #${sapatoAfazer.sapatoId}",
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
fun TelaIncluirSapatos(navController: NavController) {
    val viewModel: SapatoViewModel = viewModel()

    var nomeSapato by remember { mutableStateOf("") }
    var descricao by remember { mutableStateOf("") }
    var preco by remember { mutableStateOf(0.0) }

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
            label = { Text("Descrição") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            coroutineScope.launch {
                val sapatoSalvar = Sapato(
                    sapatoId = 0,
                    nomeSapato = nomeSapato,
                    descricao = descricao,
                    concluido = false,
                    preco = preco,
                    formaPagamento = 0,
                    par = false,
                    pago = false
                )
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
    }
}
