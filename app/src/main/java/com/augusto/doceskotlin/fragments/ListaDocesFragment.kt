package com.augusto.doceskotlin.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.augusto.doceskotlin.EDITAR_DOCES
import com.augusto.doceskotlin.VER_DOCES_A_FAZER
import com.augusto.doceskotlin.adapters.RecyclerViewDocesAdapter
import com.augusto.doceskotlin.databinding.FragmentListaDocesBinding
import com.augusto.doceskotlin.objetos.Doce
import com.augusto.doceskotlin.singletons.ListaDeDoces
import com.augusto.doceskotlin.singletons.OperacoesFirebase

private const val TIPO_DA_LISTA_DE_DOCES = "TipoDaListaDeDoces"

class ListaDocesFragment : Fragment() {

    private var param1: Int? = null

    private var bind: FragmentListaDocesBinding? = null
    private var textViewNenhumDoceAFazer: TextView? = null
    private var textViewSomaValorTotal: TextView? = null
    private var recyclerView: RecyclerView? = null
    private var recyclerViewAdapter: RecyclerViewDocesAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getInt(TIPO_DA_LISTA_DE_DOCES)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View{
        bind = FragmentListaDocesBinding.inflate(layoutInflater, container, false)
        textViewNenhumDoceAFazer = bind!!.FragmentListaDocesTextViewNenhumDoceAFazer
        textViewSomaValorTotal = bind!!.FragmentListaDocesTextViewListaDocesValorTotal
        recyclerView = bind!!.FragmentListaDocesRecycleViewDoces
        recyclerView!!.adapter = recyclerViewAdapter
        container!!.removeAllViews()
        return bind!!.root
    }

    override fun onResume() {
        super.onResume()
        when (param1) {
            EDITAR_DOCES -> RecyclerViewDocesAdapter(bind!!.root.context, ListaDeDoces.pegarLista()!!, EDITAR_DOCES)
            VER_DOCES_A_FAZER -> OperacoesFirebase.pegarDocesAFazer(this)
        }
    }

    fun mostrarDocesAFazer(lista: MutableList<Doce>, quantidadeDoces: Int, valorTotal: Double) {
        if (lista.size < 1) {
            textViewNenhumDoceAFazer!!.visibility = View.VISIBLE
        } else {
            RecyclerViewDocesAdapter(bind!!.root.context, lista, VER_DOCES_A_FAZER)
            textViewSomaValorTotal!!.text = valorTotal.toString()
            (requireActivity() as AppCompatActivity).supportActionBar?.title = "$quantidadeDoces Doces"
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: Int) =
            ListaDocesFragment().apply {
                arguments = Bundle().apply {
                    putInt(TIPO_DA_LISTA_DE_DOCES, param1)
                }
            }
    }
}