package com.augusto.doceskotlin.fragments

import android.app.Dialog
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.augusto.doceskotlin.Calendario
import com.augusto.doceskotlin.DialogoDoce
import com.augusto.doceskotlin.ListaDeDoces
import com.augusto.doceskotlin.R
import com.augusto.doceskotlin.Relogio
import com.augusto.doceskotlin.adapters.RecyclerViewCadastrarEncomendaAdapter
import com.augusto.doceskotlin.adapters.SpinnerDocesAdapter
import com.augusto.doceskotlin.databinding.FragmentCadastrarEncomendaBinding
import com.augusto.doceskotlin.models.Doce
import java.util.Calendar

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CadastrarEncomendaFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CadastrarEncomendaFragment : Fragment(), AdapterView.OnItemSelectedListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    var bind: FragmentCadastrarEncomendaBinding? = null
    var nomeCliente: EditText? = null
    var telefoneCliente: EditText? = null
    var data: EditText? = null
    var hora: EditText? = null
    var spinner: Spinner? = null
    var recyclerView: RecyclerView? = null
    var botaoSalvar: Button? = null
    var textViewValorTotal: TextView? = null
    var recyclerViewAdapter: RecyclerViewCadastrarEncomendaAdapter? = null
    var valorTotal: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        container!!.removeAllViews()
        bind = FragmentCadastrarEncomendaBinding.inflate(layoutInflater, container, false)
        nomeCliente = bind!!.FragmentCadastrarEncomendaEditTextNomeCliente
        telefoneCliente = bind!!.FragmentCadastrarEncomendaEditTextTelefoneCliente
        data = bind!!.FragmentCadastrarEncomendaEditTextData
        hora = bind!!.FragmentCadastrarEncomendaEditTextHora
        spinner = bind!!.FragmentCadastrarEncomendaSpinnerDoces
        recyclerView = bind!!.FragmentCadastrarEncomendaRecyclerView
        botaoSalvar = bind!!.FragmentCadastrarEncomendaBotaoSalvar
        textViewValorTotal = bind!!.FragmentCadastrarEncomendaTextViewValorTotal

        spinner!!.adapter = SpinnerDocesAdapter(requireContext(), ListaDeDoces.pegarLista()!!)
        spinner!!.onItemSelectedListener = this
        spinner!!.setSelection(spinner!!.adapter.count)
        recyclerViewAdapter = RecyclerViewCadastrarEncomendaAdapter(requireContext(), ArrayList())
        recyclerView!!.adapter = recyclerViewAdapter

        return bind!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val calendario = Calendar.getInstance()

        data!!.setOnClickListener {
            nomeCliente!!.clearFocus()
            telefoneCliente!!.clearFocus()
            Calendario().abrirCalendario(requireContext(), calendario, data!!)

        }

        hora!!.setOnClickListener {
            nomeCliente!!.clearFocus()
            telefoneCliente!!.clearFocus()
            Relogio().abrirRelogio(requireContext(), calendario, hora!!)
        }


    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        if (parent != null && position < parent.count) {
            //tá passando por referencia, tá mudando na ListaDeDoces, vai dar problema??
            val doceAdicionado = parent.getItemAtPosition(position) as Doce
            val criarDialogo = DialogoDoce()
            val dialog = criarDialogo.adicionarDoce(doceAdicionado,requireContext())

            criarDialogo.botao!!.setOnClickListener() {
                try {
                    if (criarDialogo.qtd!!.text.toString().toInt() > 0) {
                        if (!recyclerViewAdapter!!.lista.contains(doceAdicionado)) {
                            doceAdicionado.quantidadeDoce = criarDialogo.qtd!!.text.toString().toInt()
                            recyclerViewAdapter!!.lista.add(doceAdicionado)
                            recyclerViewAdapter!!.notifyDataSetChanged()
                            valorTotal += doceAdicionado.valorDoce!! * doceAdicionado.quantidadeDoce
                            textViewValorTotal!!.text = "R$: " + "%.2f".format(valorTotal)
                            dialog.dismiss()
                        } else {
                            Toast.makeText(
                                context,
                                "Doce já adicionado",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                    } else {
                        Toast.makeText(context, "Quantidade Inválida", Toast.LENGTH_SHORT)
                            .show()
                    }
                } catch (e: Exception) {
                    Toast.makeText(context, "Quantidade Inválida", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            }
        }


    override fun onNothingSelected(parent: AdapterView<*>?) {

    }


}


