package com.augusto.doceskotlin

import android.app.Dialog
import android.content.Context
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.augusto.doceskotlin.objetos.Doce

class DialogoDoce {

    var imagem: ImageView? = null
    var botao: Button? = null
    var qtd: EditText? = null

    fun adicionarDoce(doce: Doce, context: Context): Dialog {
        val dialog = Dialog(context)
        dialog.setContentView(R.layout.dialogo_doce)
        dialog.window!!.setBackgroundDrawableResource(R.drawable.edit_text_rounded)

        imagem = dialog.findViewById(R.id.DialogoDoceImageView)
        botao = dialog.findViewById(R.id.DialogoDoceButton)
        qtd = dialog.findViewById(R.id.DialogoDoceEditTextNumero)

        qtd!!.inputType = InputType.TYPE_CLASS_NUMBER
        qtd!!.hint = "Quantidade..."
        imagem!!.setImageResource(doce.imagemDoce!!.toInt())
        qtd!!.requestFocus()
        dialog.show()
        return dialog

    }

    fun removerDoce() {

    }

    fun mudarQtdDoce() {

    }

    fun mudarValorDoce() {

    }
}