package com.augusto.doceskotlin.adapters.doces

import com.augusto.doceskotlin.EncomendaMapper
import com.augusto.doceskotlin.dialogos.DialogoDoceAlterarQuantidade
import com.augusto.doceskotlin.dialogos.DialogoDoceRemover
import com.augusto.doceskotlin.viewHolder.DoceRecyclerViewViewHolder

class DocesDaEncomendaRecyclerViewAdapter(encomendaMapper: EncomendaMapper) : DocesRecyclerViewAdapter() {

    private var encomendaMapper: EncomendaMapper? = null

    init {
        this.encomendaMapper = encomendaMapper
        this.context = encomendaMapper.bind.root.context
    }

    override fun onBindViewHolder(holder: DoceRecyclerViewViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)

        holder.quantidade.text = lista!![position].quantidadeDoce.toString()
        holder.constraintLayout.setOnClickListener {
            DialogoDoceAlterarQuantidade(lista!![position], context!!, encomendaMapper!!)
        }

        holder.constraintLayout.setOnLongClickListener {
            DialogoDoceRemover(lista!![position], context!!, encomendaMapper!!)
            true
        }

    }
}