package com.augusto.doceskotlin.objetos

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Cliente(var nome: String?, var telefone: String?) : Parcelable {

    private var imagem: String? = null
    constructor() : this(null, null)

    override fun toString(): String {
        return "Cliente(nome=$nome, telefone=$telefone, imagem=$imagem)"
    }

}