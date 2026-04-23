package pt.transporte

import io.ktor.server.application.*
import pt.transporte.comboio.roteamento.configureLinha
import pt.transporte.comboio.roteamento.configureUtilizador

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureHTTP()
    configureTemplating()
    configureSerialization()
    configureDatabases()
    configureRouting()

    configureUtilizador()
    configureLinha()
}
