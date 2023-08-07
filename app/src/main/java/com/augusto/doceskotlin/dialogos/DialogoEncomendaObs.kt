package com.augusto.doceskotlin.dialogos

import android.app.Dialog
import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.augusto.doceskotlin.R
import com.augusto.doceskotlin.objetos.Encomenda

class DialogoEncomendaObs(context: Context, encomenda: Encomenda, imageView: ImageView) {

    init {
        val dialog = Dialog(context)
        dialog.setContentView(R.layout.dialogo_encomenda_obs)
        dialog.window!!.setBackgroundDrawableResource(R.drawable.edit_text_rounded)

        val editText: EditText = dialog.findViewById(R.id.DialogoEncomendaObsEditTextTextMultiLine)
        val buttonOk: Button = dialog.findViewById(R.id.DialogoEncomendaObsButtonOk)

        editText.setText(encomenda.obs)

        buttonOk.setOnClickListener {
            if (editText.text.toString() != encomenda.obs) {
                if (editText.text.isEmpty()) {
                    encomenda.obs = null
                } else {
                    encomenda.obs = editText.text.toString()
                }
                Toast.makeText(context,"Observações salvas",Toast.LENGTH_SHORT).show()
            }
            dialog.dismiss()
        }
        dialog.show()
    }

}