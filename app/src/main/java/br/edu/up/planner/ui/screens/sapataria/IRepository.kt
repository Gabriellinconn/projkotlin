package br.edu.up.planner.ui.screens.sapataria

import kotlinx.coroutines.flow.Flow

interface IRepository {
    fun listarAfazeres(): Flow<List<Sapato>>
    suspend fun buscarSapatoPorId(idx: Int): Sapato?
    suspend fun gravarSapato(afazer: Sapato)
    suspend fun excluirSapato(afazer: Sapato)
}