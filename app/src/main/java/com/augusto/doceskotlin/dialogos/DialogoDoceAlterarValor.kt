package com.augusto.doceskotlin.dialogos

import android.content.Context
import android.text.InputType
import com.augusto.doceskotlin.adapters.RecyclerViewDocesAdapter
import com.augusto.doceskotlin.objetos.Doce

class DialogoDoceAlterarValor(doce: Doce, context: Context, adapter: RecyclerViewDocesAdapter, position: Int) : DialogoDoce(doce, context) {

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