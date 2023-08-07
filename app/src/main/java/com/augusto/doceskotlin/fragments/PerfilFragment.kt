package com.augusto.doceskotlin.fragments

import android.content.Intent
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
import com.augusto.doceskotlin.activities.EntrarActivity
import com.augusto.doceskotlin.databinding.FragmentPerfilBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.ktx.Firebase

class PerfilFragment : Fragment() {

    var bind: FragmentPerfilBinding? = null
    private var editTextNome: EditText? = null
    private var editTextEmail: EditText? = null
    private var cancelarEdicao: MenuItem? = null
    private var buttonAtualizar: Button? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        setHasOptionsMenu(true)
        bind = FragmentPerfilBinding.inflate(layoutInflater, container, false)
        editTextNome = bind!!.FragmentPerfilEditTextNome
        editTextEmail = bind!!.FragmentPerfilEditTextEmail
        buttonAtualizar = bind!!.FragmentPerfilBotaoAtualizar

        buttonAtualizar!!.setOnClickListener {
            buttonAtualizar!!.isEnabled = false
            atualizarPerfil()
        }

        if (Firebase.auth.currentUser != null) {
            editTextNome!!.setText(Firebase.auth.currentUser!!.displayName)
            editTextEmail!!.setText(Firebase.auth.currentUser!!.email)
        }
        container!!.removeAllViews()
        return bind!!.root
    }

    private fun atualizarPerfil() {
        if(editTextNome!!.text.isNotBlank()){
            val profileUpdates = userProfileChangeRequest {
                displayName = editTextNome!!.text.toString()
            }
            Firebase.auth.currentUser!!.updateProfile(profileUpdates).addOnCompleteListener {
                vendo()
                buttonAtualizar!!.isEnabled = true
            }
        }

    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        cancelarEdicao = menu.findItem(R.id.perfil_menu_CancelarEdicao)
        super.onPrepareOptionsMenu(menu)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.perfil_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.perfil_menu_Editar -> editando()
            R.id.perfil_menu_CancelarEdicao -> vendo()
            R.id.perfil_menu_Sair -> sair()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun editando() {
        cancelarEdicao?.isVisible = true
        editTextNome!!.isFocusableInTouchMode = true
        buttonAtualizar!!.visibility = View.VISIBLE
    }

    private fun vendo() {
        cancelarEdicao?.isVisible = false
        editTextNome!!.clearFocus()
        editTextNome!!.isFocusableInTouchMode = false
        buttonAtualizar!!.visibility = View.GONE
    }

    private fun sair() {
        Firebase.auth.signOut()
        val intent = Intent(activity, EntrarActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }
}