package br.edu.up.planner.ui.screens.sapataria

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Sapato(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val titulo: String,
    val descricao: String,
    val concluido: Boolean = false
){
    constructor() : this(null, "", "", false)
}