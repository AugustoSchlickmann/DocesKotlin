package com.augusto.doceskotlin.singletons

import android.content.Context
import android.widget.EditText
import android.widget.Toast
import com.augusto.doceskotlin.R
import com.augusto.doceskotlin.objetos.Doce
import com.google.android.material.textfield.TextInputEditText

object ValidarEntradas {

    fun doCadastroUsuario(nome: TextInputEditText, email: TextInputEditText, senha: TextInputEditText, confirmarSenha: TextInputEditText): Boolean {
        try {
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

        } catch (e: Exception) {
            return false
        }
        return true
    }

    fun doLogin(email: TextInputEditText, senha: TextInputEditText): Boolean {
        try {
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

        } catch (e: Exception) {
            return false
        }
        return true
    }

    fun daEncomenda(nomeCliente: EditText, data: EditText, hora: EditText, lista: MutableList<Doce>, context: Context): Boolean {
        try {
            if (nomeCliente.text.isBlank()) {
                Toast.makeText(context, "Cliente Vazio", Toast.LENGTH_SHORT).show()
                return false
            }
            if (data.text.isBlank()) {
                Toast.makeText(context, "Selecione Data", Toast.LENGTH_SHORT).show()
                return false
            }
            if (hora.text.isBlank()) {
                Toast.makeText(context, "Selecione Hora", Toast.LENGTH_SHORT).show()
                return false
            }
            if (lista!!.size == 0) {
                Toast.makeText(context, "Lista Vazia", Toast.LENGTH_SHORT).show()
                return false
            }

        } catch (e: Exception) {
            Toast.makeText(context, context.getString(R.string.ToastDadosInvalidos), Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    fun doCliente(editTextViewNome:EditText, context: Context): Boolean {
        try {
            if (editTextViewNome.text.isBlank()) {
                Toast.makeText(context, "Nome Vazio", Toast.LENGTH_SHORT).show()
                return false
            }

        } catch (e: Exception) {
            Toast.makeText(context, R.string.ToastDadosInvalidos, Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }
}