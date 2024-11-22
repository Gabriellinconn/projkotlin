package br.edu.up.planner.ui.screens.sapataria

import Sapato
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class RemoteRepository() : IRepository {

    var db = FirebaseFirestore.getInstance()
    var sapatoCollection = db.collection("sapatos")

    //FUNÇÃO NÃO REATIVA OU SEJA NÃO ATUALIZA
//    override fun listarAfazeres(): Flow<List<Afazer>> = flow {
//        val dados = afazerCollection.get().await()
//        val afazeres = dados.documents.mapNotNull { dado ->
//            dado.toObject(Afazer::class.java)
//        }
//        emit(afazeres)
//    }

    //FUNÇÃO REATIVA
    override fun listarSapatos(): Flow<List<Sapato>> = callbackFlow {
        val listener = sapatoCollection.addSnapshotListener {
                dados, erros ->
            if (erros != null){
                close(erros)
                return@addSnapshotListener
            }
            if (dados != null){
                val sapato = dados.documents.mapNotNull { dado ->
                    dado.toObject(Sapato::class.java)
                }
                trySend(sapato).isSuccess
            }
        }
        awaitClose{ listener.remove()}
    }

    suspend fun getId(): Int{
        val dados = sapatoCollection.get().await()
        //Recupera o maior id do firestore no format inteiro
        val maxId = dados.documents.mapNotNull {
            it.getLong("id")?.toInt()
        }.maxOrNull() ?: 0
        return maxId + 1
    }

    override suspend fun gravarSapato(sapato: Sapato) {
        val document: DocumentReference
        if (sapato.sapatoId == null){ // Inclusão
            sapato.sapatoId = getId()
            document = sapatoCollection.document(sapato.sapatoId.toString())
        } else { // Alteração
            document = sapatoCollection.document(sapato.sapatoId.toString())
        }
        document.set(sapato).await()
    }

    override suspend fun buscarSapatoPorId(idx: Int): Sapato? {
        val dados = sapatoCollection.document(idx.toString()).get().await()
//        val sapato = dados.toObject(Sapato::class.java)
//        return sapato
        return dados.toObject(Sapato::class.java)
    }

    override suspend fun excluirSapato(sapato: Sapato) {
        sapatoCollection.document(sapato.sapatoId.toString()).delete().await()
    }
}