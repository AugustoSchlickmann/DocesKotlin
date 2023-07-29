package com.augusto.doceskotlin.viewHolder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.augusto.doceskotlin.R

class EncomendaRecyclerViewViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

    var constraintLayout = itemView.findViewById<ConstraintLayout>(R.id.UmaLinhaEncomendaConstraintLayout)
    var nomeCliente = itemView.findViewById<TextView>(R.id.UmaLinhaEncomendaTextViewNomeCliente)
    var data = itemView.findViewById<TextView>(R.id.UmaLinhaEncomendaTextViewData)
    var hora = itemView.findViewById<TextView>(R.id.UmaLinhaEncomendaTextViewHora)
    var quantidadeDocesEncomenda = itemView.findViewById<TextView>(R.id.UmaLinhaEncomendaTextViewQtdDoces)
    var valor = itemView.findViewById<TextView>(R.id.UmaLinhaEncomendaTextViewValor)
    var checkFeita = itemView.findViewById<ImageView>(R.id.UmaLinhaEncomendaImageViewCheckFeita)
    var avisoObs = itemView.findViewById<ImageView>(R.id.UmaLinhaEncomendaImageViewAvisoObs)

}