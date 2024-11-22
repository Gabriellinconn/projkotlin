package br.edu.up.planner.ui.screens.sapataria

import Sapato
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Sapato::class], version = 1)
abstract class SapatoDatabase: RoomDatabase(){

    abstract fun sapatoDao(): SapatoDAO

    companion object{
        fun abrirBancoDeDados(context: Context): SapatoDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                SapatoDatabase::class.java, "sapato.db"
            ).build()
        }
    }
}