package br.edu.up.planner.ui.screens.chaveiro

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface ChaveDAO {

    // Listar todas as chaves
    @Query("SELECT * FROM chave")
    fun listarChaves(): Flow<List<Chave>>

    // Buscar chave por ID
    @Query("SELECT * FROM chave WHERE chaveId = :idx LIMIT 1")
    suspend fun buscarChavePorId(idx: Int): Chave

    // Inserir ou atualizar uma chave
    @Upsert
    suspend fun gravarChave(chave: Chave)

    // Excluir uma chave
    @Delete
    suspend fun excluirChave(chave: Chave)
}
