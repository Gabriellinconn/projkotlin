package br.edu.up.planner.ui.screens.sapataria

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
@Database(entities = [Sapato::class], version = 1)
abstract class SapatoDatabase: RoomDatabase(){

    abstract fun afazerDao(): SapatoDAO

    companion object{
        fun abrirBancoDeDados(context: Context): SapatoDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                SapatoDatabase::class.java, "sapato.db"
            ).build()
        }
    }
}