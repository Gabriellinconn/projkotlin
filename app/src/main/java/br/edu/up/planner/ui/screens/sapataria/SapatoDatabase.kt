package br.edu.up.planner.ui.screens.sapataria

import Sapato
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// Configuração do banco de dados Room
@Database(entities = [Sapato::class], version = 1, exportSchema = false)
abstract class SapatoDatabase : RoomDatabase() {

    abstract fun sapatoDAO(): SapatoDAO

    companion object {
        // Instância Singleton do banco de dados
        @Volatile
        private var INSTANCE: SapatoDatabase? = null

        fun abrirBancoDeDados(context: Context): SapatoDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SapatoDatabase::class.java,
                    "sapato.db"
                )
                    // Esta linha é útil durante o desenvolvimento, mas deve ser removida em produção
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
