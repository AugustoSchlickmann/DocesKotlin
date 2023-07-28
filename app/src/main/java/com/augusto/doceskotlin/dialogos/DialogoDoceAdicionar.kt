package com.augusto.doceskotlin.dialogos

import android.content.Context
import android.text.InputType
import android.widget.Toast
import com.augusto.doceskotlin.EncomendaMapper
import com.augusto.doceskotlin.R
import com.augusto.doceskotlin.objetos.Doce

class DialogoDoceAdicionar(doce: Doce, encomendaMapper: EncomendaMapper, context: Context) : DialogoDoce(doce, context) {

    init {
        qtd!!.inputType = InputType.TYPE_CLASS_NUMBER
        botao!!.setOnClickListener{
            try {
                if (qtd!!.text.toString().toInt() > 0) {
                    doce.quantidadeDoce = qtd!!.text.toString().toInt()
                    encomendaMapper.adicionouDoce(doce)
                    dialog.dismiss()

                } else {
                    Toast.makeText(
                        context,
                        context.getString(R.string.ToastDadosInvalidos),
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            } catch (e: Exception) {
                Toast.makeText(
                    context,
                    context.getString(R.string.ToastDadosInvalidos),
                    Toast.LENGTH_SHORT
                )
                    .show()
                println(e.message)
            }
        }
        dialog.show()
    }
}