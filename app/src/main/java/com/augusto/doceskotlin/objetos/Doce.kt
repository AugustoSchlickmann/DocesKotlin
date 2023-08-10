package com.augusto.doceskotlin.objetos

import android.os.Parcel
import android.os.Parcelable

data class Doce(var idDoce: Long?, var nomeDoce: String?, var imagemDoce: String?) : Parcelable {

    var quantidadeDoce = 0
    var valorDoce: Double? = null

    constructor(parcel: Parcel) : this(
        parcel.readValue(Long::class.java.classLoader) as? Long,
        parcel.readString(),
        parcel.readString()
    ) {
        quantidadeDoce = parcel.readInt()
        valorDoce = parcel.readValue(Double::class.java.classLoader) as? Double
    }

    constructor() : this(null, null, null)

    override fun toString(): String {
        return "Doce: (idDoce=$idDoce, nomeDoce=$nomeDoce, imagemDoce=$imagemDoce, valorDoce=$valorDoce, quantidadeDoce=$quantidadeDoce)"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(idDoce)
        parcel.writeString(nomeDoce)
        parcel.writeString(imagemDoce)
        parcel.writeInt(quantidadeDoce)
        parcel.writeValue(valorDoce)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Doce> {
        override fun createFromParcel(parcel: Parcel): Doce {
            return Doce(parcel)
        }

        override fun newArray(size: Int): Array<Doce?> {
            return arrayOfNulls(size)
        }
    }


}



