package com.augusto.doceskotlin

import android.content.res.Resources
import com.augusto.doceskotlin.objetos.Doce
import com.augusto.doceskotlin.singletons.ListaDeDoces
import org.junit.Assert.*
import org.junit.Test
import java.util.Calendar

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun stringVazia() {
        assertEquals(true, "     ".isBlank())
    }

    @Test
    fun stringPequena() {
        assertEquals(true, " a  b".trim().length > 3)

    }

    @Test
    fun bitShift() {
        var numero: Int = 5 shl 2
        println(numero)
        assertEquals(20, numero)
    }

    @Test
    fun testandoNulo() {
        var lista: MutableList<Int>? = null
        lista = ArrayList()
        lista?.add(2)
        println(lista)


    }

    @Test
    fun testandoSemana() {

        val selecionado = Calendar.getInstance()
        val inicio = Calendar.getInstance()
        val fim = Calendar.getInstance()

        inicio.set(
            selecionado.get(Calendar.YEAR),
            selecionado.get(Calendar.MONTH),
            selecionado.get(Calendar.DAY_OF_MONTH) - 5, 0, 0, 0
        )

        fim.set(
            selecionado.get(Calendar.YEAR),
            selecionado.get(Calendar.MONTH),
            selecionado.get(Calendar.DAY_OF_MONTH) + 1, 23, 59, 59
        )

        println("Hoje: ${selecionado.time}")
        println("Inicio: ${inicio.time}")
        println("Fim: ${fim.time}")
    }

    @Test
    fun criandoDoces() {
        ListaDeDoces.doces = ArrayList()
        ListaDeDoces.doces!!.add(Doce(1, "Beijinho", R.drawable.beijinho.toString(), 1.30))
        ListaDeDoces.doces!!.add(Doce(2, "Brigadeiro", R.drawable.brigadeiro.toString(), 1.30))
        ListaDeDoces.doces!!.add(Doce(3, "Brigadeiro Branco", R.drawable.brigadeiro_branco.toString(), 1.30))
        ListaDeDoces.doces!!.add(Doce(4, "Caju", R.drawable.caju.toString(), 1.30))
        ListaDeDoces.doces!!.add(Doce(5, "Casadinho", R.drawable.casadinho.toString(), 1.30))
        ListaDeDoces.doces!!.add(Doce(6, "Churros", R.drawable.churros.toString(), 1.30))
        ListaDeDoces.doces!!.add(Doce(7, "Morango", R.drawable.morango.toString(), 1.30))
        ListaDeDoces.doces!!.add(Doce(8, "Rosado", R.drawable.rosado.toString(), 1.30))
        ListaDeDoces.doces!!.add(Doce(9, "Ninho", R.drawable.ninho.toString(), 1.70))
        ListaDeDoces.doces!!.add(Doce(10, "Nozes", R.drawable.nozes.toString(), 1.70))
        ListaDeDoces.doces!!.add(Doce(11, "Olho de Sogra", R.drawable.olho_sogra.toString(), 1.70))
        ListaDeDoces.doces!!.add(Doce(12, "Olho de Sogro", R.drawable.olho_sogro.toString(), 1.70))
        ListaDeDoces.doces!!.add(Doce(13, "Bandejinha", R.drawable.bandejinha.toString(), 13.00))
        ListaDeDoces.doces!!.add(Doce(99, "Selecione", R.drawable.logo.toString(), 0.0))

//        for (doce in ListaDeDoces.doces!!) {
//            Firebase.firestore.collection("DocesKotlin").document(doce.nomeDoce!!).set(doce)
//        }

    }
    @Test
    fun strings() {
        println("abcdef".trimStart().replaceFirstChar { it.uppercase() })
        println("ab cd ef".trimStart().replaceFirstChar { it.uppercase() })
        println("  ab cd ef  ".trimStart().replaceFirstChar { it.uppercase() })
        println("  ab cd ef  g".trimStart().replaceFirstChar { it.uppercase() })
        println("  a b c d e f  g".replaceFirstChar { it.uppercase() })
    }
}