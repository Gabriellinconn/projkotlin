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

        //val context = LocalContext.current
        val db = abrirBancoDeDados(this)
        //val viewModel = AfazerViewModel(db.afazerDao())
        val localRepository = LocalRepository(db.sapatoDao())
        val remoteRepository = RemoteRepository()
//        val minimalAPIRepository = MinimalAPIRepository()
        //val viewModel = AfazerViewModel(localRepository)

        val viewModel: SapatoViewModel
        if (isLocal){
            viewModel = SapatoViewModel(localRepository)
        } else {
            viewModel = SapatoViewModel(remoteRepository)
        }

        setContent {
            SapatariaNavHost(viewModel)
        }
    }
}
