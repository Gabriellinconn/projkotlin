package br.edu.up.planner.ui.screens.sapataria

import Sapato
import kotlinx.coroutines.flow.Flow

interface IRepository {
    fun listarSapatos(): Flow<List<Sapato>>
    suspend fun buscarSapatoPorId(idx: Int): Sapato?
    suspend fun gravarSapato(sapato: Sapato)
    suspend fun excluirSapato(sapato: Sapato)
}