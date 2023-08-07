package com.augusto.doceskotlin

import android.annotation.SuppressLint
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.augusto.doceskotlin.adapters.DocesRecyclerViewAdapter
import com.augusto.doceskotlin.databinding.FragmentCadastrarEncomendaBinding
import com.augusto.doceskotlin.dialogos.DialogoEncomendaObs
import com.augusto.doceskotlin.objetos.Doce
import com.augusto.doceskotlin.objetos.Encomenda
import com.augusto.doceskotlin.singletons.OperacoesFirebase
import com.augusto.doceskotlin.singletons.ValidarEntradas
import java.util.Calendar

class EncomendaMapper(var bind: FragmentCadastrarEncomendaBinding, criarSpinner: Boolean, encomenda: Encomenda) {

    var editTextNomeCliente: EditText = bind.FragmentCadastrarEncomendaEditTextNomeCliente
    var editTextTelefoneCliente: EditText = bind.FragmentCadastrarEncomendaEditTextTelefoneCliente
    var editTextData: EditText = bind.FragmentCadastrarEncomendaEditTextData
    private var editTextHora: EditText = bind.FragmentCadastrarEncomendaEditTextHora
    var buttonSalvar: Button = bind.FragmentCadastrarEncomendaBotaoSalvar
    private var textViewValorTotal: TextView = bind.FragmentCadastrarEncomendaTextViewValorTotal
    var spinner: Spinner = bind.FragmentCadastrarEncomendaSpinnerDoces
    private var recyclerView: RecyclerView = bind.FragmentCadastrarEncomendaRecyclerView
    var imageViewObs : ImageView = bind.FragmentCadastrarEncomendaImageViewObs

    var recyclerViewAdapter: DocesRecyclerViewAdapter = DocesRecyclerViewAdapter(this)

    private val calendario: Calendar = Calendar.getInstance()
    var encomenda : Encomenda

    init {
        this.encomenda = encomenda
        if (criarSpinner) {
            SpinnerDoces(this)
        }

        recyclerView.adapter = recyclerViewAdapter

        editTextData.setOnClickListener {
            editTextNomeCliente.clearFocus()
            editTextTelefoneCliente.clearFocus()
            Calendario().abrirCalendario(bind.root.context, calendario, editTextData)

        }

        editTextHora.setOnClickListener {
            editTextNomeCliente.clearFocus()
            editTextTelefoneCliente.clearFocus()
            Relogio().abrirRelogio(bind.root.context, calendario, editTextHora)
        }

        imageViewObs.setOnClickListener{
            editTextNomeCliente.clearFocus()
            editTextTelefoneCliente.clearFocus()
            DialogoEncomendaObs(bind.root.context, encomenda, imageViewObs)

        }

    }

    fun adicionouDoce(doceAdicionado: Doce) {
        recyclerViewAdapter.lista!!.add(doceAdicionado)
        recyclerViewAdapter.notifyItemInserted(recyclerViewAdapter.itemCount)
        encomenda.valorEncomenda += doceAdicionado.valorDoce!! * doceAdicionado.quantidadeDoce
        encomenda.quantidadeDocesEncomenda += doceAdicionado.quantidadeDoce
        textViewValorTotal.text = "R$: " + "%.2f".format(encomenda.valorEncomenda)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun removeuDoce(doceRemovido: Doce) {
        recyclerViewAdapter.lista!!.remove(doceRemovido)
        recyclerViewAdapter.notifyDataSetChanged()
        encomenda.valorEncomenda -= doceRemovido.valorDoce!! * doceRemovido.quantidadeDoce
        encomenda.quantidadeDocesEncomenda -= doceRemovido.quantidadeDoce
        textViewValorTotal.text = "R$: " + "%.2f".format(encomenda.valorEncomenda)
    }

    fun alterouDoce(doceAlterado: Doce, quantidadeAnterior: Int) {
        recyclerViewAdapter.notifyItemChanged(recyclerViewAdapter.lista!!.indexOf(doceAlterado))
        encomenda.valorEncomenda -= doceAlterado.valorDoce!! * quantidadeAnterior
        encomenda.valorEncomenda += doceAlterado.valorDoce!! * doceAlterado.quantidadeDoce
        encomenda.quantidadeDocesEncomenda -= quantidadeAnterior
        encomenda.quantidadeDocesEncomenda += doceAlterado.quantidadeDoce
        textViewValorTotal.text = "R$: " + "%.2f".format(encomenda.valorEncomenda)
    }

    fun colocarDadosNaTela() {
        editTextNomeCliente.setText(encomenda.cliente!!.nome)
        editTextTelefoneCliente.setText(encomenda.cliente!!.telefone)
        editTextData.setText(FORMATADOR_DATA.format(encomenda.data!!))
        editTextHora.setText(FORMATADOR_HORA.format(encomenda.data!!))
        recyclerViewAdapter.lista = encomenda.doces
        textViewValorTotal.text = "R$: " + "%.2f".format(encomenda.valorEncomenda)

    }

    fun salvarEncomenda(trocaTela : String?) {
        buttonSalvar.isEnabled = false
        if (ValidarEntradas.daEncomenda(editTextNomeCliente, editTextData, editTextHora, recyclerViewAdapter.lista!!, bind.root.context)) {
            editTextNomeCliente.clearFocus()
            editTextTelefoneCliente.clearFocus()
            try {

                encomenda.cliente?.nome = editTextNomeCliente.text.toString().trim().replaceFirstChar { it.uppercase() }
                encomenda.cliente?.telefone = editTextTelefoneCliente.text.toString()
                encomenda.data = calendario.time
                encomenda.doces = recyclerViewAdapter.lista!!

                if(trocaTela == null){
                    OperacoesFirebase.salvarNovaEncomenda(encomenda, bind.root.context, buttonSalvar)
                }else{
                    OperacoesFirebase.atualizarEncomenda(encomenda, bind.root.context, buttonSalvar)
                }

            } catch (e: Exception) {
                Toast.makeText(bind.root.context,"Erro montando encomenda", Toast.LENGTH_SHORT).show()
                buttonSalvar.isEnabled = true
            }

        } else {
            Toast.makeText(bind.root.context, R.string.ToastDadosInvalidos, Toast.LENGTH_SHORT).show()
            buttonSalvar.isEnabled = true
        }
    }

}