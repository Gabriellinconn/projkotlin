package br.edu.up.planner.ui.screens.sapataria

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SapatoViewModel(
    private val repository : IRepository
) : ViewModel(){

    private val _sapatos = MutableStateFlow<List<Sapato>>(emptyList())
    val sapatos: StateFlow<List<Sapato>> get() = _sapatos

    init {
        viewModelScope.launch {
            repository.listarAfazeres().collectLatest { listaDeAfazeres ->
                _sapatos.value = listaDeAfazeres
            } //.collectLastest
        }
    }

    fun excluir(sapato: Sapato) {
        viewModelScope.launch {
            repository.excluirSapato(sapato)
        }
    }

    suspend fun buscarPorId(sapatoId: Int): Sapato? {
        return withContext(Dispatchers.IO){
            repository.buscarSapatoPorId(sapatoId)
        }
    }

    fun gravar(sapato: Sapato) {
        viewModelScope.launch {
            repository.gravarSapato(sapato)
        }
    }

}