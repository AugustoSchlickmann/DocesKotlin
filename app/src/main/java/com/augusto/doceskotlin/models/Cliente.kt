package com.augusto.doceskotlin.models

class Cliente(var id: String?, var nome: String?) {

    var telefone : String? = null

    var imagem : String? = null


    override fun toString(): String {
        return "Cliente(id=$id, nome=$nome, telefone=$telefone, imagem=$imagem)"
    }


}