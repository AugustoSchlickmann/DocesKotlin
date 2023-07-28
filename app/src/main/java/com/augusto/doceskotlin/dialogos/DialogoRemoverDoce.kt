package com.augusto.doceskotlin.dialogos

import android.app.Dialog
import android.content.Context
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.augusto.doceskotlin.EncomendaMapper
import com.augusto.doceskotlin.R
import com.augusto.doceskotlin.objetos.Doce

class DialogoRemoverDoce(doce: Doce, context: Context, encomendaMapper: EncomendaMapper) {

    var imagem: ImageView? = null
    var botaoOk: Button? = null
    var botaoCancelar: Button? = null
    var textView : TextView? = null
    val dialog = Dialog(context)

    init {
        dialog.setContentView(R.layout.dialogo_doce_remover)
        dialog.window!!.setBackgroundDrawableResource(R.drawable.edit_text_rounded)
        imagem = dialog.findViewById(R.id.DialogoRemoverDoceImageView)
        botaoOk = dialog.findViewById(R.id.DialogoRemoverDoceBotaoOk)
        botaoCancelar = dialog.findViewById(R.id.DialogoRemoverDoceBotaoCancelar)
        textView =  dialog.findViewById(R.id.DialogoRemoverDoceTextView)
        imagem!!.setImageResource(doce.imagemDoce!!.toInt())

        textView!!.text = "Remover ${doce.nomeDoce}?"

        botaoOk!!.setOnClickListener{
            encomendaMapper.removeuDoce(doce)
            dialog.dismiss()
        }

        botaoCancelar!!.setOnClickListener{
            dialog.dismiss()
        }

        dialog.show()
    }
}