package com.augusto.doceskotlin.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.augusto.doceskotlin.ARG_PARAM_ENCOMENDA_PARCELABLE
import com.augusto.doceskotlin.ARG_PARAM_ID_ENCOMENDA
import com.augusto.doceskotlin.EncomendaMapper
import com.augusto.doceskotlin.FORMATADOR_DATA
import com.augusto.doceskotlin.FORMATADOR_HORA
import com.augusto.doceskotlin.R
import com.augusto.doceskotlin.databinding.FragmentCadastrarEncomendaBinding
import com.augusto.doceskotlin.objetos.Encomenda
import com.google.android.material.appbar.AppBarLayout

class EncomendaActivity : AppCompatActivity() {

    private var encomendaMapper: EncomendaMapper? = null
    var bind: FragmentCadastrarEncomendaBinding? = null

    var idEncomenda: String? = null

    var toolBar: Toolbar? = null
    var appBarLayout: AppBarLayout? = null
    var cancelarEdicao: MenuItem? = null
    var encomenda: Encomenda? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carregando)


        idEncomenda = intent.getStringExtra(ARG_PARAM_ID_ENCOMENDA)
        encomenda = intent.getParcelableExtra(ARG_PARAM_ENCOMENDA_PARCELABLE)

        if (encomenda == null) {
            Toast.makeText(this, "Encomenda sem identificador", Toast.LENGTH_SHORT).show()
            finish()
        } else {
            bind = FragmentCadastrarEncomendaBinding.inflate(layoutInflater)
            encomendaMapper = EncomendaMapper(bind!!)
            toolBar = bind!!.toolbarEncomenda
            setSupportActionBar(toolBar)
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            appBarLayout = bind!!.appBarLayoutEncomenda
            appBarLayout!!.visibility = View.VISIBLE
            //OperacoesFirebase.pegarEncomendoPorId(idEncomenda!!, this)
            colocarDados(encomenda!!)
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
            //R.id.EncomendaMenuExcluirEncomenda ->
            R.id.encomenda_menu_MarcarComoFeita -> encomenda!!.feita = true
        }

        return super.onOptionsItemSelected(item)
    }

    private fun vendo() {
        cancelarEdicao?.isVisible = false
        if (encomenda!!.feita == true) {
            toolBar!!.title = "Encomenda Feita"
        } else {
            toolBar!!.title = "Encomenda"
        }
        if (encomenda!!.cliente!!.telefone!!.isBlank()) {
            encomendaMapper!!.telefoneCliente.visibility = View.GONE
        } else {
            encomendaMapper!!.telefoneCliente.visibility = View.VISIBLE
        }
        encomendaMapper!!.botaoSalvar.visibility = View.INVISIBLE
        encomendaMapper!!.nomeCliente.isFocusableInTouchMode = false
        encomendaMapper!!.telefoneCliente.isFocusableInTouchMode = false
    }

    private fun editando() {
        cancelarEdicao!!.isVisible = true
        toolBar!!.title = "Editando Encomenda..."

        if (encomenda!!.cliente!!.telefone == null) {
            encomendaMapper!!.telefoneCliente.visibility = View.VISIBLE
        }
        encomendaMapper!!.botaoSalvar.visibility = View.VISIBLE
        encomendaMapper!!.nomeCliente.isFocusableInTouchMode = true
        encomendaMapper!!.telefoneCliente.isFocusableInTouchMode = true

    }

    fun colocarDados(encomendaRemota: Encomenda) {
        this.encomenda = encomendaRemota
        encomendaMapper!!.nomeCliente.setText(encomenda!!.cliente!!.nome)
        encomendaMapper!!.data.setText(FORMATADOR_DATA.format(encomenda!!.data!!))
        encomendaMapper!!.hora.setText(FORMATADOR_HORA.format(encomenda!!.data!!))
        encomendaMapper!!.recyclerViewAdapter.lista = encomenda!!.doces

        for (doce in encomenda!!.doces!!) {
            encomendaMapper!!.valorTotal += doce.valorDoce!! * doce.quantidadeDoce
        }

        encomendaMapper!!.textViewValorTotal.text = "R$: " + "%.2f".format(encomendaMapper!!.valorTotal)
        setContentView(bind!!.root)
        vendo()

    }

}