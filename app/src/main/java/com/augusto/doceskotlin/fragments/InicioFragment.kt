package com.augusto.doceskotlin.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.augusto.doceskotlin.Calendario
import com.augusto.doceskotlin.PROCURAR_ENCOMENDAS_DA_SEMANA
import com.augusto.doceskotlin.PROCURAR_ENCOMENDAS_POR_DATA
import com.augusto.doceskotlin.PROCURAR_ENCOMENDAS_POR_ID_CLIENTE
import com.augusto.doceskotlin.PROCURAR_ENCOMENDAS_POR_NOME_CLIENTE
import com.augusto.doceskotlin.PROCURAR_PROXIMAS_ENCOMENDAS
import com.augusto.doceskotlin.PROCURAR_PROXIMAS_ENCOMENDAS_COM_DOCE_SELECIONADO
import com.augusto.doceskotlin.adapters.RecyclerViewInicioAdapter
import com.augusto.doceskotlin.databinding.FragmentTelaInicialBinding
import com.augusto.doceskotlin.singletons.OperacoesFirebase

private const val TIPO_DA_LISTA_DE_ENCOMENDAS = "TipoDaListaDeEncomendas"

class InicioFragment : Fragment() {

    private var param1: Int? = null

    private var bind: FragmentTelaInicialBinding? = null
    private var progressBar: ProgressBar? = null
    private var textViewSemEncomendas: TextView? = null
    private var textViewSomaValorTotal: TextView? = null
    private var recyclerView: RecyclerView? = null
    private var recyclerViewAdapter: RecyclerViewInicioAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getInt(TIPO_DA_LISTA_DE_ENCOMENDAS)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        bind = FragmentTelaInicialBinding.inflate(layoutInflater, container, false)
        progressBar = bind!!.FragmentTelaInicialProgressBar
        textViewSemEncomendas = bind!!.FragmentTelaInicialTextViewSemEncomendas
        textViewSomaValorTotal = bind!!.FragmentTelaInicialTextViewSomaValorTotal
        recyclerView = bind!!.FragmentTelaInicialRecyclerView

        recyclerViewAdapter = RecyclerViewInicioAdapter(requireContext())
        recyclerView!!.adapter = recyclerViewAdapter

        container!!.removeAllViews()
        return bind!!.root
    }

    private fun pegarEncomendasPorIdCliente() {

    }

    private fun pegarEncomendasPorNomeCliente() {

    }

    private fun pegarEncomendasPorData() {

    }

    private fun pegarProximasEncomendasComDoceSelecionado() {

    }

    override fun onResume() {
        super.onResume()
        progressBar!!.visibility = View.VISIBLE
        recyclerViewAdapter!!.lista = ArrayList()
        when (param1) {
            PROCURAR_PROXIMAS_ENCOMENDAS -> OperacoesFirebase.pegarProximasEncomendas(recyclerViewAdapter!!,this)
            PROCURAR_ENCOMENDAS_DA_SEMANA -> Calendario().pegarSemana(recyclerViewAdapter!!,this)
            PROCURAR_PROXIMAS_ENCOMENDAS_COM_DOCE_SELECIONADO -> pegarProximasEncomendasComDoceSelecionado()
            PROCURAR_ENCOMENDAS_POR_DATA -> pegarEncomendasPorData()
            PROCURAR_ENCOMENDAS_POR_NOME_CLIENTE -> pegarEncomendasPorNomeCliente()
            PROCURAR_ENCOMENDAS_POR_ID_CLIENTE -> pegarEncomendasPorIdCliente()
            else -> OperacoesFirebase.pegarProximasEncomendas(recyclerViewAdapter!!, this)
        }
    }

    fun somarValoresEncomendas(){
        var valorTotal = 0.0
        var quantidadeTotalDoces = 0
        if (recyclerViewAdapter!!.lista?.size == 0) {
            textViewSemEncomendas!!.visibility = View.VISIBLE
            textViewSomaValorTotal!!.visibility = View.GONE
        }else{
            textViewSemEncomendas!!.visibility = View.GONE
            for (encomenda in recyclerViewAdapter!!.lista!!) {
                valorTotal += encomenda.getValorEncomenda()
                quantidadeTotalDoces += encomenda.getQuantidadeDocesEncomenda()
            }
            textViewSomaValorTotal!!.text = "R$: " + "%.2f".format(valorTotal)
            textViewSomaValorTotal!!.visibility = View.VISIBLE
        }
        progressBar!!.visibility = View.GONE
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: Int) =
            InicioFragment().apply {
                arguments = Bundle().apply {
                    putInt(TIPO_DA_LISTA_DE_ENCOMENDAS, param1)
                }
            }
    }
}