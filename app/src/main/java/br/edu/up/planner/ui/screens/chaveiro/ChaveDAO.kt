package br.edu.up.planner.ui.screens.chaveiro

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

//INFRAESTRUTURA DE BANCO DE DADOS
//https://developer.android.com/training/data-storage/room

// Objetos de manipulação do banco de dados
@Dao
interface ChaveDAO {

    //Listar
    @Query("select * from chave")
    fun listarChaves(): Flow<List<Chave>>
    //suspend fun listarAfazeres(): List<Afazer>

    //Buscar por Id
    @Query("select * from chave where chaveId = :idx")
    suspend fun buscarChavePorId(idx: Int): Chave

    //Gravar @Update @Insert
    @Upsert
    suspend fun gravarChave(chave: Chave)

    //Excluir
    @Delete
    suspend fun excluirChave(chave: Chave)

}