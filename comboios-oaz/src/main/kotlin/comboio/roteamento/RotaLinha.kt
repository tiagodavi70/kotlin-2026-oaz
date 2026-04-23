package pt.transporte.comboio.roteamento

import io.ktor.server.application.Application
import pt.transporte.comboio.DButils
import pt.transporte.comboio.linha.ParagemServico

fun Application.configureLinha() {

    val paragemServico = ParagemServico(DButils.database)
}