package com.augusto.doceskotlin.fragments.listaDeDoces

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.augusto.doceskotlin.adapters.doces.EditarDocesRecyclerViewAdapter
import com.augusto.doceskotlin.singletons.ListaDeDoces

class EditarDocesFragment : ListaDocesFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        recyclerViewAdapter = EditarDocesRecyclerViewAdapter(bind!!.root.context)
        recyclerView?.adapter = recyclerViewAdapter
        container!!.removeAllViews()
        return bind!!.root

    }

    override fun onResume() {
        super.onResume()
        ListaDeDoces.pegarDocesFirebase(recyclerViewAdapter!!)

    }

}