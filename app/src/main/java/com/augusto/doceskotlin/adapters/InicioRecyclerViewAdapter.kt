package com.augusto.doceskotlin.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.augusto.doceskotlin.ARG_PARAM_ENCOMENDA_PARCELABLE
import com.augusto.doceskotlin.FORMATADOR_DATA
import com.augusto.doceskotlin.FORMATADOR_HORA
import com.augusto.doceskotlin.viewHolder.EncomendaRecyclerViewViewHolder
import com.augusto.doceskotlin.R
import com.augusto.doceskotlin.activities.EncomendaActivity
import com.augusto.doceskotlin.objetos.Encomenda

class InicioRecyclerViewAdapter(val context: Context, var lista: MutableList<Encomenda>) : RecyclerView.Adapter<EncomendaRecyclerViewViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EncomendaRecyclerViewViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.uma_linha_encomenda, parent, false)
        return EncomendaRecyclerViewViewHolder(view)
    }

    override fun getItemCount(): Int = lista.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: EncomendaRecyclerViewViewHolder, position: Int) {
        holder.nomeCliente.text = lista[position].cliente!!.nome
        holder.data.text = FORMATADOR_DATA.format(lista[position].data!!)
        holder.hora.text = FORMATADOR_HORA.format(lista[position].data!!)
        holder.quantidadeDocesEncomenda.text = lista[position].quantidadeDocesEncomenda.toString() + " Doces"
        holder.valor.text = "R$: " + "%.2f".format(lista[position].valorEncomenda)

        if (lista[position].feita == true) {
            holder.checkFeita.visibility = View.VISIBLE
        } else {
            holder.checkFeita.visibility = View.GONE
        }

        if (lista[position].obs != null){
            holder.avisoObs.visibility = View.VISIBLE
        }else{
            holder.avisoObs.visibility = View.GONE
        }

        holder.constraintLayout.setOnClickListener {
            val irParaEncomendaActivity = Intent(context, EncomendaActivity::class.java)
            irParaEncomendaActivity.putExtra(ARG_PARAM_ENCOMENDA_PARCELABLE, lista[position])
            context.startActivity(irParaEncomendaActivity)
        }
    }

}