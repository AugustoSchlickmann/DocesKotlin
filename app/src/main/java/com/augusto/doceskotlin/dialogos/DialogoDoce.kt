package com.augusto.doceskotlin.dialogos

import android.app.Dialog
import android.content.Context
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.augusto.doceskotlin.R
import com.augusto.doceskotlin.objetos.Doce

abstract class DialogoDoce(doce: Doce, context: Context) {

    var imagem: ImageView? = null
    var botao: Button? = null
    var qtd: EditText? = null
    val dialog = Dialog(context)

    init {
        dialog.setContentView(R.layout.dialogo_doce)
        dialog.window!!.setBackgroundDrawableResource(R.drawable.edit_text_rounded)
        imagem = dialog.findViewById(R.id.DialogoDoceImageView)
        botao = dialog.findViewById(R.id.DialogoDoceButton)
        qtd = dialog.findViewById(R.id.DialogoDoceEditTextNumero)
        imagem?.setImageResource(imagem?.resources!!.getIdentifier(doce.imagemDoce,null,null))
        qtd!!.requestFocus()
    }

}