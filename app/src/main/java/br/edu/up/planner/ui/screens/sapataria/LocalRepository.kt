package br.edu.up.planner.ui.screens.sapataria

import Sapato
import kotlinx.coroutines.flow.Flow

class LocalRepository(
    private val dao : SapatoDAO
) : IRepository {

    override fun listarSapatos(): Flow<List<Sapato>> {
        return dao.listarSapatos()
    }

    override suspend fun buscarSapatoPorId(idx: Int): Sapato {
        return dao.buscarSapatoPorId(idx)
    }

    override suspend fun gravarSapato(sapato: Sapato) {
        dao.gravarSapato(sapato)
    }

    override suspend fun excluirSapato(sapato: Sapato) {
        dao.excluirSapato(sapato)
    }

}