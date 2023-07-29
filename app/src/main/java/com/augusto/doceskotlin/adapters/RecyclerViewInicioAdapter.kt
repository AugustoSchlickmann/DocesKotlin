package com.augusto.doceskotlin.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.augusto.doceskotlin.ARG_PARAM_ID_ENCOMENDA
import com.augusto.doceskotlin.FORMATADOR_DATA
import com.augusto.doceskotlin.FORMATADOR_HORA
import com.augusto.doceskotlin.viewHolder.EncomendaRecyclerViewViewHolder
import com.augusto.doceskotlin.R
import com.augusto.doceskotlin.activities.EncomendaActivity
import com.augusto.doceskotlin.objetos.Encomenda

class RecyclerViewInicioAdapter(val context: Context) :
    RecyclerView.Adapter<EncomendaRecyclerViewViewHolder>() {

    var lista: MutableList<Encomenda>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EncomendaRecyclerViewViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.uma_linha_encomenda, parent, false)
        return EncomendaRecyclerViewViewHolder(view)
    }

    override fun getItemCount(): Int {
        return  lista!!.size
    }

    override fun onBindViewHolder(holder: EncomendaRecyclerViewViewHolder, position: Int) {
        holder.nomeCliente.text = lista!![position].cliente!!.nome
        holder.data.text = FORMATADOR_DATA.format(lista!![position].data!!)
        holder.hora.text = FORMATADOR_HORA.format(lista!![position].data!!)
        holder.quantidadeDocesEncomenda.text = lista!![position].getQuantidadeDocesEncomenda().toString() + " Doces"
        holder.valor.text = "R$: " + "%.2f".format(lista!![position].getValorEncomenda())

        if (lista!![position].feita == true) {
            holder.checkFeita.visibility = View.VISIBLE
        } else {
            holder.checkFeita.visibility = View.GONE
        }

        holder.constraintLayout.setOnClickListener {
            //ir para a tela da encomenda
            val intent = Intent(context, EncomendaActivity::class.java)
            intent.putExtra(ARG_PARAM_ID_ENCOMENDA, lista!![position].getId())
            context.startActivity(intent)
        }
    }

}