package com.augusto.doceskotlin.models

class Doce {

    var idDoce: String?

    var nomeDoce: String?

    var imagemDoce: String?

    var valorDoce: Double?

    var quantidadeDoce: Int = 0

    constructor(idDoce: String, nomeDoce: String, imagemDoce: String, valorDoce: Double) {
        this.idDoce = idDoce
        this.nomeDoce = nomeDoce
        this.imagemDoce = imagemDoce
        this.valorDoce = valorDoce

    }

    override fun toString(): String {
        return "Doce(idDoce=$idDoce, nomeDoce=$nomeDoce, imagemDoce=$imagemDoce, valorDoce=$valorDoce, quantidadeDoce=$quantidadeDoce)"
    }



}



