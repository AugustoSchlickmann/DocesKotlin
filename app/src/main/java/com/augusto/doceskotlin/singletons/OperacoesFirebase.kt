package com.augusto.doceskotlin.singletons

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.augusto.doceskotlin.activities.ClienteActivity
import com.augusto.doceskotlin.activities.EncomendaActivity
import com.augusto.doceskotlin.activities.MainActivity
import com.augusto.doceskotlin.adapters.ClientesRecyclerViewAdapter
import com.augusto.doceskotlin.adapters.DocesRecyclerViewAdapter
import com.augusto.doceskotlin.adapters.InicioRecyclerViewAdapter
import com.augusto.doceskotlin.fragments.InicioFragment
import com.augusto.doceskotlin.fragments.ListaDocesFragment
import com.augusto.doceskotlin.objetos.Cliente
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
                    Toast.makeText(context, "Encomenda salva", Toast.LENGTH_SHORT).show()
                    val intent = Intent(context, MainActivity::class.java)
                    context!!.startActivity(intent)
                    (context as Activity).finish()

                }.addOnFailureListener {
                    Toast.makeText(context, "Falha no banco de dados", Toast.LENGTH_SHORT).show()
                    button.isEnabled = true
                }
            }.addOnFailureListener {
                Toast.makeText(context, "Falha no banco de dados", Toast.LENGTH_SHORT).show()
                button.isEnabled = true
            }
    }

    fun atualizarEncomenda(encomenda: Encomenda, context: Context?, button: Button) {
        Firebase.firestore.collection("ClientesKotlin").document(encomenda.cliente!!.nome!!)
            .set(encomenda.cliente!!)
            .addOnCompleteListener {
                Firebase.firestore.collection("EncomendasKotlin").document(encomenda.id!!).set(encomenda)
                    .addOnCompleteListener {
                        Toast.makeText(context, "Encomenda atualizada", Toast.LENGTH_SHORT).show()

                    }.addOnFailureListener {
                    Toast.makeText(context, "Falha no banco de dados", Toast.LENGTH_SHORT).show()
                    button.isEnabled = true
                }
            }.addOnFailureListener {
                Toast.makeText(context, "Falha no banco de dados", Toast.LENGTH_SHORT).show()
                button.isEnabled = true
            }
    }

    fun pegarProximasEncomendas(recyclerViewAdapter: InicioRecyclerViewAdapter, inicioFragment: InicioFragment) {
        val hoje = Calendar.getInstance()
        hoje.set(Calendar.HOUR_OF_DAY, 0)
        hoje.set(Calendar.MINUTE, 0)
        hoje.set(Calendar.SECOND, 0)
        inicioFragment.progressBar?.visibility = View.VISIBLE
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
        recyclerViewAdapter: InicioRecyclerViewAdapter,
        inicioFragment: InicioFragment
    ) {
        inicioFragment.progressBar?.visibility = View.VISIBLE
        Firebase.firestore.collection("EncomendasKotlin").whereGreaterThanOrEqualTo("data", inicio.time)
            .whereLessThanOrEqualTo("data", fim.time).orderBy("data").get().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    juntarEncomendas(task, inicioFragment, recyclerViewAdapter)
                }
            }
    }

    fun pegarEncomendasDoCliente(
        recyclerViewAdapter: InicioRecyclerViewAdapter,
        inicioFragment: InicioFragment,
        cliente: Cliente?
    ) {
        inicioFragment.progressBar?.visibility = View.VISIBLE
        Firebase.firestore.collection("EncomendasKotlin").whereEqualTo("cliente.nome", cliente?.nome).orderBy("data").get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    juntarEncomendas(task, inicioFragment, recyclerViewAdapter)
                }
            }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun juntarEncomendas(
        task: Task<QuerySnapshot>,
        inicioFragment: InicioFragment,
        recyclerViewAdapter: InicioRecyclerViewAdapter
    ) {
        recyclerViewAdapter.lista.clear()
        recyclerViewAdapter.notifyDataSetChanged()
        for (document in task.result) {
            val encomenda: Encomenda = document.toObject()
            encomenda.id = document.id
            recyclerViewAdapter.lista.add(encomenda)
            recyclerViewAdapter.notifyItemInserted(recyclerViewAdapter.itemCount)

        }
        inicioFragment.somarValoresEncomendas()
    }

    fun pegarDocesAFazer(recyclerViewAdapter: DocesRecyclerViewAdapter, listaDocesFragment: ListaDocesFragment) {

        val hoje = Calendar.getInstance()
        hoje.set(Calendar.HOUR_OF_DAY, 0)
        hoje.set(Calendar.MINUTE, 0)
        hoje.set(Calendar.SECOND, 0)

        var quantidadeDoces = 0
        var valorTotal = 0.0

        Firebase.firestore.collection("EncomendasKotlin")
            .whereGreaterThanOrEqualTo("data", hoje.time)
            .whereEqualTo("feita", false)
            .orderBy("data").get().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    for (document in task.result) {
                        val encomenda: Encomenda = document.toObject()
                        for (doce in encomenda.doces!!) {
                            if (recyclerViewAdapter.lista!!.contains(doce)) {
                                recyclerViewAdapter.lista!![recyclerViewAdapter.lista!!.indexOf(doce)].quantidadeDoce += doce.quantidadeDoce
                            } else {
                                recyclerViewAdapter.lista!!.add(doce)
                                recyclerViewAdapter.notifyItemInserted(recyclerViewAdapter.itemCount)
                            }
                        }
                        quantidadeDoces += encomenda.quantidadeDocesEncomenda
                        valorTotal += encomenda.valorEncomenda
                    }
                    listaDocesFragment.mostrarDocesAFazer(quantidadeDoces, valorTotal)
                }
            }

    }

    fun pegarClientes(recyclerViewAdapter: ClientesRecyclerViewAdapter?) {
        Firebase.firestore.collection("ClientesKotlin").orderBy("nome").limit(50).get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                for (document in task.result) {
                    val cliente: Cliente = document.toObject()
                    recyclerViewAdapter?.clientes!!.add(cliente)
                    recyclerViewAdapter.notifyItemInserted(recyclerViewAdapter.itemCount)
                }
            }
        }
    }
    fun pegarProximosClientes(recyclerViewAdapter: ClientesRecyclerViewAdapter?, nomeUltimo : String) {
        Firebase.firestore.collection("ClientesKotlin").orderBy("nome").limit(50).startAfter(nomeUltimo).get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                for (document in task.result) {
                    val cliente: Cliente = document.toObject()
                    recyclerViewAdapter?.clientes!!.add(cliente)
                    recyclerViewAdapter.notifyItemInserted(recyclerViewAdapter.itemCount)
                }
            }
        }
    }

    fun marcarEncomendaFeita(encomenda: Encomenda, context: Context) {
        if (encomenda.feita == false) {
            encomenda.feita = true
            Firebase.firestore.collection("EncomendasKotlin").document(encomenda.id!!).update("feita", true)
                .addOnCompleteListener {
                    Toast.makeText(context, "Encomenda Feita", Toast.LENGTH_SHORT).show()
                }
        } else {
            encomenda.feita = false
            Firebase.firestore.collection("EncomendasKotlin").document(encomenda.id!!).update("feita", false)
                .addOnCompleteListener {
                    Toast.makeText(context, "Encomenda Não Feita", Toast.LENGTH_SHORT).show()
                }
        }

    }

    fun excluirEncomenda(encomenda: Encomenda, encomendaActivity: EncomendaActivity) {
        Firebase.firestore.collection("EncomendasKotlin").document(encomenda.id!!).delete()
            .addOnCompleteListener {
                Toast.makeText(encomendaActivity, "Encomenda Deletada", Toast.LENGTH_SHORT).show()
                encomendaActivity.finish()
            }.addOnFailureListener {
                Toast.makeText(encomendaActivity, "Erro ao Deletar", Toast.LENGTH_SHORT).show()
            }
    }

    fun atualizarCliente(cliente: Cliente, clienteActivity: ClienteActivity) {
        Firebase.firestore.collection("ClientesKotlin").document(cliente.nome!!).set(cliente).addOnCompleteListener {
            Toast.makeText(clienteActivity, "Cliente atualizado", Toast.LENGTH_SHORT).show()
        }
    }

    fun pegarEncomendasPorNomeCliente(recyclerViewAdapter: InicioRecyclerViewAdapter, inicioFragment: InicioFragment, nome: String) {
        Firebase.firestore.collection("EncomendasKotlin").whereEqualTo("cliente.nome",nome).get().addOnCompleteListener{
            task -> if (task.isSuccessful) {
                juntarEncomendas(task, inicioFragment, recyclerViewAdapter)
            }
        }
    }

}