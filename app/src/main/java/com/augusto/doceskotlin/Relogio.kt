package com.augusto.doceskotlin

import android.app.TimePickerDialog
import android.content.Context
import android.widget.EditText
import java.util.Calendar

class Relogio {

    fun abrirRelogio(context: Context, calendario: Calendar, field: EditText) {
        val timepicker = TimePickerDialog(
            context,
            TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                calendario.set(Calendar.HOUR_OF_DAY, hourOfDay)
                calendario.set(Calendar.MINUTE, minute)
                calendario.set(Calendar.SECOND, 0)
                field.setText(FORMATADOR_HORA.format(calendario.timeInMillis))
            },
            0,
            0,
            true
        )

        timepicker.show()

    }
}