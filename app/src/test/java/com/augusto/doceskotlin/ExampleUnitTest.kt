package com.augusto.doceskotlin

import com.augusto.doceskotlin.models.Cliente
import com.augusto.doceskotlin.models.Doce
import com.augusto.doceskotlin.models.Encomenda
import com.augusto.doceskotlin.models.Usuario
import org.junit.Test

import org.junit.Assert.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

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
    fun taon() {
        UsuarioSingleton.usuarioAtualLogado = Usuario("1", "beabá", "beabá@gmail.com")
        assertNotNull(UsuarioSingleton.usuarioAtualLogado)
    }

    @Test
    fun criarUsuario() {
        assertNull(UsuarioSingleton.usuarioAtualLogado)
        UsuarioSingleton.usuarioAtualLogado = Usuario("1", "beabá", "beabá@gmail.com")
        assertNotNull(UsuarioSingleton.usuarioAtualLogado)
        println(UsuarioSingleton.usuarioAtualLogado.toString())
    }

    @Test
    fun testandoNulos() {
        println(UsuarioSingleton.usuarioAtualLogado?.nome)
        assertEquals(UsuarioSingleton.usuarioAtualLogado?.nome, null)
    }

    @Test
    fun testandoCriacoes() {
        var docinho = Doce("id1", "ninho","imagem1",1.25,)
        var docinho2 = Doce("id2", "Beijinho","imagem2",1.10,)
        docinho.quantidadeDoce=99
        println(docinho.toString());

        var listaDocinhos: MutableList<Doce> = ArrayList()
        listaDocinhos.add(docinho)
        listaDocinhos.add(docinho2)

        var hoje = Date()

        var encomenda : Encomenda
        encomenda = Encomenda("idEncomenda", Cliente("idCliente","nomeClinete"),hoje ,listaDocinhos,null,null)
        println(encomenda.toString())
    }

    @Test
    fun testandoDatas(){
        var dia = Date()
        var format = SimpleDateFormat("dd/MM/yyyy")
        // var calendario = Calendar.getInstance()
        // calendar = Calendar.getInstance(TimeZone.getTimeZone("America/Sao_Paulo"));

        println(dia.toString())
        println(Calendar.getInstance().time)

        println(dia.time)
        println(System.currentTimeMillis())
        println(Calendar.getInstance().timeInMillis)

        println("Formatado: "+ format.format(Calendar.getInstance().timeInMillis))

    }

    @Test
    fun printNumeros(){
        var a = 2.50
        var b = 1.2
        println("%.2f".format(a))



    }
}