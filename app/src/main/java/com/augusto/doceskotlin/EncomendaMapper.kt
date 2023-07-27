package com.augusto.doceskotlin

import android.content.Context
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.augusto.doceskotlin.adapters.RecyclerViewCadastrarEncomendaAdapter
import com.augusto.doceskotlin.adapters.SpinnerDocesAdapter
import com.augusto.doceskotlin.databinding.FragmentCadastrarEncomendaBinding
import com.augusto.doceskotlin.objetos.Doce
import com.augusto.doceskotlin.singletons.ListaDeDoces
import java.lang.NullPointerException
import java.util.Calendar

class EncomendaMapper(bind: FragmentCadastrarEncomendaBinding, var context: Context) :
    AdapterView.OnItemSelectedListener {

    var nomeCliente: EditText = bind.FragmentCadastrarEncomendaEditTextNomeCliente
    var telefoneCliente: EditText = bind.FragmentCadastrarEncomendaEditTextTelefoneCliente
    var data: EditText = bind.FragmentCadastrarEncomendaEditTextData
    var hora: EditText = bind.FragmentCadastrarEncomendaEditTextHora
    var spinner: Spinner = bind.FragmentCadastrarEncomendaSpinnerDoces
    var botaoSalvar: Button = bind.FragmentCadastrarEncomendaBotaoSalvar
    var textViewValorTotal: TextView = bind.FragmentCadastrarEncomendaTextViewValorTotal
    var recyclerView: RecyclerView = bind.FragmentCadastrarEncomendaRecyclerView
    var recyclerViewAdapter: RecyclerViewCadastrarEncomendaAdapter? = RecyclerViewCadastrarEncomendaAdapter(bind.root.context)
    var valorTotal: Double = 0.0

    val calendario = Calendar.getInstance()

    fun setar() {

        spinner.adapter = SpinnerDocesAdapter(context, ListaDeDoces.pegarLista()!!)
        spinner.onItemSelectedListener = this
        spinner.setSelection(spinner.adapter.count)

        recyclerView.adapter = recyclerViewAdapter

        data.setOnClickListener {
            nomeCliente.clearFocus()
            telefoneCliente.clearFocus()
            Calendario().abrirCalendario(context, calendario, data)

        }

        hora.setOnClickListener {
            nomeCliente.clearFocus()
            telefoneCliente.clearFocus()
            Relogio().abrirRelogio(context, calendario, hora)
        }

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        if (parent != null && position < parent.count) {
            val doceSelecionado = parent.getItemAtPosition(position) as Doce
            if (!recyclerViewAdapter!!.lista!!.contains(doceSelecionado)) {
                val doceAdicionado = doceSelecionado.copy()
                val criarDialogo = DialogoDoce()
                val dialog = criarDialogo.adicionarDoce(doceAdicionado, context)

                criarDialogo.botao!!.setOnClickListener {
                    try {
                        if (criarDialogo.qtd!!.text.toString().toInt() > 0) {

                            doceAdicionado.quantidadeDoce =
                                criarDialogo.qtd!!.text.toString().toInt()
                            recyclerViewAdapter!!.lista!!.add(doceAdicionado)
                            recyclerViewAdapter!!.notifyItemInserted(recyclerViewAdapter!!.itemCount)
                            valorTotal += doceAdicionado.valorDoce * doceAdicionado.quantidadeDoce
                            textViewValorTotal.text = "R$: " + "%.2f".format(valorTotal)
                            dialog.dismiss()

                        } else {
                            Toast.makeText(
                                context,
                                context.getString(R.string.ToastDadosInvalidos),
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        }
                    } catch (e: Exception) {
                        Toast.makeText(
                            context,
                            context.getString(R.string.ToastDadosInvalidos),
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                }
            } else {
                Toast.makeText(
                    context,
                    "Doce já adicionado",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

    fun validarEntradas(): Boolean {

        try {
            if (nomeCliente.text.isBlank()) {
                Toast.makeText(context, "Cliente Vazio", Toast.LENGTH_SHORT)
                    .show()
                return false
            }
            if (data.text.isBlank()) {
                Toast.makeText(context, "Selecione Data", Toast.LENGTH_SHORT)
                    .show()
                return false
            }
            if (hora.text.isBlank()) {
                Toast.makeText(context, "Selecione Hora", Toast.LENGTH_SHORT)
                    .show()
                return false
            }
            if (recyclerViewAdapter!!.lista!!.size == 0) {
                Toast.makeText(context, "Lista Vazia", Toast.LENGTH_SHORT)
                    .show()
                return false
            }


        } catch (e: NullPointerException) {
            Toast.makeText(
                context,
                context.getString(R.string.ToastDadosInvalidos),
                Toast.LENGTH_SHORT
            )
                .show()
        }

        return true
    }

}