package com.augusto.doceskotlin.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.augusto.doceskotlin.EDITAR_DOCES
import com.augusto.doceskotlin.EncomendaMapper
import com.augusto.doceskotlin.dialogos.DialogoRemoverDoce
import com.augusto.doceskotlin.R
import com.augusto.doceskotlin.VER_DOCES_A_FAZER
import com.augusto.doceskotlin.dialogos.DialogoDoceAlterarQuantidade
import com.augusto.doceskotlin.dialogos.DialogoDoceAlterarValor
import com.augusto.doceskotlin.viewHolder.DoceRecyclerViewViewHolder
import com.augusto.doceskotlin.objetos.Doce

class DocesRecyclerViewAdapter : RecyclerView.Adapter<DoceRecyclerViewViewHolder> {

    var lista: MutableList<Doce>? = ArrayList()
    var context: Context?
    var tipoLista : Int = 0
    var encomendaMapper : EncomendaMapper? = null

    constructor(encomendaMapper : EncomendaMapper){
        this.encomendaMapper  = encomendaMapper
        this.context = encomendaMapper.bind.root.context
    }

    constructor(context: Context?, param1: Int){
        this.context = context
        this.tipoLista = param1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoceRecyclerViewViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.uma_linha_doce, parent, false)
        return DoceRecyclerViewViewHolder(view)
    }

    override fun getItemCount(): Int {
        return lista!!.size
    }


    override fun onBindViewHolder(holder: DoceRecyclerViewViewHolder, position: Int) {
        holder.imagem.setImageResource(lista!![position].imagemDoce!!.toInt())
        holder.nome.text = lista!![position].nomeDoce

        when (tipoLista) {
            EDITAR_DOCES -> editandoValorDoces(holder, position)
            VER_DOCES_A_FAZER -> vendoDocesAFazer(holder, position)
            else -> cadastrandoEncomenda(holder, position)
        }

    }

    private fun cadastrandoEncomenda(holder: DoceRecyclerViewViewHolder, position: Int) {
        holder.quantidade.text = lista!![position].quantidadeDoce.toString()
        holder.constraintLayout.setOnClickListener{
            DialogoDoceAlterarQuantidade(lista!![position], context!!, encomendaMapper!!)
        }

        holder.constraintLayout.setOnLongClickListener{
            DialogoRemoverDoce(lista!![position], context!!, encomendaMapper!!)
            true
        }

    }

    private fun editandoValorDoces(holder: DoceRecyclerViewViewHolder, position: Int) {
        holder.quantidade.text = lista!![position].valorDoce.toString()
        holder.constraintLayout.setOnClickListener{
            DialogoDoceAlterarValor(lista!![position], context!!,this, position)
        }

    }

    private fun vendoDocesAFazer(holder: DoceRecyclerViewViewHolder, position: Int) {
        holder.quantidade.text = lista!![position].quantidadeDoce.toString()
        holder.constraintLayout.setOnClickListener{
            //IR PARA TELA QUE MOSTRA ENCOMENDAS QUE TEM O DOCE SELECIONADO
            //InicioFragment.newInstance(PROCURAR_PROXIMAS_ENCOMENDAS_COM_DOCE_SELECIONADO)
        }

    }



}