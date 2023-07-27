package com.augusto.doceskotlin.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.augusto.doceskotlin.R
import com.augusto.doceskotlin.viewHolder.DoceRecyclerViewViewHolder
import com.augusto.doceskotlin.objetos.Doce

class RecyclerViewCadastrarEncomendaAdapter(val context: Context, val lista: MutableList<Doce>) :
    RecyclerView.Adapter<DoceRecyclerViewViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoceRecyclerViewViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.uma_linha_doce, parent, false)
        return DoceRecyclerViewViewHolder(view)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(holder: DoceRecyclerViewViewHolder, position: Int) {
        holder.imagem.setImageResource(lista[position].imagemDoce!!.toInt())
        holder.nome.text = lista[position].nomeDoce
        holder.quantidade.text = lista[position].quantidadeDoce.toString()

    }

}