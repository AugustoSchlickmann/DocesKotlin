package com.augusto.doceskotlin.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.augusto.doceskotlin.R
import com.augusto.doceskotlin.singletons.UsuarioSingleton
import com.augusto.doceskotlin.ValidarEntradas
import com.augusto.doceskotlin.objetos.Usuario
import com.google.android.material.textfield.TextInputEditText

class CadastrarUsuarioActivity : AppCompatActivity(), ValidarEntradas {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastrar_usuario)

        val botaoCadastrar: Button = findViewById(R.id.CadastrarUsuarioActivityBotaoCadastrar)
        val nome: TextInputEditText = findViewById(R.id.CadastrarUsuarioActivityTextInputEditTextNome)
        val email: TextInputEditText = findViewById(R.id.CadastrarUsuarioActivityTextInputEditTextEmail)
        val senha: TextInputEditText = findViewById(R.id.CadastrarUsuarioActivityTextInputEditTextSenha)
        val confirmarSenha: TextInputEditText = findViewById(R.id.CadastrarUsuarioActivityTextInputEditTextConfirmarSenha)
        val voltar: TextView = findViewById(R.id.CadastrarUsuarioActivityTextViewVoltar)

        botaoCadastrar.setOnClickListener {

            if (validarDados(nome, email, senha, confirmarSenha)) {
                UsuarioSingleton.usuarioAtualLogado = Usuario("1", nome.text.toString(), email.text.toString())
                startActivity(Intent(this, MainActivity::class.java))
                finish()

            }

        }

        voltar.setOnClickListener {
            startActivity(Intent(this, EntrarActivity::class.java))
            finish()
        }
    }


    fun validarDados(
        nome: TextInputEditText,
        email: TextInputEditText,
        senha: TextInputEditText,
        confirmarSenha: TextInputEditText
    ): Boolean {
        var aviso: String


        if (nome.text.toString().isBlank()) {
            aviso = "Preencha o Nome"
            nome.setError(aviso)
            return false
        }
        if (nome.text.toString().trim().length < 3) {
            aviso = "Nome Muito Curto"
            nome.setError(aviso)
            return false
        }

        if (email.text.toString().isBlank()) {
            aviso = "Preencha o E-mail "
            email.setError(aviso)
            return false
        }

        if (senha.text.toString().isBlank()) {
            aviso = "Preencha a Senha"
            senha.setError(aviso)
            return false
        }

        if (confirmarSenha.text.toString().isBlank()) {
            aviso = "Confirme a Senha"
            confirmarSenha.setError(aviso)
            return false
        }

        if (senha.text.toString() != confirmarSenha.text.toString()) {
            aviso = "Senhas Diferentes"
            confirmarSenha.setError(aviso)
            senha.setError(aviso)
            return false
        }

        return true
    }
}