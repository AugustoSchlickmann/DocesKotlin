package com.augusto.doceskotlin.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.augusto.doceskotlin.R
import com.augusto.doceskotlin.objetos.Doce

class SpinnerDocesAdapter(val context: Context, val lista: MutableList<Doce>) : BaseAdapter() {

    override fun getCount(): Int {
        return lista.size -1
    }

    override fun getItem(position: Int): Any {
        return lista.get(position)
    }

    override fun getItemId(position: Int): Long {
        return lista[position].hashCode().toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var rootView =
            LayoutInflater.from(context).inflate(R.layout.uma_linha_spinner_doce, parent, false)

        //var constraintLayout = rootView.findViewById<ConstraintLayout>(R.id.UmaLinhaSpinnerDoceConstraintLayout)
        var imagem = rootView.findViewById<ImageView>(R.id.UmaLinhaSpinnerDoceImageView)
        var nome: TextView = rootView.findViewById(R.id.UmaLinhaSpinnerDoceTextViewNomeDoce)
        var valor = rootView.findViewById<TextView>(R.id.UmaLinhaSpinnerDoceTextViewValorDoce)

        imagem.setImageResource(lista[position].imagemDoce!!.toInt())
        nome.text = lista[position].nomeDoce
        valor.text = lista[position].valorDoce.toString()

        //constraintLayout.setOnClickListener { println("Doce Selecionado "+ lista[position].toString()) }

        return rootView
    }

}