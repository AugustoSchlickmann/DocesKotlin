package com.augusto.doceskotlin.models

class Cliente(var nome: String?) {

    var id: String? = null
    var telefone : String? = null

    var imagem : String? = null


    override fun toString(): String {
        return "Cliente(id=$id, nome=$nome, telefone=$telefone, imagem=$imagem)"
    }


}