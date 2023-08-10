package com.augusto.doceskotlin.fragments.inicio

import com.augusto.doceskotlin.singletons.OperacoesFirebase

class InicioFragmentEncomendasProximas : InicioFragment() {

    override fun onResume() {
        super.onResume()
        OperacoesFirebase.pegarProximasEncomendas(recyclerViewAdapter!!, this)

    }
}
