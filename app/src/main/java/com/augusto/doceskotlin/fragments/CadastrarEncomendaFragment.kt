package com.augusto.doceskotlin.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.augusto.doceskotlin.EncomendaMapper
import com.augusto.doceskotlin.singletons.EncomendasTeste
import com.augusto.doceskotlin.activities.MainActivity
import com.augusto.doceskotlin.databinding.FragmentCadastrarEncomendaBinding
import com.augusto.doceskotlin.objetos.Cliente
import com.augusto.doceskotlin.objetos.Encomenda

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CadastrarEncomendaFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CadastrarEncomendaFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var bind: FragmentCadastrarEncomendaBinding? = null

    private var encomendaMapper : EncomendaMapper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        bind = FragmentCadastrarEncomendaBinding.inflate(layoutInflater, container, false)
        encomendaMapper = EncomendaMapper(bind!!)

        encomendaMapper!!.recyclerViewAdapter!!.lista = ArrayList()
        container!!.removeAllViews()
        return bind!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        encomendaMapper!!.botaoSalvar!!.setOnClickListener {
            encomendaMapper!!.botaoSalvar!!.isEnabled = false
            if (encomendaMapper!!.validarEntradas()) {
                try {
                    val novaEncomenda = Encomenda(
                        Cliente(encomendaMapper!!.nomeCliente!!.text.toString()),
                        encomendaMapper!!.calendario.timeInMillis,
                        encomendaMapper!!.recyclerViewAdapter!!.lista!!,
                        null,
                        false
                    )
                    EncomendasTeste.listaEncomendasTeste.add(novaEncomenda)
                    var intent = Intent(requireActivity(), MainActivity::class.java)
                    requireActivity().startActivity(intent)
                    requireActivity().finish()
                } catch (e: Exception) {
                    Toast.makeText(context, "Erro Criando Encomenda", Toast.LENGTH_SHORT).show()
                }

            } else {
                encomendaMapper!!.botaoSalvar!!.isEnabled = true
            }
        }

    }





}


