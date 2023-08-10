package com.augusto.doceskotlin.adapters.doces

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.augusto.doceskotlin.R
import com.augusto.doceskotlin.viewHolder.DoceRecyclerViewViewHolder
import com.augusto.doceskotlin.objetos.Doce

abstract class DocesRecyclerViewAdapter : RecyclerView.Adapter<DoceRecyclerViewViewHolder>() {

    var lista: MutableList<Doce>? = ArrayList()
    var context: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoceRecyclerViewViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.uma_linha_doce, parent, false)
        return DoceRecyclerViewViewHolder(view)
    }

    override fun getItemCount(): Int {
        return lista!!.size
    }

    override fun onBindViewHolder(holder: DoceRecyclerViewViewHolder, position: Int) {
        holder.imagem.setImageResource(
            holder.imagem.resources.getIdentifier(
                lista?.get(position)?.imagemDoce!!,
                null,
                null
            )
        )
        holder.nome.text = lista!![position].nomeDoce

    }

}