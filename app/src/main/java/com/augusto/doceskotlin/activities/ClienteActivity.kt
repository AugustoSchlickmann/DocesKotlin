package com.augusto.doceskotlin.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.augusto.doceskotlin.ARG_PARAM_CLIENTE_PARCELABLE
import com.augusto.doceskotlin.R
import com.augusto.doceskotlin.databinding.ActivityClienteBinding
import com.augusto.doceskotlin.objetos.Cliente

class ClienteActivity : AppCompatActivity() {

    var bind: ActivityClienteBinding? = null
    var cliente: Cliente? = null
    var toolBar: Toolbar? = null
    var textViewNome: TextView? = null
    var textViewTelefone: TextView? = null
    var buttonCadastrarEncomenda: Button? = null
    var buttonSalvar: Button? = null
    var cancelarEdicao: MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carregando)

        cliente = intent.getParcelableExtra(ARG_PARAM_CLIENTE_PARCELABLE)

        if (cliente == null) {
            Toast.makeText(this, "Cliente nulo", Toast.LENGTH_SHORT).show()
            finish()
        } else {
            bind = ActivityClienteBinding.inflate(layoutInflater)
            toolBar = bind!!.ClienteActivityToolbar
            setSupportActionBar(toolBar)
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            textViewNome = bind!!.ClienteActivityEditTextNome
            textViewTelefone = bind!!.ClienteActivityEditTextTelefone
            colocarDados()
        }
    }

    private fun colocarDados() {
        textViewNome!!.text = cliente?.nome
        if(cliente?.telefone!!.isNotBlank()) {
            textViewTelefone!!.text = cliente?.telefone
            textViewTelefone!!.visibility = View.VISIBLE
        }
        setContentView(bind!!.root)
    }
}