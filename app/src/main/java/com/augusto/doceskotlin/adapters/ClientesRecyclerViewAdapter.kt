package com.augusto.doceskotlin.adapters

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.augusto.doceskotlin.ARG_PARAM_CLIENTE_PARCELABLE
import com.augusto.doceskotlin.activities.ClienteActivity
import com.augusto.doceskotlin.databinding.UmaLinhaClienteBinding
import com.augusto.doceskotlin.objetos.Cliente
import com.augusto.doceskotlin.viewHolder.ClientesRecyclerViewViewHolder

class ClientesRecyclerViewAdapter(var clientes: MutableList<Cliente>) : RecyclerView.Adapter<ClientesRecyclerViewViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientesRecyclerViewViewHolder {
        return ClientesRecyclerViewViewHolder(UmaLinhaClienteBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = clientes.size

    override fun onBindViewHolder(holder: ClientesRecyclerViewViewHolder, position: Int) {
        holder.nome.text=clientes[position].nome
        holder.telefone.text=clientes[position].telefone

        holder.constraintLayout.setOnClickListener{
            val intent = Intent(holder.binding.root.context, ClienteActivity::class.java)
            intent.putExtra(ARG_PARAM_CLIENTE_PARCELABLE, clientes[position])
            holder.binding.root.context.startActivity(intent)
        }
    }

}