package br.edu.up.planner.ui.screens.chaveiro

import kotlinx.coroutines.flow.Flow

interface IRepository {
    fun listarChaves(): Flow<List<Chave>>
    suspend fun buscarChavePorId(idx: Int): Chave?
    suspend fun gravarChave(chave: Chave)
    suspend fun excluirChave(chave: Chave)
}