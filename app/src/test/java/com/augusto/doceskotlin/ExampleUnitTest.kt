package com.augusto.doceskotlin

import com.augusto.doceskotlin.objetos.Cliente
import com.augusto.doceskotlin.objetos.Doce
import com.augusto.doceskotlin.objetos.Encomenda
import com.augusto.doceskotlin.objetos.Usuario
import com.augusto.doceskotlin.singletons.UsuarioSingleton
import org.junit.Test

import org.junit.Assert.*
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
        var docinhoClone = docinho2.copy()


        var listaDocinhos: MutableList<Doce> = ArrayList()
        listaDocinhos.add(docinho)
        listaDocinhos.add(docinho2)
        if (listaDocinhos.contains(docinhoClone)){
            println("Lista já tem esse doce");
        }else{
            listaDocinhos.add(docinhoClone)
        }


        var hoje = Date()

        var encomenda : Encomenda
        encomenda = Encomenda( Cliente("nomeClinete"),System.currentTimeMillis() ,listaDocinhos,null,null)
        println(encomenda.toString())
        println()
    }

    @Test
    fun testandoDatas(){
        var dia = Date()
        val formatadorData = SimpleDateFormat("dd/MM/yyyy")
        val formatadorHora = SimpleDateFormat("HH:mm")

        // var calendario = Calendar.getInstance()
        // calendar = Calendar.getInstance(TimeZone.getTimeZone("America/Sao_Paulo"));

        println(dia)
        println(Calendar.getInstance().time)

        println(dia.time)
        println(formatadorData.format(System.currentTimeMillis()))
        println(formatadorHora.format(System.currentTimeMillis()))



    }

}