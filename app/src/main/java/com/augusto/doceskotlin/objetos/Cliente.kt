package com.augusto.doceskotlin.objetos

class Cliente(var nome: String?, var telefone: String? ) {

    private var imagem: String? = null

    constructor() : this(null,null)

    override fun toString(): String {
        return "Cliente(nome=$nome, telefone=$telefone, imagem=$imagem)"
    }


}