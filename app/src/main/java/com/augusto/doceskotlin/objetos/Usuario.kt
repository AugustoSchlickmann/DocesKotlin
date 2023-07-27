package com.augusto.doceskotlin.objetos

class Usuario {

    var idUsuario: String?

    var nome: String?

    var email: String?

    var senha: String? = null

    var imagem: String? = null

    constructor(idUsuario: String, nome: String, email: String) {
        this.idUsuario = idUsuario
        this.nome = nome
        this.email = email
    }

    override fun toString(): String {
        return "To String do Usuario: (idUsuario=$idUsuario, nome=$nome, email=$email, senha=$senha, imagem=$imagem)"
    }


}