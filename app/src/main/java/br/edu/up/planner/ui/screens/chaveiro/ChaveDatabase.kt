package br.edu.up.planner.ui.screens.chaveiro

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//INFRAESTRUTURA DE BANCO DE DADOS
//https://developer.android.com/training/data-storage/room

// Banco de dados
//@Database(
//    Tabelas no banco de dados
//    entities = [
//        Afazer::class
//        Livro::class,
//        Pessoa::class...
//    ],
//    version = 1
//)
@Database(entities = [Chave::class], version = 1)
abstract class ChaveDatabase: RoomDatabase(){

    abstract fun chaveDAO(): ChaveDAO

    companion object{
        fun abrirBancoDeDados(context: Context): ChaveDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                ChaveDatabase::class.java, "sapato.db"
            ).build()
        }
    }
}