package com.augusto.doceskotlin.singletons

import android.app.TimePickerDialog
import android.content.Context
import android.widget.EditText
import com.augusto.doceskotlin.FORMATADOR_HORA
import java.util.Calendar

object Relogio {

    fun abrirRelogio(context: Context, calendario: Calendar, field: EditText) {

        val timepicker = TimePickerDialog(
            context, { view, hourOfDay, minute ->
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