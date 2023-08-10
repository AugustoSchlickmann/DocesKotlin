package com.augusto.doceskotlin.adapters.doces

import android.content.Context
import com.augusto.doceskotlin.R
import com.augusto.doceskotlin.activities.MainActivity
import com.augusto.doceskotlin.fragments.inicio.InicioFragmentEncomendasDoce
import com.augusto.doceskotlin.viewHolder.DoceRecyclerViewViewHolder

class DocesAFazerRecyclerViewAdapter(context: Context?) : DocesRecyclerViewAdapter() {

    init {
        this.context = context
    }

    override fun onBindViewHolder(holder: DoceRecyclerViewViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)

        holder.quantidade.text = lista!![position].quantidadeDoce.toString()
        holder.constraintLayout.setOnClickListener {
            val fragment = InicioFragmentEncomendasDoce.encomendasQueContemDoce(lista!![position])
            (context as MainActivity).supportFragmentManager.beginTransaction().replace(R.id.frameLayout, fragment).commitNow()
        }

    }

}