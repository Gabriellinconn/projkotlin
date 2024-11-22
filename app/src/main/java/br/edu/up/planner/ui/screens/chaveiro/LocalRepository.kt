package br.edu.up.planner.ui.screens.chaveiro

import kotlinx.coroutines.flow.Flow

class LocalRepositorya(
    private val dao : ChaveDAO
) : IRepository {

    override fun listarChaves(): Flow<List<Chave>> {
        return dao.listarChaves()
    }

    override suspend fun buscarChavePorId(idx: Int): Chave {
        return dao.buscarChavePorId(idx)
    }

    override suspend fun gravarChave(chave: Chave) {
        dao.gravarChave(chave)
    }

    override suspend fun excluirChave(chave: Chave) {
        dao.excluirChave(chave)
    }

}