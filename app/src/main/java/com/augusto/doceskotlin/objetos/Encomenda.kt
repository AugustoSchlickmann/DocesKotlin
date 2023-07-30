package com.augusto.doceskotlin.objetos

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.Date

@Parcelize
data class Encomenda(
    var cliente: Cliente?,
    var data: Date?,
    var doces: MutableList<Doce>?,
    var obs: String?,
    var feita: Boolean?
) : Parcelable {

    private var id: String? = null
    private var valorEncomenda: Double = 0.0
    private var quantidadeDocesEncomenda: Int = 0

    constructor() : this(Cliente(), null, null, null, null)

    override fun toString(): String {
        return "Encomenda(id=$id, cliente=$cliente, data=$data, doces=$doces, obs=$obs, feita=$feita)"
    }

    fun setId(id: String) {
        this.id = id
    }

    fun getId(): String {
        return id.toString()
    }

    fun setValorEncomenda(valor: Double) {
        this.valorEncomenda = valor
    }

    fun getValorEncomenda(): Double {
        return this.valorEncomenda
    }

    fun setQuantidadeDocesEncomenda(quantidade: Int) {
        this.quantidadeDocesEncomenda = quantidade
    }

    fun getQuantidadeDocesEncomenda(): Int {
        return this.quantidadeDocesEncomenda
    }

}