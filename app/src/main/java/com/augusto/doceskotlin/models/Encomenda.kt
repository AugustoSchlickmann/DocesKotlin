package com.augusto.doceskotlin.models

import java.util.Date

class Encomenda(var id:String?, var cliente:Cliente?, var data:Date?, var doces:MutableList<Doce>, var obs:String?, var feita:Boolean?) {

    override fun toString(): String {
        return "Encomenda(id=$id, cliente=$cliente, data=$data, doces=$doces, obs=$obs, feita=$feita)"
    }
}