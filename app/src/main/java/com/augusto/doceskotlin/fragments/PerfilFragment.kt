package com.augusto.doceskotlin.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.augusto.doceskotlin.R
import com.augusto.doceskotlin.databinding.FragmentPerfilBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.ktx.Firebase

class PerfilFragment : Fragment() {

    var bind : FragmentPerfilBinding? = null
    var editTextNome : EditText? = null
    var editTextEmail : EditText? = null
    var cancelarEdicao : MenuItem? = null
    var buttonAtualizar : Button? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        bind = FragmentPerfilBinding.inflate(layoutInflater, container, false)
        editTextNome = bind!!.FragmentPerfilEditTextNome
        editTextEmail = bind!!.FragmentPerfilEditTextEmail
        buttonAtualizar = bind!!.FragmentPerfilBotaoAtualizar

        buttonAtualizar!!.setOnClickListener{
            atualizarPerfil()
        }

        if(Firebase.auth.currentUser != null){
            editTextNome!!.setText(Firebase.auth.currentUser!!.displayName)
            editTextEmail!!.setText(Firebase.auth.currentUser!!.email)
        }
        container!!.removeAllViews()
        return bind!!.root
    }

    private fun atualizarPerfil() {
        val profileUpdates = userProfileChangeRequest {
            displayName = editTextNome!!.text.toString()
        }
        Firebase.auth.currentUser!!.updateProfile(profileUpdates).addOnCompleteListener{
            vendo()
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        cancelarEdicao = menu.findItem(R.id.perfil_menu_CancelarEdicao)
        super.onPrepareOptionsMenu(menu)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.perfil_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.perfil_menu_Editar -> editando()
            R.id.perfil_menu_CancelarEdicao -> vendo()
            R.id.perfil_menu_Sair -> sair()
            R.id.perfil_menu_ExcluirConta -> excluirConta()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun editando() {
        editTextNome!!.isFocusableInTouchMode = true
        buttonAtualizar!!.visibility= View.VISIBLE
    }

    private fun vendo() {
        editTextNome!!.isFocusableInTouchMode = false
        buttonAtualizar!!.visibility= View.GONE
    }

    private fun sair() {

    }

    private fun excluirConta() {

    }
}