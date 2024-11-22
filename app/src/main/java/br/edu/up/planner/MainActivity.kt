package br.edu.up.planner


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import br.edu.up.planner.ui.screens.sapataria.LocalRepository
import br.edu.up.planner.ui.screens.sapataria.RemoteRepository
import br.edu.up.planner.ui.screens.sapataria.SapatariaNavHost
import br.edu.up.planner.ui.screens.sapataria.SapatoDatabase.Companion.abrirBancoDeDados
import br.edu.up.planner.ui.screens.sapataria.SapatoViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val isLocal = false

        val db = abrirBancoDeDados(this)
        val localRepository = LocalRepository(db.sapatoDAO())


        // Inicializar o viewModel com um tipo correto antes de entrar na condição
        val viewModel: SapatoViewModel

        // Verifique qual repositório utilizar baseado no valor de isLocal
        viewModel = if (isLocal) {
            // Inicialize com o repositório local
            SapatoViewModel(localRepository)
        } else {
            // Inicialize com o repositório remoto (certifique-se de que RemoteRepository esteja implementado)
            val remoteRepository = RemoteRepository()  // Garanta que RemoteRepository esteja implementado
            SapatoViewModel(remoteRepository)
        }

        setContent {
            SapatariaNavHost(viewModel)
        }
    }
}
