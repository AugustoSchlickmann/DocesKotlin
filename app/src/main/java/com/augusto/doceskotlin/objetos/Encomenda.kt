package com.augusto.doceskotlin.objetos

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.Date

@Parcelize
data class Encomenda(
    var id: String?,
    var cliente: Cliente?,
    var data: Date?,
    var doces: MutableList<Doce>?,
    var obs: String?,
    var feita: Boolean?,
    var valorEncomenda: Double = 0.0,
    var quantidadeDocesEncomenda: Int = 0
) : Parcelable {

    constructor() : this(null, Cliente(), null, null, null, false, 0.0, 0)

    override fun toString(): String {
        return "Encomenda(id=$id, cliente=$cliente, data=$data, doces=$doces, obs=$obs, feita=$feita)"
    }


}