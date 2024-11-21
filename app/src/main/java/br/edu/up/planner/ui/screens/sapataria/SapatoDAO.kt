package br.edu.up.planner.ui.screens.sapataria

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

//INFRAESTRUTURA DE BANCO DE DADOS
//https://developer.android.com/training/data-storage/room

// Objetos de manipulação do banco de dados
@Dao
interface SapatoDAO {

    //Listar
    @Query("select * from sapato")
    fun listarSapatos(): Flow<List<Sapato>>
    //suspend fun listarAfazeres(): List<Afazer>

    //Buscar por Id
    @Query("select * from sapato where id = :idx")
    suspend fun buscarSapatoPorId(idx: Int): Sapato

    //Gravar @Update @Insert
    @Upsert
    suspend fun gravarSapato(afazer: Sapato)

    //Excluir
    @Delete
    suspend fun excluirSapato(afazer: Sapato)

}