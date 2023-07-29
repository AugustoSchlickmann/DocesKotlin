package com.augusto.doceskotlin.singletons

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Button
import android.widget.Toast
import com.augusto.doceskotlin.activities.EncomendaActivity
import com.augusto.doceskotlin.activities.MainActivity
import com.augusto.doceskotlin.adapters.RecyclerViewInicioAdapter
import com.augusto.doceskotlin.fragments.InicioFragment
import com.augusto.doceskotlin.fragments.ListaDocesFragment
import com.augusto.doceskotlin.objetos.Doce
import com.augusto.doceskotlin.objetos.Encomenda
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import java.util.Calendar

object OperacoesFirebase {
    fun salvarNovaEncomenda(novaEncomenda: Encomenda, context: Context?, button: Button) {
        Firebase.firestore.collection("ClientesKotlin").document(novaEncomenda.cliente!!.nome!!)
            .set(novaEncomenda.cliente!!)
            .addOnCompleteListener {
                Firebase.firestore.collection("EncomendasKotlin").add(novaEncomenda).addOnCompleteListener {
                    Toast.makeText(context, "Encomenda Salva", Toast.LENGTH_SHORT).show()
                    val intent = Intent(context, MainActivity::class.java)
                    context!!.startActivity(intent)
                    (context as Activity).finish()
                    button.isEnabled = true
                }.addOnFailureListener {
                    Toast.makeText(context, "Falha", Toast.LENGTH_SHORT).show()
                    button.isEnabled = true
                }
            }.addOnFailureListener {
                Toast.makeText(context, "Falha", Toast.LENGTH_SHORT).show()
                button.isEnabled = true
            }
    }

    fun pegarProximasEncomendas(recyclerViewAdapter: RecyclerViewInicioAdapter, inicioFragment: InicioFragment) {
        val hoje = Calendar.getInstance()
        hoje.set(Calendar.HOUR_OF_DAY, 0)
        hoje.set(Calendar.MINUTE, 0)
        hoje.set(Calendar.SECOND, 0)

        Firebase.firestore.collection("EncomendasKotlin").whereGreaterThanOrEqualTo("data", hoje.time)
            .orderBy("data").get().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    juntarEncomendas(task, inicioFragment, recyclerViewAdapter)
                }
            }

    }

    fun pegarEncomendasSemana(
        inicio: Calendar,
        fim: Calendar,
        recyclerViewAdapter: RecyclerViewInicioAdapter,
        inicioFragment: InicioFragment
    ) {
        Firebase.firestore.collection("EncomendasKotlin").whereGreaterThanOrEqualTo("data", inicio.time)
            .whereLessThanOrEqualTo("data", fim.time).orderBy("data").get().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    juntarEncomendas(task, inicioFragment, recyclerViewAdapter)
                }
            }

    }

    private fun juntarEncomendas(
        task: Task<QuerySnapshot>,
        inicioFragment: InicioFragment,
        recyclerViewAdapter: RecyclerViewInicioAdapter
    ) {
        for (document in task.result) {
            var quantidadeDocesEncomenda = 0
            var valorEncomenda = 0.0
            val encomenda: Encomenda = document.toObject()
            for (doces in encomenda.doces!!) {
                quantidadeDocesEncomenda += doces.quantidadeDoce
                valorEncomenda += doces.quantidadeDoce * doces.valorDoce!!
            }
            encomenda.setValorEncomenda(valorEncomenda)
            encomenda.setQuantidadeDocesEncomenda(quantidadeDocesEncomenda)
            encomenda.setId(document.id)
            recyclerViewAdapter.lista?.add(encomenda)
        }
        inicioFragment.somarValoresEncomendas()
    }

    fun pegarEncomendoPorId(idEncomenda: String, encomendaActivity: EncomendaActivity) {
        Firebase.firestore.collection("EncomendasKotlin").document(idEncomenda).get().addOnSuccessListener { task ->
            if (task.exists()) {
                val encomenda: Encomenda = task.toObject()!!
                encomendaActivity.colocarDados(encomenda)
            } else {
                Toast.makeText(encomendaActivity, "Erro procurando encomenda", Toast.LENGTH_SHORT).show()
                encomendaActivity.finish()
            }
        }.addOnFailureListener {
            Toast.makeText(encomendaActivity, "Erro procurando encomenda", Toast.LENGTH_SHORT).show()
            encomendaActivity.finish()
        }
    }

    fun pegarDocesAFazer(listaDocesFragment: ListaDocesFragment) {
        val hoje = Calendar.getInstance()
        hoje.set(Calendar.HOUR_OF_DAY, 0)
        hoje.set(Calendar.MINUTE, 0)
        hoje.set(Calendar.SECOND, 0)
        var docesAFazer: MutableList<Doce> = ArrayList()
        var quantidadeDoces = 0
        var valorTotal = 0.0

        Firebase.firestore.collection("EncomendasKotlin")
            .whereGreaterThanOrEqualTo("data", hoje.time)
            .whereEqualTo("feita", false)
            .orderBy("data").get().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    for (document in task.result) {

                        println(document.data)
                        println(document.data.get("doces"))
                        println(document.data.get("doces")!!.javaClass)
//                        document.data.get("doces").let { docesDaEncomenda ->
//                            for (doce in docesDaEncomenda as ArrayList<Doce>) {
//                                if (docesAFazer.contains(doce)) {
//                                    docesAFazer[docesAFazer.indexOf(doce)].quantidadeDoce += doce.quantidadeDoce
//                                } else {
//                                    docesAFazer.add(doce)
//                                }
//                                quantidadeDoces += doce.quantidadeDoce
//                                valorTotal += doce.quantidadeDoce * doce.valorDoce!!
//                            }
//
//                        }
                    }
                  //  listaDocesFragment.mostrarDocesAFazer(docesAFazer, quantidadeDoces, valorTotal)
                }
            }

    }

}