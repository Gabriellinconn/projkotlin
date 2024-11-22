import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sapato")
data class Sapato(
    @PrimaryKey(autoGenerate = true) var sapatoId: Int,
    val nomeSapato: String,
    val descricao: String,
    val concluido: Boolean,
    val preco: Double,
    val formaPagamento: Int,
    val par: Boolean,
    val pago: Boolean
)
