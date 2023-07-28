package com.augusto.doceskotlin.dialogos

import android.content.Context
import android.text.InputType
import android.widget.Toast
import com.augusto.doceskotlin.EncomendaMapper
import com.augusto.doceskotlin.R
import com.augusto.doceskotlin.objetos.Doce

class DialogoDoceAlterarQuantidade(doce: Doce, context: Context,encomendaMapper: EncomendaMapper) : DialogoDoce(doce, context)  {

    init {
        qtd!!.inputType = InputType.TYPE_CLASS_NUMBER
        qtd!!.setText(doce.quantidadeDoce.toString())
        botao!!.setOnClickListener{
            try {
                if (qtd!!.text.toString().toInt() > 0) {
                    val quantidadeAnterior = doce.quantidadeDoce
                    doce.quantidadeDoce = qtd!!.text.toString().toInt()
                    encomendaMapper.alterouDoce(doce,quantidadeAnterior)
                    dialog.dismiss()

                } else {
                    encomendaMapper.removeuDoce(doce)
                    dialog.dismiss()
                }
            } catch (e: Exception) {
                Toast.makeText(
                    context,
                    context.getString(R.string.ToastDadosInvalidos),
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
        }
        dialog.show()
    }

}