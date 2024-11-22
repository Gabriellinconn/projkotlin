package br.edu.up.planner.ui.screens.sapataria

import Sapato
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

// DAO (Data Access Object) para manipulação do banco de dados
@Dao
interface SapatoDAO {

    // Listar todos os sapatos
    @Query("SELECT * FROM sapato")
    fun listarSapatos(): Flow<List<Sapato>>

    // Buscar sapato por ID
    @Query("SELECT * FROM sapato WHERE sapatoId = :idx")
    suspend fun buscarSapatoPorId(idx: Int): Sapato

    // Gravar (inserir ou atualizar)
    @Upsert
    suspend fun gravarSapato(sapato: Sapato)

    // Excluir
    @Delete
    suspend fun excluirSapato(sapato: Sapato)
}
