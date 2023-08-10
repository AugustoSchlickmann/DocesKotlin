package com.augusto.doceskotlin.fragments.listaDeDoces

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.augusto.doceskotlin.adapters.doces.DocesAFazerRecyclerViewAdapter
import com.augusto.doceskotlin.singletons.OperacoesFirebase

class DocesAFazerFragment : ListaDocesFragment() {

    private var textViewNenhumDoceAFazer: TextView? = null
    private var textViewSomaValorTotal: TextView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        recyclerViewAdapter = DocesAFazerRecyclerViewAdapter(bind!!.root.context)
        recyclerView!!.adapter = recyclerViewAdapter
        textViewNenhumDoceAFazer = bind!!.FragmentListaDocesTextViewNenhumDoceAFazer
        textViewSomaValorTotal = bind!!.FragmentListaDocesTextViewListaDocesValorTotal
        container!!.removeAllViews()
        return bind!!.root

    }

    override fun onResume() {
        super.onResume()
        OperacoesFirebase.pegarDocesAFazer(recyclerViewAdapter!!, this)

    }

    @SuppressLint("SetTextI18n")
    fun mostrarDocesAFazer(quantidadeDoces: Int, valorTotal: Double) {
        if (recyclerViewAdapter!!.lista!!.size < 1) {
            textViewNenhumDoceAFazer!!.visibility = View.VISIBLE
            textViewSomaValorTotal!!.visibility = View.GONE

        } else {
            textViewSomaValorTotal!!.text = "R$: " + "%.2f".format(valorTotal)
            textViewSomaValorTotal!!.visibility = View.VISIBLE
            (requireActivity() as AppCompatActivity).supportActionBar?.title = "Doces a fazer: $quantidadeDoces"
        }
    }

}
