package com.augusto.doceskotlin.singletons

import android.annotation.SuppressLint
import com.augusto.doceskotlin.SpinnerDoces
import com.augusto.doceskotlin.adapters.RecyclerViewDocesAdapter
import com.augusto.doceskotlin.objetos.Doce
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

object ListaDeDoces {

    var doces: MutableList<Doce>? = null

    fun pegarDocesFirebaseSpinner(spinnerDoces: SpinnerDoces) {
        if(doces == null){
            doces = ArrayList()
            Firebase.firestore.collection("DocesKotlin").orderBy("idDoce").get().addOnCompleteListener { task ->
                for (document in task.result) {
                    val doce: Doce = document.toObject()
                    doces?.add(doce)
                }
                spinnerDoces.popularizarSpinner()
            }
        }else{
            spinnerDoces.popularizarSpinner()
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    fun pegarDocesFirebase(recyclerViewAdapter: RecyclerViewDocesAdapter) {
        if(doces == null){
            doces = ArrayList()
            Firebase.firestore.collection("DocesKotlin").orderBy("idDoce").get().addOnCompleteListener { task ->
                for (document in task.result) {
                    val doce: Doce = document.toObject()
                    doces?.add(doce)
                }
                recyclerViewAdapter.lista = doces
                recyclerViewAdapter.notifyDataSetChanged()
            }
        }else{
            recyclerViewAdapter.lista = doces
            recyclerViewAdapter.notifyDataSetChanged()
        }
    }


}