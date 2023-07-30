package com.augusto.doceskotlin.dialogos

import android.content.Context
import com.augusto.doceskotlin.adapters.DocesRecyclerViewAdapter
import com.augusto.doceskotlin.objetos.Doce

class DialogoDoceAlterarValor(doce: Doce, context: Context, adapter: DocesRecyclerViewAdapter, position: Int) : DialogoDoce(doce, context) {

   init{
       qtd!!.setText(doce.valorDoce.toString())
       qtd!!.hint = "Novo Valor..."
       botao!!.setOnClickListener{
           doce.valorDoce = qtd!!.text.toString().toDouble()
           adapter.notifyItemChanged(position)
           dialog.dismiss()
       }
       dialog.show()
   }
}