package com.augusto.doceskotlin

import android.app.DatePickerDialog
import android.content.Context
import android.widget.EditText
import com.augusto.doceskotlin.adapters.RecyclerViewInicioAdapter
import com.augusto.doceskotlin.fragments.InicioFragment
import com.augusto.doceskotlin.singletons.OperacoesFirebase
import java.util.Calendar


class Calendario {

    fun abrirCalendario(context: Context, calendario: Calendar, field: EditText) {

        val datepicker = DatePickerDialog(context)

        datepicker.show()

        datepicker.setOnDateSetListener { view, year, month, dayOfMonth ->
            calendario.set(year, month, dayOfMonth)
            field.setText(FORMATADOR_DATA.format(calendario.timeInMillis))
        }

    }

    fun pegarSemana(
        recyclerViewAdapter: RecyclerViewInicioAdapter, inicioFragment: InicioFragment,

        ){
        val datepicker = DatePickerDialog(inicioFragment.requireContext())
        val calendario = Calendar.getInstance()
        val inicio = Calendar.getInstance()
        val fim = Calendar.getInstance()

        datepicker.show()

        datepicker.setOnDateSetListener { view, year, month, dayOfMonth ->
            calendario.set(year, month, dayOfMonth,0,0,0)
            inicio.set(year, month, dayOfMonth-5,0,0,0)
            fim.set(year, month, dayOfMonth+1,23,59,59)

            OperacoesFirebase.pegarEncomendasSemana(inicio, fim, recyclerViewAdapter, inicioFragment)

        }
    }


}