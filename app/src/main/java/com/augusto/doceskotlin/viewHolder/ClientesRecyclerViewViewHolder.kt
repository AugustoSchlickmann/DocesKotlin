package com.augusto.doceskotlin.viewHolder

import androidx.recyclerview.widget.RecyclerView
import com.augusto.doceskotlin.databinding.FragmentListaClientesBinding
import com.augusto.doceskotlin.databinding.UmaLinhaClienteBinding

class ClientesRecyclerViewViewHolder(var binding: UmaLinhaClienteBinding) : RecyclerView.ViewHolder(binding.root) {

    var constraintLayout = binding.UmaLinhaClienteConstraintLayout
    var imagem = binding.imageViewUmaLinhaCliente
    var nome = binding.UmaLinhaClienteTextViewNome
    var telefone = binding.UmaLinhaClienteTextViewTelefone

}