package com.augusto.doceskotlin

import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import com.augusto.doceskotlin.adapters.SpinnerDocesAdapter
import com.augusto.doceskotlin.dialogos.DialogoDoceAdicionar
import com.augusto.doceskotlin.objetos.Doce
import com.augusto.doceskotlin.singletons.ListaDeDoces

class SpinnerDoces(var encomendaMapper: EncomendaMapper) : AdapterView.OnItemSelectedListener {

    init {
        ListaDeDoces.pegarDocesFirebaseSpinner(this)
    }

    fun popularizarSpinner() {
        val spinnerDocesAdapter = SpinnerDocesAdapter(encomendaMapper.bind.root.context, ListaDeDoces.doces!!)
        encomendaMapper.spinner.adapter = spinnerDocesAdapter
        encomendaMapper.spinner.onItemSelectedListener = this
        encomendaMapper.spinner.setSelection(spinnerDocesAdapter.count)
        encomendaMapper.spinner.visibility = View.VISIBLE
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        if (parent != null && position < parent.count) {
            val doceSelecionado = parent.getItemAtPosition(position) as Doce
            if (!encomendaMapper.recyclerViewAdapter.lista!!.contains(doceSelecionado)) {
                val doceAdicionado = doceSelecionado.copy()
                DialogoDoceAdicionar(doceAdicionado, encomendaMapper, encomendaMapper.bind.root.context)
            } else {
                Toast.makeText(
                    encomendaMapper.bind.root.context,
                    "Doce já adicionado",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

}