package com.augusto.doceskotlin.dialogos

import android.content.Context
import com.augusto.doceskotlin.adapters.doces.DocesRecyclerViewAdapter
import com.augusto.doceskotlin.objetos.Doce
import com.augusto.doceskotlin.singletons.OperacoesFirebase

class DialogoDoceAlterarValor(doce: Doce, context: Context, adapter: DocesRecyclerViewAdapter, position: Int) : DialogoDoce(doce, context) {

   init{
       qtd!!.setText(doce.valorDoce.toString())
       qtd!!.hint = "Novo Valor..."
       botao!!.setOnClickListener{
           doce.valorDoce = qtd!!.text.toString().toDouble()
           adapter.notifyItemChanged(position)
           OperacoesFirebase.atualizarDoce(doce, context)
           dialog.dismiss()
       }
       dialog.show()
   }
}