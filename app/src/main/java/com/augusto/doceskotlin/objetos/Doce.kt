package com.augusto.doceskotlin.objetos

data class Doce(var idDoce: String, var nomeDoce: String, var imagemDoce: String,  var valorDoce: Double) {

    var quantidadeDoce=0

    override fun toString(): String {
        return "Doce: (idDoce=$idDoce, nomeDoce=$nomeDoce, imagemDoce=$imagemDoce, valorDoce=$valorDoce, quantidadeDoce=$quantidadeDoce)"
    }



}



