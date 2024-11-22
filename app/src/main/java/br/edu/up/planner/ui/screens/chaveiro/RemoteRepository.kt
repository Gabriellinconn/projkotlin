package br.edu.up.planner.ui.screens.chaveiro

import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class RemoteRepository() : IRepository {

    var db = FirebaseFirestore.getInstance()
    var chaveCollection = db.collection("chaves")

    //FUNÇÃO NÃO REATIVA OU SEJA NÃO ATUALIZA
//    override fun listarAfazeres(): Flow<List<Afazer>> = flow {
//        val dados = afazerCollection.get().await()
//        val afazeres = dados.documents.mapNotNull { dado ->
//            dado.toObject(Afazer::class.java)
//        }
//        emit(afazeres)
//    }

    //FUNÇÃO REATIVA
    override fun listarChaves(): Flow<List<Chave>> = callbackFlow {
        val listener = chaveCollection.addSnapshotListener {
                dados, erros ->
            if (erros != null){
                close(erros)
                return@addSnapshotListener
            }
            if (dados != null){
                val chaves = dados.documents.mapNotNull { dado ->
                    dado.toObject(Chave::class.java)
                }
                trySend(chaves).isSuccess
            }
        }
        awaitClose{ listener.remove()}
    }

    suspend fun getId(): Int{
        val dados = chaveCollection.get().await()
        //Recupera o maior id do firestore no format inteiro
        val maxId = dados.documents.mapNotNull {
            it.getLong("id")?.toInt()
        }.maxOrNull() ?: 0
        return maxId + 1
    }

    override suspend fun gravarChave(chave: Chave) {
        val document: DocumentReference
        if (chave.chaveId == null){ // Inclusão
            chave.chaveId = getId()
            document = chaveCollection.document(chave.chaveId.toString())
        } else { // Alteração
            document = chaveCollection.document(chave.chaveId.toString())
        }
        document.set(chave).await()
    }

    override suspend fun buscarChavePorId(idx: Int): Chave? {
        val dados = chaveCollection.document(idx.toString()).get().await()
//        val sapato = dados.toObject(Sapato::class.java)
//        return sapato
        return dados.toObject(Chave::class.java)
    }

    override suspend fun excluirChave(chave: Chave) {
        chaveCollection.document(chave.chaveId.toString()).delete().await()
    }
}