package com.augusto.doceskotlin.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.augusto.doceskotlin.ARG_PARAM_CLIENTE_PARCELABLE
import com.augusto.doceskotlin.FRAGMENT_CADASTRAR_ENCOMENDA_COM_CLIENTE
import com.augusto.doceskotlin.MAIN_ACTIVITY_QUAL_FRAGMENTO_CRIAR
import com.augusto.doceskotlin.PROCURAR_ENCOMENDAS_DO_CLIENTE
import com.augusto.doceskotlin.R
import com.augusto.doceskotlin.singletons.ValidarEntradas
import com.augusto.doceskotlin.databinding.ActivityClienteBinding
import com.augusto.doceskotlin.objetos.Cliente
import com.augusto.doceskotlin.singletons.OperacoesFirebase

class ClienteActivity : AppCompatActivity() {

    var cliente: Cliente? = null

    private var toolBar: Toolbar? = null
    private var editTextViewNome: EditText? = null
    private var editTextViewTelefone: EditText? = null
    private var buttonCadastrarEncomenda: Button? = null
    private var buttonSalvar: Button? = null
    private var cancelarEdicao: MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carregando)

        cliente = intent.getParcelableExtra(ARG_PARAM_CLIENTE_PARCELABLE)

        if (cliente == null) {
            Toast.makeText(this, "Cliente nulo", Toast.LENGTH_SHORT).show()
            finish()
        } else {
            val bind = ActivityClienteBinding.inflate(layoutInflater)
            toolBar = bind.ClienteActivityToolbar
            setSupportActionBar(toolBar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = "Cliente"
            editTextViewNome = bind.ClienteActivityEditTextNome
            editTextViewTelefone = bind.ClienteActivityEditTextTelefone
            buttonSalvar = bind.ClienteActivityBotaoSalvar
            buttonCadastrarEncomenda = bind.ClienteActivityBotaoCadastrarEncomenda

            buttonSalvar?.setOnClickListener {
                if (ValidarEntradas.doCliente(editTextViewNome!!, this)) {
                    cliente!!.nome = editTextViewNome?.text.toString()
                    cliente!!.telefone = editTextViewTelefone?.text.toString()
                    OperacoesFirebase.atualizarCliente(cliente!!, this)
                }
            }
            buttonCadastrarEncomenda?.setOnClickListener {
                val cadastrarEncomendaComCliente = Intent(
                    this,
                    MainActivity::class.java
                ).apply {
                    putExtra(MAIN_ACTIVITY_QUAL_FRAGMENTO_CRIAR, FRAGMENT_CADASTRAR_ENCOMENDA_COM_CLIENTE)
                    putExtra(ARG_PARAM_CLIENTE_PARCELABLE, cliente)
                }
                this.startActivity(cadastrarEncomendaComCliente)
                finish()
            }

            colocarDados(bind.root)
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        cancelarEdicao = menu.findItem(R.id.cliente_menu_CancelarEdicaoCliente)
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.cliente_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
            R.id.cliente_menu_EditarCliente -> editando()
            R.id.cliente_menu_CancelarEdicaoCliente -> vendo()
            R.id.cliente_menu_VerEncomendas -> {
                val procurarEncomendasDoCliente = Intent(
                    this,
                    MainActivity::class.java
                ).apply {
                    putExtra(MAIN_ACTIVITY_QUAL_FRAGMENTO_CRIAR, PROCURAR_ENCOMENDAS_DO_CLIENTE)
                    putExtra(ARG_PARAM_CLIENTE_PARCELABLE, cliente)
                }
                this.startActivity(procurarEncomendasDoCliente)
            }

        }
        return super.onOptionsItemSelected(item)
    }

    private fun colocarDados(bind: ConstraintLayout) {
        editTextViewNome?.setText(cliente?.nome)
        if (cliente?.telefone!!.isNotBlank()) {
            editTextViewTelefone?.setText(cliente?.telefone)
            editTextViewTelefone?.visibility = View.VISIBLE
        }
        vendo()
        setContentView(bind)
    }

    private fun vendo() {
        cancelarEdicao?.isVisible = false
        buttonSalvar?.isVisible = false
        if (editTextViewTelefone?.text!!.isBlank()) {
            editTextViewTelefone?.visibility = View.INVISIBLE
        }
        editTextViewNome?.isFocusableInTouchMode = false
        editTextViewTelefone?.isFocusableInTouchMode = false
    }

    private fun editando() {
        cancelarEdicao?.isVisible = true
        buttonSalvar?.visibility = View.VISIBLE
        editTextViewTelefone?.visibility = View.VISIBLE
        editTextViewNome?.isFocusableInTouchMode = true
        editTextViewTelefone?.isFocusableInTouchMode = true
    }


}