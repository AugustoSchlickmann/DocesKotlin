package com.augusto.doceskotlin.fragments.inicio

import android.os.Bundle
import com.augusto.doceskotlin.ARG_PARAM_DOCE_PARCELABLE
import com.augusto.doceskotlin.objetos.Doce
import com.augusto.doceskotlin.singletons.OperacoesFirebase

class InicioFragmentEncomendasDoce : InicioFragment() {

    private var doce: Doce? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            doce = it.getParcelable(ARG_PARAM_DOCE_PARCELABLE)
        }
    }

    override fun onResume() {
        super.onResume()
        if (doce != null) {
            OperacoesFirebase.pegarProximasEncomendasQueContemDoce(recyclerViewAdapter!!, this, doce!!)
        }

    }

    companion object {
        @JvmStatic
        fun encomendasQueContemDoce(param: Doce) =
            InicioFragmentEncomendasDoce().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_PARAM_DOCE_PARCELABLE, param)
                }
            }
    }
}