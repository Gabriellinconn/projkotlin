package br.edu.up.planner.ui.screens.chaveiro

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
import br.edu.up.planner.ui.screens.sapataria.SapatoViewModel
import kotlinx.coroutines.launch

// Rotas

// Rotas
object ChavesRota {
    const val TELA_LISTAR_CHAVES_ROTA = "listar_chaves"
    const val TELA_INCLUIR_CHAVES_ROTA = "incluir_chaves"
}



// Tela principal
@Composable
fun TelaChavesAfazer(drawerState: SapatoViewModel, navController: NavHostController) {

    val serviçosChaves = mutableListOf(
        Chave(pago = true, modelochave = "Papaiz", nomecliente = "pedro", preco = 18.00, formaPagamento = 1, tipo = 1, quantidade = 2, concluido = false, chaveId = 4),
        Chave(pago = true, modelochave = "Papaiz", nomecliente = "pedro", preco = 18.00, formaPagamento = 1, tipo = 1, quantidade = 2, concluido = false, chaveId = 5),
    )

    val navCtrlChave = rememberNavController()

    Scaffold(
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                NavHost(
                    navController = navCtrlChave,
                    startDestination = ChavesRota.TELA_LISTAR_CHAVES_ROTA
                ) {
                    composable(ChavesRota.TELA_LISTAR_CHAVES_ROTA) {
                        TelaListagemChaves(chavesAfazer = serviçosChaves)
                    }
                    composable(ChavesRota.TELA_INCLUIR_CHAVES_ROTA) {
                        TelaIncluirChaves(navController = navCtrlChave,(
                                viewModel()
                                )
                        )
                    }
                }
            }
        },
        floatingActionButton = { FloatButton(navController = navCtrlChave) }

    )
}

// Tela de listagem
@Composable
fun TelaListagemChaves(chavesAfazer: MutableList<Chave>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 16.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(chavesAfazer) { chave ->
            ChaveCard(chaveAfazer = chave)
        }
    }
}

// Card de um sapato
@Composable
fun ChaveCard(chaveAfazer: Chave) {
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
                text = chaveAfazer.nomecliente,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 5.dp)
            )
            Text(
                text = chaveAfazer.modelochave,
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom = 5.dp)
            )
            Text(
                text = "ID: #${chaveAfazer.chaveId}",
                fontSize = 14.sp,
                modifier = Modifier.padding(bottom = 5.dp)
            )
            Text(
                text = "Quantidade: #${chaveAfazer.quantidade}",
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom = 5.dp)
            )
            Text(
                text = if (chaveAfazer.tipo == 1 ) "Normal" else "Tetra",
                fontSize = 16.sp
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "R$ ${chaveAfazer.preco}",
                    fontSize = 16.sp
                )
                Text(
                    text = when (chaveAfazer.formaPagamento) {
                        1 -> "Dinheiro"
                        2 -> "Cartão"
                        3 -> "Pix"
                        else -> "Não informado"
                    },
                    fontSize = 16.sp
                )
                Text(
                    text = if (chaveAfazer.pago) "Pago" else "Não pago",
                    color = if (chaveAfazer.concluido) Color.Green else Color.Red,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Text(
                text = if (chaveAfazer.concluido) "Concluído" else "Pendente",
                color = if (chaveAfazer.concluido) Color.Green else Color.Yellow,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

// Tela de inclusão
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaIncluirChaves(navController: NavController, chaveId: Int? = null) {
    // Instanciar o ViewModel dentro do Composable corretamente
    val viewModel: ChaveViewModel = viewModel()

    var pago by remember { mutableStateOf(false) }
    var modelochave by remember { mutableStateOf("") }
    var nomecliente by remember { mutableStateOf("") }
    var concluido by remember { mutableStateOf((false)) }
    var formaPagamento by remember { mutableStateOf(0) }
    var preco by remember { mutableStateOf((0.0)) }
    var tipo by remember { mutableStateOf(0) }
    var quantidade by remember { mutableStateOf(0) }
    var chaveId by remember { mutableStateOf(0) }


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
            value = modelochave,
            onValueChange = { modelochave = it },
            label = { Text("Modelo da Chave") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = nomecliente,
            onValueChange = { nomecliente = it },
            label = { Text("Nome do Cliente") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            coroutineScope.launch {
                // Criar o objeto sapato
                val chaveSalvar = Chave(
                    chaveId = chaveId,
                    pago = pago,
                    modelochave = modelochave,
                    nomecliente = nomecliente,
                    preco = preco,
                    formaPagamento = formaPagamento,
                    concluido = concluido,
                    tipo = tipo,
                    quantidade = quantidade

                )

                // Salvar no ViewModel ou repositório
                viewModel.gravar(chaveSalvar)
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
        navController.navigate(ChavesRota.TELA_INCLUIR_CHAVES_ROTA)
    }) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Adicionar"
        )
    }}


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

