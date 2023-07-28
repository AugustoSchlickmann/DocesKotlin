package com.augusto.doceskotlin.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.augusto.doceskotlin.EDITAR_DOCES
import com.augusto.doceskotlin.adapters.RecyclerViewDocesAdapter
import com.augusto.doceskotlin.databinding.FragmentListaDocesBinding
import com.augusto.doceskotlin.singletons.ListaDeDoces

private const val TIPO_DA_LISTA = "TipoDaLista"

class ListaDocesFragment : Fragment() {

    private var param1: Int? = null

    private var bind : FragmentListaDocesBinding? = null
    private var textViewNenhumDoceAFazer : TextView? = null
    private var textViewSomaValorTotal : TextView? = null
    private var recyclerView: RecyclerView? = null
    private var recyclerViewAdapter : RecyclerViewDocesAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getInt(TIPO_DA_LISTA)

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bind = FragmentListaDocesBinding.inflate(layoutInflater, container, false)

        if (param1 == EDITAR_DOCES){
            recyclerViewAdapter = RecyclerViewDocesAdapter(bind!!.root.context, ListaDeDoces.pegarLista()!!, EDITAR_DOCES)
        }else{
            pegarDocesDaSemana()
        }

        textViewNenhumDoceAFazer = bind!!.FragmentListaDocesTextViewNenhumDoceAFazer
        textViewSomaValorTotal = bind!!.FragmentListaDocesTextViewListaDocesValorTotal
        recyclerView = bind!!.FragmentListaDocesRecycleViewDoces
        recyclerView!!.adapter = recyclerViewAdapter
        container!!.removeAllViews()
        return bind!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (param1 == EDITAR_DOCES){

        }else{
            textViewNenhumDoceAFazer!!.visibility = View.VISIBLE
        }
    }

    private fun pegarDocesDaSemana() {

    }

    companion object {
        @JvmStatic
        fun newInstance(param1: Int) =
            ListaDocesFragment().apply {
                arguments = Bundle().apply {
                    putInt(TIPO_DA_LISTA, param1)
                }
            }
    }
}