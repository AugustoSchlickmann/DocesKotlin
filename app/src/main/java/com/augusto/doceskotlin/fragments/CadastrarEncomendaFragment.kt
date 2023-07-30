package com.augusto.doceskotlin.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.augusto.doceskotlin.EncomendaMapper
import com.augusto.doceskotlin.R
import com.augusto.doceskotlin.databinding.FragmentCadastrarEncomendaBinding
import com.augusto.doceskotlin.objetos.Cliente
import com.augusto.doceskotlin.objetos.Encomenda
import com.augusto.doceskotlin.singletons.OperacoesFirebase

private const val CADASTRANDO_ENCOMENDA_COM_ARGS = "param1"

class CadastrarEncomendaFragment : Fragment() {
    private var param1: String? = null

    private var bind: FragmentCadastrarEncomendaBinding? = null

    private var encomendaMapper : EncomendaMapper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(CADASTRANDO_ENCOMENDA_COM_ARGS)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        bind = FragmentCadastrarEncomendaBinding.inflate(layoutInflater, container, false)
        encomendaMapper = EncomendaMapper(bind!!)
        encomendaMapper!!.recyclerViewAdapter.lista = ArrayList()
        container!!.removeAllViews()
        return bind!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        encomendaMapper!!.botaoSalvar.setOnClickListener {
            encomendaMapper!!.botaoSalvar.isEnabled = false
            if (encomendaMapper!!.validarEntradas()) {
                try {
                    val novoCliente = Cliente(
                        encomendaMapper!!.nomeCliente.text.toString(),
                        encomendaMapper!!.telefoneCliente.text.toString())

                    val novaEncomenda = Encomenda(
                        novoCliente,
                        encomendaMapper!!.calendario.time,
                        encomendaMapper!!.recyclerViewAdapter.lista!!,
                        null,
                        false)

                    OperacoesFirebase.salvarNovaEncomenda(novaEncomenda, requireActivity(), encomendaMapper!!.botaoSalvar)


                } catch (e: Exception) {
                    Toast.makeText(context, "Erro Criando Encomenda", Toast.LENGTH_SHORT).show()
                    encomendaMapper!!.botaoSalvar.isEnabled = true
                }

            } else {
                Toast.makeText(context, R.string.ToastDadosInvalidos, Toast.LENGTH_SHORT).show()
                encomendaMapper!!.botaoSalvar.isEnabled = true
            }
        }

    }

}


