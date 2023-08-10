package com.augusto.doceskotlin.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.augusto.doceskotlin.adapters.ClientesRecyclerViewAdapter
import com.augusto.doceskotlin.databinding.FragmentListaClientesBinding
import com.augusto.doceskotlin.singletons.OperacoesFirebase

class ListaClientesFragment : Fragment() {

    private var textViewSemClientes: TextView? = null
    private var recyclerView: RecyclerView? = null
    private var recyclerViewAdapter: ClientesRecyclerViewAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View{
        val bind =  FragmentListaClientesBinding.inflate(layoutInflater,container,false)
        textViewSemClientes = bind.FragmentListaClientesTextViewSemClientes
        recyclerView = bind.FragmentListaClientesRecycleViewClientes
        container!!.removeAllViews()
        return bind.root
    }

    override fun onResume() {
        super.onResume()
        recyclerViewAdapter = ClientesRecyclerViewAdapter(ArrayList())
        recyclerView!!.adapter = recyclerViewAdapter
        OperacoesFirebase.pegarClientes(recyclerViewAdapter)

//        recyclerView!!.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
//                super.onScrollStateChanged(recyclerView, newState)
//                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
//                    Toast.makeText(requireContext(), recyclerViewAdapter!!.clientes[recyclerViewAdapter!!.itemCount-1].nome!!, Toast.LENGTH_SHORT).show()
//                    OperacoesFirebase.pegarProximosClientes(recyclerViewAdapter, recyclerViewAdapter!!.clientes[recyclerViewAdapter!!.itemCount-1].nome!!)
//                }
//            }
//        })

    }

}