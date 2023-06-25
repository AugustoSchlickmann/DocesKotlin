package com.augusto.doceskotlin.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.augusto.doceskotlin.R
import com.augusto.doceskotlin.UsuarioSingleton
import com.augusto.doceskotlin.models.Usuario
import com.google.android.material.textfield.TextInputEditText

class EntrarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entrar)

        val botaoEntrar : Button = findViewById(R.id.EntrarActivityBotaoEntrar)
        val botaoCriarConta: Button = findViewById(R.id.EntrarActivityBotaoCriarConta)
        var email: TextInputEditText = findViewById(R.id.EntrarActivityTextInputEditTextEmail)
        var senha: TextInputEditText = findViewById(R.id.EntrarActivityTextInputEditTextSenha)

        botaoEntrar.setOnClickListener{
            UsuarioSingleton.usuarioAtualLogado = Usuario("1", "Testador", email.text.toString())
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        botaoCriarConta.setOnClickListener {
            startActivity(Intent(this, CadastrarUsuarioActivity::class.java))
            finish()
        }

    }
}