package com.augusto.doceskotlin

import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.augusto.doceskotlin.objetos.Encomenda
import org.junit.Test
import org.junit.Assert.*
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
    fun testandoNulo(){
        var lista: MutableList<Int>? = null
        lista = ArrayList()
        lista?.add(2)
        println(lista)


    }

    @Test
    fun testandoSemana(){

        val selecionado = Calendar.getInstance()
        val inicio = Calendar.getInstance()
        val fim = Calendar.getInstance()

        inicio.set(
            selecionado.get(Calendar.YEAR),
            selecionado.get(Calendar.MONTH),
            selecionado.get(Calendar.DAY_OF_MONTH)-5,0,0,0)

        fim.set(
            selecionado.get(Calendar.YEAR),
            selecionado.get(Calendar.MONTH),
            selecionado.get(Calendar.DAY_OF_MONTH)+1, 23,59,59)

        println("Hoje: ${selecionado.time}")
        println("Inicio: ${inicio.time}")
        println("Fim: ${fim.time}")
    }

}