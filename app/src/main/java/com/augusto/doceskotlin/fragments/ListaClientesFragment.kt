package com.augusto.doceskotlin.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.augusto.doceskotlin.adapters.ClientesRecyclerViewAdapter
import com.augusto.doceskotlin.databinding.FragmentListaClientesBinding
import com.augusto.doceskotlin.singletons.OperacoesFirebase

class ListaClientesFragment : Fragment() {

    private var bind: FragmentListaClientesBinding? = null
    private var textViewSemClientes: TextView? = null
    private var recyclerView: RecyclerView? = null
    private var recyclerViewAdapter: ClientesRecyclerViewAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bind =  FragmentListaClientesBinding.inflate(layoutInflater,container,false)
        textViewSemClientes = bind!!.FragmentListaClientesTextViewSemClientes
        recyclerView = bind!!.FragmentListaClientesRecycleViewClientes
        container!!.removeAllViews()
        return bind!!.root
    }

    override fun onResume() {
        super.onResume()
        recyclerViewAdapter = ClientesRecyclerViewAdapter(ArrayList())
        recyclerView!!.adapter = recyclerViewAdapter
        OperacoesFirebase.pegarClientes(recyclerViewAdapter)
    }

}