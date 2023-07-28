package com.augusto.doceskotlin

import org.junit.Test

import org.junit.Assert.*

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

}