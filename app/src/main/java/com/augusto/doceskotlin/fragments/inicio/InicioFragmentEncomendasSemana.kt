package com.augusto.doceskotlin.fragments.inicio

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.augusto.doceskotlin.singletons.Calendario
import com.augusto.doceskotlin.R

class InicioFragmentEncomendasSemana : InicioFragment() {

    override fun onResume() {
        super.onResume()
        Calendario.pegarSemana(recyclerViewAdapter!!, this)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.lista_encomenda_menu_calendario, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.lista_encomenda_menu_AbrirCalendario -> Calendario.pegarSemana(recyclerViewAdapter!!, this)
        }
        return super.onOptionsItemSelected(item)
    }


}