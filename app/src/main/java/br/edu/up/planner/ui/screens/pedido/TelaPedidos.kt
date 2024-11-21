package br.edu.up.planner

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.edu.up.planner.ui.theme.CorDoTitulo
import kotlinx.coroutines.launch
import androidx.compose.material3.OutlinedTextField as OutlinedTextField1
import androidx.compose.material3.Text as Text1


@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun TelaPedidos(drawerState: DrawerState) {

    var nomeCliente by remember { mutableStateOf("") }
    var tipoSapato by remember { mutableStateOf("") }
    var tarefa by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .background(Color(0xFFF0F0F0)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val coroutineScope = rememberCoroutineScope()

        TopAppBar(
            navigationIcon = {
                IconButton(onClick = {
                    coroutineScope.launch {
                        drawerState.open()
                    }
                }) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = "=",
                        tint = Color.White,
                        modifier = Modifier.size(40.dp))
                }
            },
            title = {
                Text(text = "Sapataria e Chaveiro Botânico ", fontSize = 22.sp,
                    color = Color.White,
                    fontWeight = FontWeight(600),
                    modifier = Modifier.padding(20.dp)
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(CorDoTitulo)
        )

        Text1(text = "Adicionar Pedido", fontSize = 24.sp, color = Color.Black)
        Spacer(modifier = Modifier.height(16.dp))


        OutlinedTextField1(
            value = nomeCliente,
            onValueChange = { nomeCliente = it },
            label = { Text1(text = "Nome do Cliente") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedLabelColor = Color.Blue,
                unfocusedLabelColor = Color.Gray
            )
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Campo para o tipo de sapato
        OutlinedTextField1(
            value = tipoSapato,
            onValueChange = { tipoSapato = it },
            label = { Text1(text = "Tipo de Sapato") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedLabelColor = Color.Blue,
                unfocusedLabelColor = Color.Gray
            )
        )
        Spacer(modifier = Modifier.height(16.dp))


        OutlinedTextField1(
            value = tarefa,
            onValueChange = { tarefa = it },
            label = { Text1("O que precisa fazer") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            colors = TextFieldDefaults.run {
                outlinedTextFieldColors(
                    focusedLabelColor = Color.Blue,
                    unfocusedLabelColor = Color.Gray,
                )
            }
        )
        Spacer(modifier = Modifier.height(16.dp))


        Button(
            onClick = { /* Ação para adicionar o pedido */ },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF3D72D)),
            modifier = Modifier
                .align(Alignment.End)
                .padding(16.dp)
        ) {
            Text1("Adicionar Pedido")
            }
        }
}