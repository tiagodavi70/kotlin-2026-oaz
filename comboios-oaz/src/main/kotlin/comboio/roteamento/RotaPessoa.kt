package pt.transporte.comboio.roteamento

import io.ktor.server.application.Application
import io.ktor.server.request.receiveParameters
import io.ktor.server.response.respond
import io.ktor.server.response.respondRedirect
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.routing
import io.ktor.server.thymeleaf.ThymeleafContent
import pt.transporte.comboio.utils.DButils
import pt.transporte.comboio.pessoa.PessoaExposed
import pt.transporte.comboio.pessoa.PessoaServico
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime


fun Application.configurePessoa() {

    val utilizador1 = PessoaExposed("Tiago", "999999")
    val utilizador2 = PessoaExposed("Izabella", "888888")
    //val utilizadores = mutableListOf<UtilizadorExposed>(utilizador1, utilizador2)

    val pessoaServico = PessoaServico(DButils.database)

    routing {
        get("/utilizador.html") {
            call.respond(ThymeleafContent("utilizador",
                mapOf("utilizador" to utilizador1, "utilizador2" to utilizador2, "variavelNumerica" to 3)))
        }

        get("utilizadores.html") {
            val utilizadores = pessoaServico.ler()
            call.respond(ThymeleafContent("utilizadores", mapOf("lista" to utilizadores)))
        }

        post("/utilizador") {
            val params = call.receiveParameters()
            val nome:String? = params["nome"]
            val nif:String? = params["nif"]
            if (nome != null && nif != null && nome != "" && nif != "") {
                val novo_utilizador = PessoaExposed(nome, nif)
                pessoaServico.criar(novo_utilizador)
                call.respondRedirect("/index.html")
            } else {
                call.respondRedirect("/cadastro.html")
            }
        }

        post("/data") {
            val params = call.receiveParameters()
            val data = params["data"]
            val hora = params["hora"]
            val dataHora = params["dataHora"]

            println("$data $hora $dataHora")

            if (data != null && hora != null && dataHora != null) {
                val dataObj: LocalDate = LocalDate.parse(data)
                val horaObj: LocalTime = LocalTime.parse(hora)
                val dataHoraObj: LocalDateTime = LocalDateTime.parse(dataHora)

                println("$dataObj $horaObj $dataHoraObj")
            }

            call.respondRedirect("data.html")
        }
    }

}