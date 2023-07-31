package com.augusto.doceskotlin.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.augusto.doceskotlin.R
import com.augusto.doceskotlin.singletons.ValidarEntradas
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.ktx.Firebase

class CadastrarUsuarioActivity : AppCompatActivity(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastrar_usuario)

        val nome: TextInputEditText = findViewById(R.id.CadastrarUsuarioActivityTextInputEditTextNome)
        val email: TextInputEditText = findViewById(R.id.CadastrarUsuarioActivityTextInputEditTextEmail)
        val senha: TextInputEditText = findViewById(R.id.CadastrarUsuarioActivityTextInputEditTextSenha)
        val confirmarSenha: TextInputEditText = findViewById(R.id.CadastrarUsuarioActivityTextInputEditTextConfirmarSenha)
        val progressBar: ProgressBar = findViewById(R.id.CadastrarUsuarioActivityProgressBar)
        val botaoCadastrar: Button = findViewById(R.id.CadastrarUsuarioActivityBotaoCadastrar)
        val voltar: TextView = findViewById(R.id.CadastrarUsuarioActivityTextViewVoltar)


        botaoCadastrar.setOnClickListener {
            if (ValidarEntradas.doCadastroUsuario(nome, email, senha, confirmarSenha)) {
                progressBar.visibility = View.VISIBLE
                Firebase.auth.createUserWithEmailAndPassword(email.text.toString(), senha.text.toString()).addOnCompleteListener(this){task ->
                    if (task.isSuccessful){
                        cadastarUsuario(Firebase.auth.currentUser!!, nome.text.toString())
                    }else{
                        progressBar.visibility = View.GONE
                        try {
                            throw task.exception!!
                        } catch (senhaFraca: FirebaseAuthWeakPasswordException) {
                            Toast.makeText(this, "Senha Fraca",Toast.LENGTH_SHORT).show()
                            senha.error = "Senha Fraca"
                        } catch (emailJaExiste: FirebaseAuthUserCollisionException) {
                            Toast.makeText(this, "E-mail Em Uso",Toast.LENGTH_SHORT).show()
                            email.error = "E-mail em uso"
                        } catch (emailMalFormado: FirebaseAuthInvalidCredentialsException) {
                            Toast.makeText(this, "E-mail Inválido",Toast.LENGTH_SHORT).show()
                            email.error = "E-mail Inválido"
                        } catch (e: Exception) {
                            Toast.makeText(this, "Erro",Toast.LENGTH_SHORT).show()
                        }

                    }

                }.addOnFailureListener(this){
                    progressBar.visibility = View.GONE
                    Toast.makeText(this, "Falha",Toast.LENGTH_SHORT).show()
                }

            }else{
                Toast.makeText(this, R.string.ToastDadosInvalidos,Toast.LENGTH_SHORT).show()
            }
        }

        voltar.setOnClickListener {
            startActivity(Intent(this, EntrarActivity::class.java))
            finish()
        }
    }

    private fun cadastarUsuario(user : FirebaseUser, nome : String) {
        val profileUpdates = userProfileChangeRequest {
            displayName = nome
        }
        user.updateProfile(profileUpdates).addOnCompleteListener{
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }



}