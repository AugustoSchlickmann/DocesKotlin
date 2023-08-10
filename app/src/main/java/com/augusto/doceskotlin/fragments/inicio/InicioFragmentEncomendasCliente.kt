package com.augusto.doceskotlin.fragments.inicio

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.augusto.doceskotlin.ARG_PARAM_CLIENTE_PARCELABLE
import com.augusto.doceskotlin.R
import com.augusto.doceskotlin.objetos.Cliente
import com.augusto.doceskotlin.singletons.OperacoesFirebase

class InicioFragmentEncomendasCliente : InicioFragment() {

    private var cliente: Cliente? = null
    private var searchView: SearchView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            cliente = it.getParcelable(ARG_PARAM_CLIENTE_PARCELABLE)
        }
    }

    override fun onResume() {
        super.onResume()
        if (cliente != null) {
            OperacoesFirebase.pegarEncomendasDoCliente(recyclerViewAdapter!!, this, cliente)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.lista_encomenda_menu_lupa, menu)

        val lupaIcon = menu.findItem(R.id.lista_encomenda_menu_Pesquisar)
        searchView = lupaIcon.actionView as SearchView?
        searchView?.queryHint = "Nome do cliente..."

        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String): Boolean {
                val nome = query.trimStart().replaceFirstChar { it.uppercase() }
                (activity as AppCompatActivity?)!!.supportActionBar!!.title = nome
                OperacoesFirebase.pegarEncomendasPorNomeCliente(
                    recyclerViewAdapter!!,
                    this@InicioFragmentEncomendasCliente,
                    nome
                )
                return false
            }

            override fun onQueryTextChange(texto: String): Boolean {
                return false
            }

        })

    }

    companion object {
        @JvmStatic
        fun encomendasDoCliente(param: Cliente) =
            InicioFragmentEncomendasCliente().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_PARAM_CLIENTE_PARCELABLE, param)
                }
            }
    }

}