package com.augusto.doceskotlin

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import com.augusto.doceskotlin.adapters.RecyclerViewCadastrarEncomendaAdapter
import com.augusto.doceskotlin.adapters.SpinnerDocesAdapter
import com.augusto.doceskotlin.databinding.FragmentCadastrarEncomendaBinding
import com.augusto.doceskotlin.objetos.Doce
import com.augusto.doceskotlin.singletons.ListaDeDoces

class EncomendaMapper(bind: FragmentCadastrarEncomendaBinding) : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private var nomeCliente: EditText = bind.FragmentCadastrarEncomendaEditTextNomeCliente
    private var telefoneCliente: EditText = bind.FragmentCadastrarEncomendaEditTextTelefoneCliente
    private var data: EditText = bind.FragmentCadastrarEncomendaEditTextData
    private var hora: EditText = bind.FragmentCadastrarEncomendaEditTextHora
    private var spinner: Spinner = bind.FragmentCadastrarEncomendaSpinnerDoces
    private var recyclerView: RecyclerView = bind.FragmentCadastrarEncomendaRecyclerView
    private var botaoSalvar: Button = bind.FragmentCadastrarEncomendaBotaoSalvar
    private var textViewValorTotal: TextView = bind.FragmentCadastrarEncomendaTextViewValorTotal

    private var recyclerViewAdapter: RecyclerViewCadastrarEncomendaAdapter? = RecyclerViewCadastrarEncomendaAdapter(bind.root.context, ArrayList())
    private var valorTotal: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        spinner.adapter = SpinnerDocesAdapter(applicationContext, ListaDeDoces.pegarLista()!!)
        spinner.onItemSelectedListener = this
        spinner.setSelection(spinner!!.adapter.count)
        recyclerViewAdapter = RecyclerViewCadastrarEncomendaAdapter(applicationContext, ArrayList())
        recyclerView.adapter = recyclerViewAdapter
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        if (parent != null && position < parent.count) {
            val doceSelecionado = parent.getItemAtPosition(position) as Doce
            if (!recyclerViewAdapter!!.lista.contains(doceSelecionado)) {
                val doceAdicionado = doceSelecionado.copy()
                val criarDialogo = DialogoDoce()
                val dialog = criarDialogo.adicionarDoce(doceAdicionado, applicationContext)

                criarDialogo.botao!!.setOnClickListener {
                    try {
                        if (criarDialogo.qtd!!.text.toString().toInt() > 0) {

                            doceAdicionado.quantidadeDoce =
                                criarDialogo.qtd!!.text.toString().toInt()
                            recyclerViewAdapter!!.lista.add(doceAdicionado)
                            recyclerViewAdapter!!.notifyItemInserted(recyclerViewAdapter!!.itemCount)
                            valorTotal += doceAdicionado.valorDoce * doceAdicionado.quantidadeDoce
                            textViewValorTotal!!.text = "R$: " + "%.2f".format(valorTotal)
                            dialog.dismiss()

                        } else {
                            Toast.makeText(
                                applicationContext,
                                getString(R.string.ToastDadosInvalidos),
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        }
                    } catch (e: Exception) {
                        Toast.makeText(
                            applicationContext,
                            getString(R.string.ToastDadosInvalidos),
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                }
            } else {
                Toast.makeText(
                    applicationContext,
                    "Doce já adicionado",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

}