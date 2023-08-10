package com.augusto.doceskotlin

import android.annotation.SuppressLint
import java.text.DecimalFormat
import java.text.SimpleDateFormat

@SuppressLint("SimpleDateFormat")
val FORMATADOR_HORA = SimpleDateFormat("HH:mm")

@SuppressLint("SimpleDateFormat")
val FORMATADOR_DATA = SimpleDateFormat("dd/MM/yyyy")

val FORMATARDOR_NUMERO = DecimalFormat("#.##")

const val ARG_PARAM_CLIENTE_PARCELABLE = "ClienteParcelable"
const val ARG_PARAM_DOCE_PARCELABLE = "DoceParcelable"
const val ARG_PARAM_ENCOMENDA_PARCELABLE = "EncomendaParcelable"

//CONSTANTES PARA CRIAR FRAGMENT CadastrarEncomenda COM PARAMETROS
const val CADASTRANDO_ENCOMENDA_BANDEJINHA = "Bandejinhas"

//CONSTANTES PARA ESPECIFICAR O FRAGMENT INICIAL DA MainActivity OnCreate()
const val MAIN_ACTIVITY_QUAL_FRAGMENTO_CRIAR = "QualFragmentoCriar"
const val FRAGMENT_CADASTRAR_ENCOMENDA_COM_CLIENTE = 1
const val PROCURAR_ENCOMENDAS_DO_CLIENTE = 5