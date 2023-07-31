package com.augusto.doceskotlin

import android.annotation.SuppressLint
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.augusto.doceskotlin.adapters.DocesRecyclerViewAdapter
import com.augusto.doceskotlin.databinding.FragmentCadastrarEncomendaBinding
import com.augusto.doceskotlin.objetos.Doce
import com.augusto.doceskotlin.objetos.Encomenda
import com.augusto.doceskotlin.singletons.OperacoesFirebase
import com.augusto.doceskotlin.singletons.ValidarEntradas
import java.util.Calendar

class EncomendaMapper(var bind: FragmentCadastrarEncomendaBinding, criarSpinner: Boolean) {

    var nomeCliente: EditText = bind.FragmentCadastrarEncomendaEditTextNomeCliente
    var telefoneCliente: EditText = bind.FragmentCadastrarEncomendaEditTextTelefoneCliente
    var data: EditText = bind.FragmentCadastrarEncomendaEditTextData
    private var hora: EditText = bind.FragmentCadastrarEncomendaEditTextHora
    var botaoSalvar: Button = bind.FragmentCadastrarEncomendaBotaoSalvar
    private var textViewValorTotal: TextView = bind.FragmentCadastrarEncomendaTextViewValorTotal
    var spinner: Spinner = bind.FragmentCadastrarEncomendaSpinnerDoces
    private var recyclerView: RecyclerView = bind.FragmentCadastrarEncomendaRecyclerView

    var recyclerViewAdapter: DocesRecyclerViewAdapter = DocesRecyclerViewAdapter(this)

    private val calendario: Calendar = Calendar.getInstance()
    var encomenda : Encomenda? = null

    init {
        if (criarSpinner) {
            SpinnerDoces(this)
        }

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

    fun adicionouDoce(doceAdicionado: Doce) {
        recyclerViewAdapter.lista!!.add(doceAdicionado)
        recyclerViewAdapter.notifyItemInserted(recyclerViewAdapter.itemCount)
        encomenda!!.valorEncomenda += doceAdicionado.valorDoce!! * doceAdicionado.quantidadeDoce
        encomenda!!.quantidadeDocesEncomenda += doceAdicionado.quantidadeDoce
        textViewValorTotal.text = "R$: " + "%.2f".format(encomenda!!.valorEncomenda)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun removeuDoce(doceRemovido: Doce) {
        recyclerViewAdapter.lista!!.remove(doceRemovido)
        recyclerViewAdapter.notifyDataSetChanged()
        encomenda!!.valorEncomenda -= doceRemovido.valorDoce!! * doceRemovido.quantidadeDoce
        encomenda!!.quantidadeDocesEncomenda -= doceRemovido.quantidadeDoce
        textViewValorTotal.text = "R$: " + "%.2f".format(encomenda!!.valorEncomenda)
    }

    fun alterouDoce(doceAlterado: Doce, quantidadeAnterior: Int) {
        recyclerViewAdapter.notifyItemChanged(recyclerViewAdapter.lista!!.indexOf(doceAlterado))
        encomenda!!.valorEncomenda -= doceAlterado.valorDoce!! * quantidadeAnterior
        encomenda!!.valorEncomenda += doceAlterado.valorDoce!! * doceAlterado.quantidadeDoce
        encomenda!!.quantidadeDocesEncomenda -= quantidadeAnterior
        encomenda!!.quantidadeDocesEncomenda += doceAlterado.quantidadeDoce
        textViewValorTotal.text = "R$: " + "%.2f".format(encomenda!!.valorEncomenda)
    }

    fun colocarDadosNaTela() {
        nomeCliente.setText(encomenda!!.cliente!!.nome)
        telefoneCliente.setText(encomenda!!.cliente!!.telefone)
        data.setText(FORMATADOR_DATA.format(encomenda!!.data!!))
        hora.setText(FORMATADOR_HORA.format(encomenda!!.data!!))
        recyclerViewAdapter.lista = encomenda!!.doces
        textViewValorTotal.text = "R$: " + "%.2f".format(encomenda!!.valorEncomenda)

    }

    fun salvarEncomenda(trocaTela : String?) {
        botaoSalvar.isEnabled = false
        if (ValidarEntradas.daEncomenda(nomeCliente, data, hora, recyclerViewAdapter.lista!!, bind.root.context)) {
            try {

                encomenda!!.cliente?.nome = nomeCliente.text.toString().trimStart().replaceFirstChar { it.uppercase() }
                encomenda!!.cliente?.telefone = telefoneCliente.text.toString()
                encomenda!!.data = calendario.time
                encomenda!!.doces = recyclerViewAdapter.lista!!

                if(trocaTela == null){
                    OperacoesFirebase.salvarNovaEncomenda(encomenda!!, bind.root.context, botaoSalvar)
                }else{
                    OperacoesFirebase.atualizarEncomenda(encomenda!!, bind.root.context, botaoSalvar)
                }

            } catch (e: Exception) {
                Toast.makeText(bind.root.context,"Erro montando encomenda", Toast.LENGTH_SHORT).show()
                botaoSalvar.isEnabled = true
            }

        } else {
            Toast.makeText(bind.root.context, R.string.ToastDadosInvalidos, Toast.LENGTH_SHORT).show()
            botaoSalvar.isEnabled = true
        }
    }

}