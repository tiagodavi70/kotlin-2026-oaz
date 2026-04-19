package pt.transporte.comboio

import kotlinx.serialization.Serializable

@Serializable
data class Utilizador(val nome:String, val nif:String)