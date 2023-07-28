package com.augusto.doceskotlin

import android.annotation.SuppressLint
import java.text.SimpleDateFormat

@SuppressLint("SimpleDateFormat")
val FORMATADOR_HORA = SimpleDateFormat("HH:mm")
@SuppressLint("SimpleDateFormat")
val FORMATADOR_DATA = SimpleDateFormat("dd/MM/yyyy")

const val EDITAR_DOCES = 1
const val VER_DOCES_SEMANA = 2

const val ARG_PARAM_ID_ENCOMENDA = "IDEncomenda"
const val ARG_PARAM_ID_CLIENTE = "IDCliente"
const val ARG_PARAM_ID_DOCE = "IDEncomenda"