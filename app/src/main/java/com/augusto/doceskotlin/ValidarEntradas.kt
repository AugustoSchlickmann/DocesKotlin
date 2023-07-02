package com.augusto.doceskotlin

import com.google.android.material.textfield.TextInputEditText

interface ValidarEntradas {

    fun validarEntradasLogin(dados: Array<TextInputEditText>): String {
       for (d in dados){
           if (d.text.toString().isBlank()){
               println(d.text)
               return d.hint.toString()
           }
       }
        return "0"
    }
}