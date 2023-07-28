package com.augusto.doceskotlin

import com.google.android.material.textfield.TextInputEditText

class ValidarEntradas {

    fun validar(nome: TextInputEditText, email: TextInputEditText, senha: TextInputEditText, confirmarSenha: TextInputEditText): Boolean {
        val aviso: String

        if (nome.text.toString().isBlank()) {
            aviso = "Preencha o Nome"
            nome.error = aviso
            return false
        }
        if (nome.text.toString().trim().length < 3) {
            aviso = "Nome Muito Curto"
            nome.error = aviso
            return false
        }

        if (email.text.toString().isBlank()) {
            aviso = "Preencha o E-mail "
            email.error = aviso
            return false
        }

        if (senha.text.toString().isBlank()) {
            aviso = "Preencha a Senha"
            senha.error = aviso
            return false
        }

        if (confirmarSenha.text.toString().isBlank()) {
            aviso = "Confirme a Senha"
            confirmarSenha.error = aviso
            return false
        }

        if (senha.text.toString() != confirmarSenha.text.toString()) {
            aviso = "Senhas Diferentes"
            confirmarSenha.error = aviso
            senha.error = aviso
            return false
        }

        return true
    }
    fun validar(email: TextInputEditText, senha: TextInputEditText): Boolean {

        val aviso: String

        if (email.text.toString().isBlank()) {
            aviso = "Preencha o E-mail "
            email.error = aviso
            return false
        }

        if (senha.text.toString().isBlank()) {
            aviso = "Preencha a Senha"
            senha.error = aviso
            return false
        }

        return true
    }
}