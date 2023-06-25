package com.augusto.doceskotlin.models

class Usuario {

    var idUsuario: String?
        get() = field
        set(value) {
            field = value
        }

    var nome: String?

    var email: String?
        get() = field
        set(email) {
            field = email
        }

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