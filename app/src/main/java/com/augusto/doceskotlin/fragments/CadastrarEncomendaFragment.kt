package com.augusto.doceskotlin.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.augusto.doceskotlin.Calendario
import com.augusto.doceskotlin.DialogoDoce
import com.augusto.doceskotlin.EncomendasTeste
import com.augusto.doceskotlin.ListaDeDoces
import com.augusto.doceskotlin.R
import com.augusto.doceskotlin.Relogio
import com.augusto.doceskotlin.activities.MainActivity
import com.augusto.doceskotlin.adapters.RecyclerViewCadastrarEncomendaAdapter
import com.augusto.doceskotlin.adapters.SpinnerDocesAdapter
import com.augusto.doceskotlin.databinding.FragmentCadastrarEncomendaBinding
import com.augusto.doceskotlin.models.Cliente
import com.augusto.doceskotlin.models.Doce
import com.augusto.doceskotlin.models.Encomenda
import java.lang.NullPointerException
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
    private var nomeCliente: EditText? = null
    var telefoneCliente: EditText? = null
    var data: EditText? = null
    var hora: EditText? = null
    var spinner: Spinner? = null
    var recyclerView: RecyclerView? = null
    private var botaoSalvar: Button? = null
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
    ): View {
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

        container!!.removeAllViews()
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

        botaoSalvar!!.setOnClickListener {
            botaoSalvar!!.isEnabled = false
            if (validarEntradas()) {
                try {
                    var novaEncomenda = Encomenda(
                        Cliente(nomeCliente!!.text.toString()),
                        calendario.timeInMillis,
                        recyclerViewAdapter!!.lista,
                        null,
                        false
                    )
                    EncomendasTeste.listaEncomendasTeste.add(novaEncomenda)
                    var intent = Intent(activity, MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    requireActivity().startActivity(intent)
                } catch (e: Exception) {
                    Toast.makeText(context, "Erro Criando Encomenda", Toast.LENGTH_SHORT).show()
                }

            } else {
                botaoSalvar!!.isEnabled = true
            }
        }


    }

    private fun validarEntradas(): Boolean {

        try {
            if (nomeCliente!!.text.isBlank()) {
                Toast.makeText(context, "Cliente Vazio", Toast.LENGTH_SHORT)
                    .show()
                return false
            }
            if (data!!.text.isBlank()) {
                Toast.makeText(context, "Selecione Data", Toast.LENGTH_SHORT)
                    .show()
                return false
            }
            if (hora!!.text.isBlank()) {
                Toast.makeText(context, "Selecione Hora", Toast.LENGTH_SHORT)
                    .show()
                return false
            }
            if (recyclerViewAdapter!!.lista.size == 0) {
                Toast.makeText(context, "Lista Vazia", Toast.LENGTH_SHORT)
                    .show()
                return false
            }


        } catch (e: NullPointerException) {
            Toast.makeText(context, getString(R.string.ToastDadosInvalidos), Toast.LENGTH_SHORT)
                .show()
        }

        return true
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        if (parent != null && position < parent.count) {
            val doceSelecionado = parent.getItemAtPosition(position) as Doce
            if (!recyclerViewAdapter!!.lista.contains(doceSelecionado)) {
                val doceAdicionado = doceSelecionado.copy()
                val criarDialogo = DialogoDoce()
                val dialog = criarDialogo.adicionarDoce(doceAdicionado, requireContext())

                criarDialogo.botao!!.setOnClickListener {
                    try {
                        if (criarDialogo.qtd!!.text.toString().toInt() > 0) {

                            doceAdicionado.quantidadeDoce =
                                criarDialogo.qtd!!.text.toString().toInt()
                            recyclerViewAdapter!!.lista.add(doceAdicionado)
                            recyclerViewAdapter!!.notifyItemInserted(recyclerViewAdapter!!.itemCount)
                            valorTotal += doceAdicionado.valorDoce!! * doceAdicionado.quantidadeDoce
                            textViewValorTotal!!.text = "R$: " + "%.2f".format(valorTotal)
                            dialog.dismiss()

                        } else {
                            Toast.makeText(
                                context,
                                getString(R.string.ToastDadosInvalidos),
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        }
                    } catch (e: Exception) {
                        Toast.makeText(
                            context,
                            getString(R.string.ToastDadosInvalidos),
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                }
            } else {
                Toast.makeText(
                    context,
                    "Doce já adicionado",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }
    }


    override fun onNothingSelected(parent: AdapterView<*>?) {

    }


}


