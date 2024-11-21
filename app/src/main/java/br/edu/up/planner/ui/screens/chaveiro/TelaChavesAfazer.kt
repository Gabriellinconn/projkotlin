package br.edu.up.planner.ui.screens.chaveiro

import androidx.compose.foundation.background
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
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.edu.up.planner.ui.screens.util.SECTopBar

// Rotas
object ChavesRota {
    const val TELA_LISTAR_CHAVES_ROTA = "listar_chaves"
    const val TELA_INCLUIR_CHAVES_ROTA = "incluir_chaves"
}

// Tela principal
@Composable
fun TelaChavesAfazer(drawerState: DrawerState) {
    val serviçosChaves = mutableListOf(
        ChavesAfazer(
            modelochave = "Chave Papaiz",
            nomecliente = "Wellinton",
            preco = 24.00,
            formaPagamento = 1,
            tipo = 1,
            concluido = false,
            pago = true,
            id = 1,
            quantidade = 2
        ),
        ChavesAfazer(
            modelochave = "Chave GOLD",
            nomecliente = "Cleber",
            preco = 18.00,
            formaPagamento = 2,
            tipo = 2,
            concluido = true,
            pago = false,
            id = 2,
            quantidade = 1
        ),
        ChavesAfazer(
            modelochave = "Aliança",
            nomecliente = "Junior",
            preco = 36.00,
            formaPagamento = 3,
            tipo = 1,
            concluido = false,
            pago = false,
            id = 3,
            quantidade = 1
        ),
    )

    val navCtrlTarefas = rememberNavController()

    Scaffold(
        topBar = { SECTopBar(drawerState) },
        content = { paddingValues ->
            Box(modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
            ) {
                NavHost(
                    navController = navCtrlTarefas,
                    startDestination = ChavesRota.TELA_LISTAR_CHAVES_ROTA
                ) {
                    composable(ChavesRota.TELA_LISTAR_CHAVES_ROTA) {
                        TelaListagemChaves(chavesAfazer = serviçosChaves)
                    }
                    composable(ChavesRota.TELA_INCLUIR_CHAVES_ROTA) {
                        TelaIncluirChaves()
                    }
                }
            }
        },
        floatingActionButton = {
            FloatButton(navController = navCtrlTarefas)
        }
    )
}

// Tela de listagem
@Composable
private fun TelaListagemChaves(chavesAfazer: MutableList<ChavesAfazer>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp, bottom = 90.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(chavesAfazer) { chave ->
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
                        text = chave.modelochave,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 5.dp)
                    )
                    Text(
                        text = if (chave.tipo == 1) "Chave Normal" else "Chave Tetra",
                        fontSize = 16.sp,
                        modifier = Modifier.padding(bottom = 5.dp)
                    )
                    Text(
                        text = "Quantidade: ${chave.quantidade}",
                        fontSize = 14.sp,
                        modifier = Modifier.padding(bottom = 5.dp)
                    )
                    Text(
                        text = "Cliente: ${chave.nomecliente}",
                        fontSize = 16.sp,
                        modifier = Modifier.padding(bottom = 5.dp)
                    )
                    Text(
                        text = "#${chave.id}",
                        fontSize = 14.sp,
                        modifier = Modifier.padding(bottom = 5.dp)
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 5.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "R$ ${chave.preco}",
                            fontSize = 16.sp
                        )
                        Text(
                            text = when (chave.formaPagamento) {
                                1 -> "Dinheiro"
                                2 -> "Cartão"
                                3 -> "Pix"
                                else -> "Não informado"
                            },
                            fontSize = 16.sp
                        )
                        Text(
                            text = if (chave.pago) "Pago" else "Não pago",
                            color = if (chave.concluido) Color.Green else Color.Red,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                    Text(
                        text = if (chave.concluido) "Concluído" else "Pendente",
                        color = if (chave.concluido) Color.Green else Color.Yellow,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

// Tela de inclusão
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaIncluirChaves() {
    var nomeCliente by remember { mutableStateOf("") }
    var modelochave by remember { mutableStateOf("") }
    var quantidade by remember { mutableStateOf("") }

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
            value = nomeCliente,
            onValueChange = { nomeCliente = it },
            label = { Text("Nome do Cliente") },
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = modelochave,
            onValueChange = { modelochave = it },
            label = { Text("Modelo da Chave") },
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = quantidade,
            onValueChange = { quantidade = it },
            label = { Text("Quantidade") },
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { /* Adicionar ação de salvar */ }) {
            Text("Adicionar Pedido")
        }
    }
}

// Botão flutuante
@Composable
fun FloatButton(navController: NavController) {
    FloatingActionButton(onClick = {
        navController.navigate(ChavesRota.TELA_INCLUIR_CHAVES_ROTA)
    }) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Adicionar"
        )
    }
}

// Classe de dados
data class ChavesAfazer(
    var pago: Boolean,
    var modelochave: String,
    var nomecliente: String,
    var concluido: Boolean = false,
    var preco: Double,
    var formaPagamento: Int? = null,
    var tipo: Int, // 1 = Normal, 2 = Tetra
    var quantidade: Int,
    var id: Int? = null
)
