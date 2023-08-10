package com.augusto.doceskotlin.fragments.listaDeDoces

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.augusto.doceskotlin.adapters.doces.DocesRecyclerViewAdapter
import com.augusto.doceskotlin.databinding.FragmentListaDocesBinding

abstract class ListaDocesFragment : Fragment() {

    internal var bind: FragmentListaDocesBinding? = null
    internal var recyclerView: RecyclerView? = null
    internal var recyclerViewAdapter: DocesRecyclerViewAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        bind = FragmentListaDocesBinding.inflate(layoutInflater, container, false)
        recyclerView = bind!!.FragmentListaDocesRecycleViewDoces
        return bind!!.root

    }

}