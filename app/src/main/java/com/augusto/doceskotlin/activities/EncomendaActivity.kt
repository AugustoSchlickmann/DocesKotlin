package com.augusto.doceskotlin.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.augusto.doceskotlin.ARG_PARAM_ID_ENCOMENDA
import com.augusto.doceskotlin.EncomendaMapper
import com.augusto.doceskotlin.FORMATADOR_DATA
import com.augusto.doceskotlin.FORMATADOR_HORA
import com.augusto.doceskotlin.R
import com.augusto.doceskotlin.databinding.FragmentCadastrarEncomendaBinding
import com.augusto.doceskotlin.objetos.Encomenda
import com.augusto.doceskotlin.singletons.EncomendasTeste
import com.google.android.material.appbar.AppBarLayout
import java.text.SimpleDateFormat

class EncomendaActivity : AppCompatActivity() {

    private var encomendaMapper: EncomendaMapper? = null
    var bind : FragmentCadastrarEncomendaBinding? = null

    var idEncomenda: String? = null

    var toolBar: Toolbar? = null
    var appBarLayout: AppBarLayout? = null
    var cancelarEdicao : MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = FragmentCadastrarEncomendaBinding.inflate(layoutInflater)
        setContentView(bind!!.root)

        idEncomenda = intent.getStringExtra(ARG_PARAM_ID_ENCOMENDA)

        encomendaMapper = EncomendaMapper(bind!!)


        toolBar = bind!!.toolbarEncomenda
        setSupportActionBar(toolBar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        appBarLayout = bind!!.appBarLayoutEncomenda
        appBarLayout!!.visibility = View.VISIBLE

        colocarDados(EncomendasTeste.listaEncomendasTeste[idEncomenda!!.toInt()])
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        cancelarEdicao = menu.findItem(R.id.EncomendaMenuCancelarEdicao)
        vendo()
        return super.onPrepareOptionsMenu(menu)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.encomenda_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> finish()
            R.id.EncomendaMenuEditarEncomenda -> editando()
            R.id.EncomendaMenuCancelarEdicao -> vendo()
            //R.id.EncomendaMenuExcluirEncomenda ->
            R.id.EncomendaMenuMarcarComoFeita -> EncomendasTeste.listaEncomendasTeste[idEncomenda!!.toInt()].feita = true
        }

        return super.onOptionsItemSelected(item)
    }

    private fun vendo() {
        cancelarEdicao!!.isVisible = false
        if (EncomendasTeste.listaEncomendasTeste[idEncomenda!!.toInt()].feita == true){
            toolBar!!.title="Encomenda Feita"
        }else{
            toolBar!!.title="Encomenda"
        }
        if (EncomendasTeste.listaEncomendasTeste[idEncomenda!!.toInt()].cliente!!.telefone == null){
            encomendaMapper!!.telefoneCliente.visibility = View.GONE
        }else{
            encomendaMapper!!.telefoneCliente.visibility = View.VISIBLE
        }
        encomendaMapper!!.botaoSalvar.visibility = View.GONE
        encomendaMapper!!.nomeCliente.isFocusableInTouchMode = false
        encomendaMapper!!.telefoneCliente.isFocusableInTouchMode = false
    }

    private fun editando() {
        cancelarEdicao!!.isVisible = true
        toolBar!!.title="Editando Encomenda..."

        if (EncomendasTeste.listaEncomendasTeste[idEncomenda!!.toInt()].cliente!!.telefone == null){
            encomendaMapper!!.telefoneCliente.visibility = View.VISIBLE
        }
        encomendaMapper!!.botaoSalvar.visibility = View.VISIBLE
        encomendaMapper!!.nomeCliente.isFocusableInTouchMode = true
        encomendaMapper!!.telefoneCliente.isFocusableInTouchMode = true

    }

    private fun colocarDados(encomenda: Encomenda) {
        encomendaMapper!!.nomeCliente.setText(encomenda.cliente!!.nome)
        encomendaMapper!!.data.setText(FORMATADOR_DATA.format(encomenda.data))
        encomendaMapper!!.hora.setText(FORMATADOR_HORA.format(encomenda.data))
        encomendaMapper!!.recyclerViewAdapter.lista = encomenda.doces
        encomendaMapper!!.recyclerViewAdapter!!.notifyDataSetChanged()

        for (doce in encomenda.doces) {
            encomendaMapper!!.valorTotal += doce.valorDoce * doce.quantidadeDoce
        }

        encomendaMapper!!.textViewValorTotal.text = "R$: " + "%.2f".format(encomendaMapper!!.valorTotal)

    }
}