package com.augusto.doceskotlin.objetos

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Cliente(var nome: String?, var telefone: String?) : Parcelable {

    constructor() : this(null, null)

    override fun toString(): String {
        return "Cliente(nome=$nome, telefone=$telefone)"
    }

}