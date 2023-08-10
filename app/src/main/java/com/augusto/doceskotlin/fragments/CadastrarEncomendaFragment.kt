package com.augusto.doceskotlin.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.augusto.doceskotlin.EncomendaMapper
import com.augusto.doceskotlin.databinding.FragmentCadastrarEncomendaBinding
import com.augusto.doceskotlin.objetos.Cliente
import com.augusto.doceskotlin.objetos.Encomenda

private const val CADASTRANDO_BANDEJINHA = "CadastrandoBandejinha"
private const val CADASTRANDO_COM_CLIENTE = "CadastrandoComCliente"

class CadastrarEncomendaFragment : Fragment() {

    private var param1: String? = null
    private var param2: Cliente? = null

    private var encomendaMapper: EncomendaMapper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(CADASTRANDO_BANDEJINHA)
            param2 = it.getParcelable(CADASTRANDO_COM_CLIENTE)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val bind = FragmentCadastrarEncomendaBinding.inflate(layoutInflater, container, false)
        encomendaMapper = EncomendaMapper(bind, true, Encomenda())
        encomendaMapper!!.recyclerViewAdapter.lista = ArrayList()

        param1?.let {
            encomendaMapper!!.editTextNomeCliente.setText(it)
            encomendaMapper!!.editTextTelefoneCliente.visibility = View.GONE
        }

        param2?.let {
            encomendaMapper!!.editTextNomeCliente.setText(it.nome)
            encomendaMapper!!.editTextTelefoneCliente.setText(it.telefone)
        }


        encomendaMapper!!.buttonSalvar.setOnClickListener {
            encomendaMapper!!.salvarEncomenda(null)
        }

        container!!.removeAllViews()
        return bind.root
    }

    companion object {

        @JvmStatic
        fun cadastrandoBandejinha(param: String) =
            CadastrarEncomendaFragment().apply {
                arguments = Bundle().apply {
                    putString(CADASTRANDO_BANDEJINHA, param)
                }
            }

        @JvmStatic
        fun cadastrandoComCliente(param: Cliente) =
            CadastrarEncomendaFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(CADASTRANDO_COM_CLIENTE, param)
                }
            }
    }

}


