package com.augusto.doceskotlin.singletons

import com.augusto.doceskotlin.R
import com.augusto.doceskotlin.objetos.Doce

object ListaDeDoces {

    private var doces: MutableList<Doce>? = null

    private fun criarDoces() {
        doces = ArrayList()

        doces!!.add(Doce("id1", "Ninho", R.drawable.ninho.toString(), 1.50))
        doces!!.add(Doce("id2", "Beijinho", R.drawable.beijinho.toString(), 1.20))
        doces!!.add(Doce("id3", "Nozes", R.drawable.nozes.toString(), 1.50))
        doces!!.add(Doce("id4", "Brigadeiro", R.drawable.brigadeiro.toString(), 1.20))
        doces!!.add(Doce("id5", "Morango", R.drawable.morango.toString(), 1.20))
        doces!!.add(Doce("id99", "Selecione", R.drawable.logo.toString(), 1.20))
    }

    fun pegarLista(): MutableList<Doce>? {
        return if (doces == null) {
            criarDoces()
            doces
        } else {
            doces
        }
    }

}