package com.augusto.doceskotlin.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.augusto.doceskotlin.R
import com.augusto.doceskotlin.singletons.ValidarEntradas
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class EntrarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(Firebase.auth.currentUser != null){
            usuarioLogadoTrocarTela()
        }else{
            setContentView(R.layout.activity_entrar)

            val botaoEntrar : Button = findViewById(R.id.EntrarActivityBotaoEntrar)
            val botaoCriarConta: Button = findViewById(R.id.EntrarActivityBotaoCriarConta)
            val email: TextInputEditText = findViewById(R.id.EntrarActivityTextInputEditTextEmail)
            val senha: TextInputEditText = findViewById(R.id.EntrarActivityTextInputEditTextSenha)
            val progressBar : ProgressBar = findViewById(R.id.EntrarActivityProgressBar)

            botaoEntrar.setOnClickListener{
                if(ValidarEntradas.doLogin(email,senha)){
                    progressBar.visibility= View.VISIBLE
                    Firebase.auth.signInWithEmailAndPassword(email.text.toString(), senha.text.toString()).addOnCompleteListener(this) { task ->
                        if (task.isSuccessful){
                            usuarioLogadoTrocarTela()
                        }
                    }.addOnFailureListener{
                        progressBar.visibility= View.GONE
                        Toast.makeText(this, "Falha", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(this, R.string.ToastDadosInvalidos,Toast.LENGTH_SHORT).show()
                }
            }

            botaoCriarConta.setOnClickListener {
                startActivity(Intent(this, CadastrarUsuarioActivity::class.java))
                finish()
            }

        }
        }

    private fun usuarioLogadoTrocarTela() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

}