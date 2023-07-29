package com.augusto.doceskotlin

import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.augusto.doceskotlin.adapters.RecyclerViewDocesAdapter
import com.augusto.doceskotlin.databinding.FragmentCadastrarEncomendaBinding
import com.augusto.doceskotlin.objetos.Doce
import java.lang.NullPointerException
import java.util.Calendar

open class EncomendaMapper(var bind: FragmentCadastrarEncomendaBinding){

    var nomeCliente: EditText = bind.FragmentCadastrarEncomendaEditTextNomeCliente
    var telefoneCliente: EditText = bind.FragmentCadastrarEncomendaEditTextTelefoneCliente
    var data: EditText = bind.FragmentCadastrarEncomendaEditTextData
    var hora: EditText = bind.FragmentCadastrarEncomendaEditTextHora
    var botaoSalvar: Button = bind.FragmentCadastrarEncomendaBotaoSalvar

    var textViewValorTotal: TextView = bind.FragmentCadastrarEncomendaTextViewValorTotal
    var valorTotal: Double = 0.0

    var spinner: Spinner = bind.FragmentCadastrarEncomendaSpinnerDoces

    var recyclerView: RecyclerView = bind.FragmentCadastrarEncomendaRecyclerView
    var recyclerViewAdapter: RecyclerViewDocesAdapter = RecyclerViewDocesAdapter(this)

    val calendario = Calendar.getInstance()

    init {
        SpinnerDoces(this)
        recyclerView.adapter = recyclerViewAdapter

        data.setOnClickListener {
            nomeCliente.clearFocus()
            telefoneCliente.clearFocus()
            Calendario().abrirCalendario(bind.root.context, calendario, data)

        }

        hora.setOnClickListener {
            nomeCliente.clearFocus()
            telefoneCliente.clearFocus()
            Relogio().abrirRelogio(bind.root.context, calendario, hora)
        }
    }


    fun adicionouDoce(doceAdicionado : Doce){
        recyclerViewAdapter.lista!!.add(doceAdicionado)
        recyclerViewAdapter.notifyItemInserted(recyclerViewAdapter.itemCount)
        valorTotal+= doceAdicionado.valorDoce!! * doceAdicionado.quantidadeDoce
        textViewValorTotal.text = "R$: " + "%.2f".format(valorTotal)
    }

    fun removeuDoce(doceRemovido : Doce){
        //val posicaoDoceRemovido = recyclerViewAdapter.lista!!.indexOf(doceRemovido)
        recyclerViewAdapter.lista!!.remove(doceRemovido)
        //recyclerViewAdapter.notifyItemRemoved(posicaoDoceRemovido)
        recyclerViewAdapter.notifyDataSetChanged()
        valorTotal-= doceRemovido.valorDoce!! * doceRemovido.quantidadeDoce
        textViewValorTotal.text = "R$: " + "%.2f".format(valorTotal)
    }

    fun alterouDoce(doceAlterado : Doce, quantidadeAnterior : Int){
        recyclerViewAdapter.notifyItemChanged(recyclerViewAdapter.lista!!.indexOf(doceAlterado))
        valorTotal-= quantidadeAnterior * doceAlterado.valorDoce!!
        valorTotal+= doceAlterado.valorDoce!! * doceAlterado.quantidadeDoce
        textViewValorTotal.text = "R$: " + "%.2f".format(valorTotal)
    }

    fun validarEntradas(): Boolean {

        try {
            if (nomeCliente.text.isBlank()) {
                Toast.makeText(bind.root.context, "Cliente Vazio", Toast.LENGTH_SHORT)
                    .show()
                return false
            }
            if (data.text.isBlank()) {
                Toast.makeText(bind.root.context, "Selecione Data", Toast.LENGTH_SHORT)
                    .show()
                return false
            }
            if (hora.text.isBlank()) {
                Toast.makeText(bind.root.context, "Selecione Hora", Toast.LENGTH_SHORT)
                    .show()
                return false
            }
            if (recyclerViewAdapter.lista!!.size == 0) {
                Toast.makeText(bind.root.context, "Lista Vazia", Toast.LENGTH_SHORT)
                    .show()
                return false
            }


        } catch (e: Exception) {
            Toast.makeText(
                bind.root.context,
                bind.root.context.getString(R.string.ToastDadosInvalidos),
                Toast.LENGTH_SHORT
            )
                .show()
        }

        return true
    }

}