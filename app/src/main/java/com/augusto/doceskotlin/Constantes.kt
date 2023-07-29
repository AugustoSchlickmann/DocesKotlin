package com.augusto.doceskotlin

import android.annotation.SuppressLint
import java.text.SimpleDateFormat

@SuppressLint("SimpleDateFormat")
val FORMATADOR_HORA = SimpleDateFormat("HH:mm")

@SuppressLint("SimpleDateFormat")
val FORMATADOR_DATA = SimpleDateFormat("dd/MM/yyyy")

//CONSTANTES PARA ESPECIFICAR O TIPO_DA_LISTA_DE_DOCES
const val EDITAR_DOCES = 1
const val VER_DOCES_A_FAZER = 2

//CONSTANTES PARA ESPECIFICAR O TIPO_DA_LISTA_DE_ENCOMENDAS
const val PROCURAR_PROXIMAS_ENCOMENDAS = 1
const val PROCURAR_ENCOMENDAS_DA_SEMANA = 2
const val PROCURAR_PROXIMAS_ENCOMENDAS_COM_DOCE_SELECIONADO = 3
const val PROCURAR_ENCOMENDAS_POR_DATA = 4
const val PROCURAR_ENCOMENDAS_POR_NOME_CLIENTE = 5
const val PROCURAR_ENCOMENDAS_POR_ID_CLIENTE = 6

const val ARG_PARAM_ID_ENCOMENDA = "IDEncomenda"
const val ARG_PARAM_ID_CLIENTE = "IDCliente"
const val ARG_PARAM_ID_DOCE = "IDEncomenda"