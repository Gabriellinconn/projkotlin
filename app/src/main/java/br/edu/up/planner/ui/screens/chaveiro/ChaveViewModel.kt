package br.edu.up.planner.ui.screens.chaveiro

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ChaveViewModel(
    private val repository : IRepository
) : ViewModel(){

    private val _chaves = MutableStateFlow<List<Chave>>(emptyList())
    val chaves: StateFlow<List<Chave>> get() = _chaves

    init {
        viewModelScope.launch {
            repository.listarChaves().collectLatest { listaDeChaves ->
                _chaves.value = listaDeChaves
            } //.collectLastest
        }
    }

    fun excluir(chave: Chave) {
        viewModelScope.launch {
            repository.excluirChave(chave)
        }
    }

    suspend fun buscarPorId(chaveId: Int): Chave? {
        return withContext(Dispatchers.IO){
            repository.buscarChavePorId(chaveId)
        }
    }

    fun gravar(chave: Chave) {
        viewModelScope.launch {
            repository.gravarChave(chave)
        }
    }

}