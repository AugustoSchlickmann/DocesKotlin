package com.augusto.doceskotlin.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.augusto.doceskotlin.viewHolder.EncomendaRecyclerViewViewHolder
import com.augusto.doceskotlin.R
import com.augusto.doceskotlin.activities.EncomendaActivity
import com.augusto.doceskotlin.objetos.Encomenda
import java.text.SimpleDateFormat

class RecyclerViewInicioAdapter(val context: Context, val lista: MutableList<Encomenda>) :
    RecyclerView.Adapter<EncomendaRecyclerViewViewHolder>() {
    val formatadorData = SimpleDateFormat("dd/MM/yyyy")
    val formatadorHora = SimpleDateFormat("HH:mm")
    var somaValorTotal = 0.0

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EncomendaRecyclerViewViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.uma_linha_encomenda, parent, false)
        return EncomendaRecyclerViewViewHolder(view)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(holder: EncomendaRecyclerViewViewHolder, position: Int) {

        var qtdDocesTotal = 0
        var valorEncomenda = 0.0

        for (doces in lista[position].doces) {
            qtdDocesTotal += doces.quantidadeDoce
            valorEncomenda += doces.quantidadeDoce * doces.valorDoce
        }
        somaValorTotal += valorEncomenda

        holder.nomeCliente.text = lista[position].cliente!!.nome
        holder.data.text = formatadorData.format(lista[position].data)
        holder.hora.text = formatadorHora.format(lista[position].data)
        holder.qtdDoces.text = qtdDocesTotal.toString()
        holder.valor.text = "R$: " + "%.2f".format(valorEncomenda)

        if (lista[position].feita == true) {
            holder.checkFeita.visibility = View.VISIBLE
        } else {
            holder.checkFeita.visibility = View.GONE
        }

        holder.constraintLayout.setOnClickListener {
            //ir para a tela da encomenda
            var intent = Intent(context, EncomendaActivity::class.java)
            intent.putExtra("posicao", position.toString())
            context.startActivity(intent)

        }


    }
}