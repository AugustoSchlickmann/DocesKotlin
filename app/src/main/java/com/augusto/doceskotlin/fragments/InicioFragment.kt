package com.augusto.doceskotlin.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.augusto.doceskotlin.Calendario
import com.augusto.doceskotlin.PROCURAR_ENCOMENDAS_DA_SEMANA
import com.augusto.doceskotlin.PROCURAR_ENCOMENDAS_POR_DATA
import com.augusto.doceskotlin.PROCURAR_ENCOMENDAS_POR_NOME_CLIENTE
import com.augusto.doceskotlin.PROCURAR_PROXIMAS_ENCOMENDAS
import com.augusto.doceskotlin.R
import com.augusto.doceskotlin.adapters.InicioRecyclerViewAdapter
import com.augusto.doceskotlin.databinding.FragmentTelaInicialBinding
import com.augusto.doceskotlin.singletons.OperacoesFirebase

private const val TIPO_DA_LISTA_DE_ENCOMENDAS = "TipoDaListaDeEncomendas"

class InicioFragment : Fragment() {

    private var param1: Int? = null

    private var bind: FragmentTelaInicialBinding? = null
    var progressBar: ProgressBar? = null
    private var textViewSemEncomendas: TextView? = null
    private var textViewSomaValorTotal: TextView? = null
    private var recyclerView: RecyclerView? = null
    private var recyclerViewAdapter: InicioRecyclerViewAdapter? = null
    private var container: ViewGroup? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getInt(TIPO_DA_LISTA_DE_ENCOMENDAS)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        setHasOptionsMenu(true)
        //this.container = container
        bind = FragmentTelaInicialBinding.inflate(layoutInflater, container, false)
        progressBar = bind!!.FragmentTelaInicialProgressBar
        textViewSemEncomendas = bind!!.FragmentTelaInicialTextViewSemEncomendas
        textViewSomaValorTotal = bind!!.FragmentTelaInicialTextViewSomaValorTotal
        recyclerView = bind!!.FragmentTelaInicialRecyclerView
        recyclerViewAdapter = InicioRecyclerViewAdapter(requireContext())
        recyclerView!!.adapter = recyclerViewAdapter
        container!!.removeAllViews()
        return bind!!.root
    }

    override fun onResume() {
        super.onResume()
        when (param1) {
            PROCURAR_PROXIMAS_ENCOMENDAS -> OperacoesFirebase.pegarProximasEncomendas(recyclerViewAdapter!!, this)
            PROCURAR_ENCOMENDAS_DA_SEMANA -> Calendario().pegarSemana(recyclerViewAdapter!!, this)
            PROCURAR_ENCOMENDAS_POR_DATA -> Calendario().pegarDia(recyclerViewAdapter!!, this)
            //PROCURAR_PROXIMAS_ENCOMENDAS_COM_DOCE_SELECIONADO ->
            //PROCURAR_ENCOMENDAS_POR_NOME_CLIENTE ->
            //PROCURAR_ENCOMENDAS_POR_ID_CLIENTE ->
            else -> OperacoesFirebase.pegarProximasEncomendas(recyclerViewAdapter!!, this)
        }
    }

    fun somarValoresEncomendas() {
        var valorTotal = 0.0
        var quantidadeTotalDoces = 0
        if (recyclerViewAdapter!!.lista?.size == 0) {
            textViewSemEncomendas!!.visibility = View.VISIBLE
            textViewSomaValorTotal!!.visibility = View.GONE
        } else {
            textViewSemEncomendas!!.visibility = View.GONE
            for (encomenda in recyclerViewAdapter!!.lista!!) {
                valorTotal += encomenda.getValorEncomenda()
                quantidadeTotalDoces += encomenda.getQuantidadeDocesEncomenda()
            }
            textViewSomaValorTotal!!.text = "R$: " + "%.2f".format(valorTotal)
            textViewSomaValorTotal!!.visibility = View.VISIBLE
        }
        progressBar?.visibility = View.GONE
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.lista_encomenda_menu, menu)

        when (param1) {
            PROCURAR_ENCOMENDAS_DA_SEMANA -> {
                val calendarioIcon = menu.findItem(R.id.lista_encomenda_menu_AbrirCalendario)
                calendarioIcon.isVisible = true
            }

            PROCURAR_ENCOMENDAS_POR_DATA -> {
                val calendarioIcon = menu.findItem(R.id.lista_encomenda_menu_AbrirCalendario)
                calendarioIcon.isVisible = true
            }

            PROCURAR_ENCOMENDAS_POR_NOME_CLIENTE -> {
                val lupaIcon = menu.findItem(R.id.lista_encomenda_menu_Pesquisar)
                lupaIcon.isVisible = true
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.lista_encomenda_menu_AbrirCalendario ->
                if(param1 == PROCURAR_ENCOMENDAS_DA_SEMANA){
                    Calendario().pegarSemana(recyclerViewAdapter!!, this)
                }else{
                    Calendario().pegarDia(recyclerViewAdapter!!, this)
                }

        }
        return super.onOptionsItemSelected(item)
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