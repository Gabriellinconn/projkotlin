package br.edu.up.planner.ui.screens.sapataria

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Sapato(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val nomeSapato: String,
    val descricao: String,
    val concluido: Boolean = false,
    val preco: Double,
    val formaPagamento: Int,
    val par: Boolean = false,
    val pago: Boolean = false

)

