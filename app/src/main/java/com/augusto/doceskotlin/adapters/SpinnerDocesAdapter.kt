package com.augusto.doceskotlin.adapters

import android.annotation.SuppressLint
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
        return lista[position]
    }

    override fun getItemId(position: Int): Long {
        return lista[position].hashCode().toLong()
    }

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val rootView = LayoutInflater.from(context).inflate(R.layout.uma_linha_spinner_doce, parent, false)

        val imagem = rootView.findViewById<ImageView>(R.id.UmaLinhaSpinnerDoceImageView)
        val nome: TextView = rootView.findViewById(R.id.UmaLinhaSpinnerDoceTextViewNomeDoce)
        val valor = rootView.findViewById<TextView>(R.id.UmaLinhaSpinnerDoceTextViewValorDoce)

        imagem.setImageResource(lista[position].imagemDoce!!.toInt())
        nome.text = lista[position].nomeDoce
        valor.text = lista[position].valorDoce.toString()

        return rootView
    }

}