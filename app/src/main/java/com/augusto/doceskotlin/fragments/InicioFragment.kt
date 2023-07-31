package com.augusto.doceskotlin.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.augusto.doceskotlin.ARG_PARAM_CLIENTE_PARCELABLE
import com.augusto.doceskotlin.Calendario
import com.augusto.doceskotlin.PROCURAR_ENCOMENDAS_DA_SEMANA
import com.augusto.doceskotlin.PROCURAR_ENCOMENDAS_DO_CLIENTE
import com.augusto.doceskotlin.PROCURAR_ENCOMENDAS_POR_DATA
import com.augusto.doceskotlin.PROCURAR_ENCOMENDAS_POR_NOME_CLIENTE_DIGITADO
import com.augusto.doceskotlin.PROCURAR_PROXIMAS_ENCOMENDAS
import com.augusto.doceskotlin.R
import com.augusto.doceskotlin.adapters.InicioRecyclerViewAdapter
import com.augusto.doceskotlin.databinding.FragmentTelaInicialBinding
import com.augusto.doceskotlin.objetos.Cliente
import com.augusto.doceskotlin.singletons.OperacoesFirebase

private const val TIPO_DA_LISTA_DE_ENCOMENDAS = "TipoDaListaDeEncomendas"

class InicioFragment : Fragment() {

    private var param1: Int? = null
    private var cliente: Cliente? = null

    private var bind: FragmentTelaInicialBinding? = null
    var progressBar: ProgressBar? = null
    private var textViewSemEncomendas: TextView? = null
    private var textViewSomaValorTotal: TextView? = null
    private var recyclerView: RecyclerView? = null
    private var recyclerViewAdapter: InicioRecyclerViewAdapter? = null
    private var container: ViewGroup? = null
    private var searchView: SearchView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getInt(TIPO_DA_LISTA_DE_ENCOMENDAS)
            cliente = it.getParcelable(ARG_PARAM_CLIENTE_PARCELABLE)
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
        container!!.removeAllViews()
        return bind!!.root
    }

    override fun onResume() {
        super.onResume()
        recyclerViewAdapter = InicioRecyclerViewAdapter(requireContext(), ArrayList())
        recyclerView!!.adapter = recyclerViewAdapter
        when (param1) {
            PROCURAR_PROXIMAS_ENCOMENDAS -> OperacoesFirebase.pegarProximasEncomendas(recyclerViewAdapter!!, this)
            PROCURAR_ENCOMENDAS_DA_SEMANA -> Calendario().pegarSemana(recyclerViewAdapter!!, this)
            PROCURAR_ENCOMENDAS_POR_DATA -> Calendario().pegarDia(recyclerViewAdapter!!, this)
            PROCURAR_ENCOMENDAS_DO_CLIENTE -> OperacoesFirebase.pegarEncomendasDoCliente(recyclerViewAdapter!!, this, cliente)
            //PROCURAR_PROXIMAS_ENCOMENDAS_COM_DOCE_SELECIONADO ->
        }
    }

    fun somarValoresEncomendas() {
        var valorTotal = 0.0
        var quantidadeTotalDoces = 0
        if (recyclerViewAdapter!!.lista.size == 0) {
            textViewSemEncomendas!!.visibility = View.VISIBLE
            textViewSomaValorTotal!!.visibility = View.GONE
        } else {
            textViewSemEncomendas!!.visibility = View.GONE
            for (encomenda in recyclerViewAdapter!!.lista) {
                valorTotal += encomenda.valorEncomenda
                quantidadeTotalDoces += encomenda.quantidadeDocesEncomenda
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

            PROCURAR_ENCOMENDAS_POR_NOME_CLIENTE_DIGITADO -> {
                val lupaIcon = menu.findItem(R.id.lista_encomenda_menu_Pesquisar)
                searchView = lupaIcon.actionView as SearchView?
                searchView!!.queryHint = "Nome do cliente..."
                lupaIcon.isVisible = true

                searchView!!.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String): Boolean {
                        val nome = query.trimStart().replaceFirstChar { it.uppercase() }
                        (activity as AppCompatActivity?)!!.supportActionBar!!.title = nome
                        OperacoesFirebase.pegarEncomendasPorNomeCliente(recyclerViewAdapter!!, this@InicioFragment, nome)
                        return false
                    }

                    override fun onQueryTextChange(texto: String): Boolean {
                        return false
                    }
                })

            }


        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.lista_encomenda_menu_AbrirCalendario ->
                if (param1 == PROCURAR_ENCOMENDAS_DA_SEMANA) {
                    Calendario().pegarSemana(recyclerViewAdapter!!, this)
                } else {
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

        @JvmStatic
        fun encomendasDoCliente(param1: Int, param2: Cliente) =
            InicioFragment().apply {
                arguments = Bundle().apply {
                    putInt(TIPO_DA_LISTA_DE_ENCOMENDAS, param1)
                    putParcelable(ARG_PARAM_CLIENTE_PARCELABLE, param2)
                }
            }
    }
}