package com.augusto.doceskotlin

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class DoceRecyclerViewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var constraintLayout = itemView.findViewById<ConstraintLayout>(R.id.UmaLinhaDoceConstraintLayout)
    var imagem = itemView.findViewById<ImageView>(R.id.UmaLinhaDoceImageView)
    var nome = itemView.findViewById<TextView>(R.id.UmaLinhaDoceTextViewNomeDoce)
    var quantidade = itemView.findViewById<TextView>(R.id.UmaLinhaDoceTextViewValorDoce)
}