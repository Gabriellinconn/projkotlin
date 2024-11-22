package br.edu.up.planner.ui.screens.chaveiro

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "chave")
data class Chave(
    @PrimaryKey(autoGenerate = true)
var pago: Boolean,
var modelochave: String,
var nomecliente: String,
var concluido: Boolean = false,
var preco: Double,
var formaPagamento: Int? = null,
var tipo: Int, // 1 = Normal, 2 = Tetra
var quantidade: Int,
var chaveId: Int? = null
)




