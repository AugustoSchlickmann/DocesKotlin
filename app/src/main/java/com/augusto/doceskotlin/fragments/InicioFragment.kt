package com.augusto.doceskotlin.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.augusto.doceskotlin.EncomendasTeste
import com.augusto.doceskotlin.R
import com.augusto.doceskotlin.adapters.RecyclerViewInicioAdapter
import com.augusto.doceskotlin.databinding.FragmentCadastrarEncomendaBinding
import com.augusto.doceskotlin.databinding.FragmentTelaInicialBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [InicioFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class InicioFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    var bind : FragmentTelaInicialBinding? = null
    var progressBar : ProgressBar? = null
    var textViewSemEncomendas : TextView? = null
    var textViewSomaValorTotal : TextView? = null
    var recyclerView: RecyclerView? = null
    var recyclerViewAdapter : RecyclerViewInicioAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        bind = FragmentTelaInicialBinding.inflate(layoutInflater, container, false)
        textViewSemEncomendas = bind!!.MainActivityTextViewSemEncomendas
        textViewSomaValorTotal = bind!!.MainActivityTextViewSomaValorTotal
        recyclerView = bind!!.MainActivityRecyclerView
        recyclerViewAdapter = RecyclerViewInicioAdapter(requireContext(),EncomendasTeste.listaEncomendasTeste)
        recyclerView!!.adapter = recyclerViewAdapter

        container!!.removeAllViews()
        return bind!!.root
    }

    override fun onResume() {
        super.onResume()
        if(recyclerViewAdapter!!.lista.size>0){
            textViewSemEncomendas!!.visibility = View.GONE
            textViewSomaValorTotal!!.text= "R$: " + "%.2f".format(recyclerViewAdapter!!.somaValorTotal)
            textViewSomaValorTotal!!.visibility = View.VISIBLE
        }else{
            textViewSemEncomendas!!.visibility = View.VISIBLE
            textViewSomaValorTotal!!.visibility = View.GONE
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment InicioFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            InicioFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}