package com.augusto.doceskotlin.fragments.inicio

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.augusto.doceskotlin.adapters.InicioRecyclerViewAdapter
import com.augusto.doceskotlin.databinding.FragmentTelaInicialBinding

abstract class InicioFragment : Fragment() {

    var progressBar: ProgressBar? = null
    private var textViewSemEncomendas: TextView? = null
    private var textViewSomaValorTotal: TextView? = null
    private var recyclerView: RecyclerView? = null
    internal var recyclerViewAdapter: InicioRecyclerViewAdapter? = null
    private var container: ViewGroup? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        setHasOptionsMenu(true)
        //this.container = container
        val bind = FragmentTelaInicialBinding.inflate(layoutInflater, container, false)
        progressBar = bind.FragmentTelaInicialProgressBar
        textViewSemEncomendas = bind.FragmentTelaInicialTextViewSemEncomendas
        textViewSomaValorTotal = bind.FragmentTelaInicialTextViewSomaValorTotal
        recyclerView = bind.FragmentTelaInicialRecyclerView
        container?.removeAllViews()
        return bind.root
    }

    override fun onResume() {
        super.onResume()
        recyclerViewAdapter = InicioRecyclerViewAdapter(requireContext(), ArrayList())
        recyclerView?.adapter = recyclerViewAdapter
    }

    @SuppressLint("SetTextI18n")
    open fun somarValoresEncomendas() {
        var valorTotal = 0.0
        var quantidadeTotalDoces = 0

        if (recyclerViewAdapter?.lista?.size == 0) {
            textViewSemEncomendas?.visibility = View.VISIBLE
            textViewSomaValorTotal?.visibility = View.GONE

        } else {
            textViewSemEncomendas?.visibility = View.GONE
            for (encomenda in recyclerViewAdapter?.lista!!) {
                valorTotal += encomenda.valorEncomenda
                quantidadeTotalDoces += encomenda.quantidadeDocesEncomenda
            }
            textViewSomaValorTotal?.text = "R$: " + "%.2f".format(valorTotal)
            textViewSomaValorTotal?.visibility = View.VISIBLE
        }
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "${quantidadeTotalDoces} Doces Encomendados"
        progressBar?.visibility = View.GONE
    }

}