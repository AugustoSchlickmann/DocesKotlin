package com.augusto.doceskotlin

import android.annotation.SuppressLint
import java.text.DecimalFormat
import java.text.SimpleDateFormat

@SuppressLint("SimpleDateFormat")
val FORMATADOR_HORA = SimpleDateFormat("HH:mm")

@SuppressLint("SimpleDateFormat")
val FORMATADOR_DATA = SimpleDateFormat("dd/MM/yyyy")

val FORMATARDOR_NUMERO = DecimalFormat("#.##")

//CONSTANTES PARA ESPECIFICAR O TIPO_DA_LISTA_DE_DOCES
const val EDITAR_DOCES = 1
const val VER_DOCES_A_FAZER = 2

//CONSTANTES PARA ESPECIFICAR O TIPO_DA_LISTA_DE_ENCOMENDAS
const val PROCURAR_PROXIMAS_ENCOMENDAS = 1
const val PROCURAR_ENCOMENDAS_DA_SEMANA = 2
const val PROCURAR_ENCOMENDAS_POR_DATA = 3
const val PROCURAR_ENCOMENDAS_POR_NOME_CLIENTE_DIGITADO = 4
const val PROCURAR_ENCOMENDAS_DO_CLIENTE = 5
const val PROCURAR_PROXIMAS_ENCOMENDAS_COM_DOCE_SELECIONADO = 6

const val ARG_PARAM_CLIENTE_PARCELABLE = "ClienteParcelable"
const val ARG_PARAM_ENCOMENDA_PARCELABLE = "EncomendaParcelable"

//CONSTANTES PARA CRIAR FRAGMENT CadastrarEncomenda COM PARAMETROS
const val CADASTRANDO_ENCOMENDA_BANDEJINHA = "Bandejinhas"

//CONSTANTES PARA ESPECIFICAR O FRAGMENT INICIAL DA MainActivity OnCreate()
const val MAIN_ACTIVITY_QUAL_FRAGMENTO_CRIAR = "QualFragmentoCriar"
const val FRAGMENT_CADASTRAR_ENCOMENDA_COM_CLIENTE = 1
