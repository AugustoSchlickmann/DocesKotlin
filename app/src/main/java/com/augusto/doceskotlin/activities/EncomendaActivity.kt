package com.augusto.doceskotlin.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.augusto.doceskotlin.ARG_PARAM_ENCOMENDA_PARCELABLE
import com.augusto.doceskotlin.EncomendaMapper
import com.augusto.doceskotlin.R
import com.augusto.doceskotlin.SpinnerDoces
import com.augusto.doceskotlin.databinding.FragmentCadastrarEncomendaBinding
import com.augusto.doceskotlin.objetos.Encomenda
import com.augusto.doceskotlin.singletons.OperacoesFirebase
import com.google.android.material.appbar.AppBarLayout

class EncomendaActivity : AppCompatActivity() {

    private var encomendaMapper: EncomendaMapper? = null
    var bind: FragmentCadastrarEncomendaBinding? = null
    private var toolBar: Toolbar? = null
    private var appBarLayout: AppBarLayout? = null
    private var cancelarEdicao: MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carregando)

        val encomenda: Encomenda? = intent.getParcelableExtra(ARG_PARAM_ENCOMENDA_PARCELABLE)

        if (encomenda == null) {
            Toast.makeText(this, "Encomenda não encontrada", Toast.LENGTH_SHORT).show()
            finish()
        } else {
            bind = FragmentCadastrarEncomendaBinding.inflate(layoutInflater)
            encomendaMapper = EncomendaMapper(bind!!, false, encomenda)

            toolBar = bind!!.toolbarEncomenda
            setSupportActionBar(toolBar)
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            appBarLayout = bind!!.appBarLayoutEncomenda
            appBarLayout!!.visibility = View.VISIBLE

            encomendaMapper!!.buttonSalvar.setOnClickListener {
                encomendaMapper!!.salvarEncomenda(encomendaMapper!!.encomenda.id!!)
                vendo()
            }

            encomendaMapper!!.colocarDadosNaTela()
            vendo()
            setContentView(bind!!.root)
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        cancelarEdicao = menu.findItem(R.id.encomenda_menu_CancelarEdicao)
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.encomenda_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
            R.id.encomenda_menu_EditarEncomenda -> editando()
            R.id.encomenda_menu_CancelarEdicao -> vendo()

            R.id.encomenda_menu_ExcluirEncomenda -> OperacoesFirebase.excluirEncomenda(
                encomendaMapper!!.encomenda,
                this
            )

            R.id.encomenda_menu_MarcarComoFeita -> OperacoesFirebase.marcarEncomendaFeita(
                encomendaMapper!!.encomenda,
                this
            )
        }

        return super.onOptionsItemSelected(item)
    }


    private fun vendo() {
        if (encomendaMapper!!.encomenda.feita == true) {
            supportActionBar!!.title = "Encomenda Feita"
        } else {
            supportActionBar!!.title = "Encomenda de ${encomendaMapper!!.encomenda.quantidadeDocesEncomenda} Doces"
        }

        if (encomendaMapper!!.editTextTelefoneCliente.text.isBlank()) {
            encomendaMapper!!.editTextTelefoneCliente.visibility = View.GONE
        }

        encomendaMapper!!.buttonSalvar.visibility = View.INVISIBLE
        encomendaMapper!!.editTextNomeCliente.isFocusableInTouchMode = false
        encomendaMapper!!.editTextTelefoneCliente.isFocusableInTouchMode = false
        cancelarEdicao?.isVisible = false
        encomendaMapper!!.spinner.visibility = View.GONE

        if(encomendaMapper!!.encomenda.obs == null){
            encomendaMapper!!.imageViewObs.visibility = View.INVISIBLE
        }else{
            encomendaMapper!!.imageViewObs.visibility = View.VISIBLE
        }

    }

    private fun editando() {
        supportActionBar!!.title = "Editando Encomenda..."
        encomendaMapper!!.editTextTelefoneCliente.visibility = View.VISIBLE
        encomendaMapper!!.buttonSalvar.visibility = View.VISIBLE
        encomendaMapper!!.editTextNomeCliente.isFocusableInTouchMode = true
        encomendaMapper!!.editTextTelefoneCliente.isFocusableInTouchMode = true
        cancelarEdicao?.isVisible = true

        if (encomendaMapper!!.spinner.adapter == null) {
            SpinnerDoces(encomendaMapper!!)
        } else {
            encomendaMapper!!.spinner.visibility = View.VISIBLE
        }

        encomendaMapper!!.imageViewObs.visibility = View.VISIBLE

    }

}