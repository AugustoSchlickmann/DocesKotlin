package com.augusto.doceskotlin.adapters.doces

import android.content.Context
import com.augusto.doceskotlin.dialogos.DialogoDoceAlterarValor
import com.augusto.doceskotlin.viewHolder.DoceRecyclerViewViewHolder

class EditarDocesRecyclerViewAdapter(context: Context?) : DocesRecyclerViewAdapter() {

    init {
        this.context = context
    }

    override fun onBindViewHolder(holder: DoceRecyclerViewViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)

        holder.quantidade.text = lista!![position].valorDoce.toString()
        holder.constraintLayout.setOnClickListener {
            DialogoDoceAlterarValor(lista!![position], context!!, this, position)
        }

    }
}