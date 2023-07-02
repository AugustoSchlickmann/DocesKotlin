package com.augusto.doceskotlin.models

import java.util.Date

class Encomenda(var cliente:Cliente?, var data:Long?, var doces:MutableList<Doce>, var obs:String?, var feita:Boolean?) {

    var id:String? = null

    override fun toString(): String {
        return "Encomenda(id=$id, cliente=$cliente, data=$data, doces=$doces, obs=$obs, feita=$feita)"
    }
}