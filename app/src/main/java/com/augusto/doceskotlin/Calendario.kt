package com.augusto.doceskotlin

import android.app.DatePickerDialog
import android.content.Context
import android.widget.EditText
import java.text.SimpleDateFormat
import java.util.Calendar


class Calendario {

    fun abrirCalendario(context: Context, calendario: Calendar, field: EditText) {

        val datepicker = DatePickerDialog(context)

        datepicker.show()

        datepicker.setOnDateSetListener { view, year, month, dayOfMonth ->
            calendario.set(year, month, dayOfMonth)
            field.setText(FORMATADOR_DATA.format(calendario.timeInMillis))

            println("Data: " + calendario.time)
        }

    }


}