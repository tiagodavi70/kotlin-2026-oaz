package pt.transporte.comboio.roteamento

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.request.receiveParameters
import io.ktor.server.response.respond
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.put
import io.ktor.server.routing.routing
import pt.transporte.comboio.utils.DButils
import pt.transporte.comboio.linha.ParagemExposed
import pt.transporte.comboio.linha.ParagemServico

fun Application.configureLinha() {

    val paragemServico = ParagemServico(DButils.database)

    routing {
        post("/paragem") {
            val params = call.receiveParameters()
            val nome: String? = params["nome"]
            val estacao: Boolean? = params["estacao"]?.toBooleanStrictOrNull()
            val regiao: String? = params["regiao"]
            if (regiao != null && regiao != null) {

                if (nome != null && estacao != null && regiao != null) {
                    val paragem = ParagemExposed(-1, nome, regiao, estacao)
                    paragemServico.criar(paragem)
                    call.respond(HttpStatusCode.Created)
                } else {
                    call.respond(HttpStatusCode.BadRequest)
                }
            } else {
                call.respond(HttpStatusCode.BadRequest)
            }
        }

        get("/paragem") {
            call.respond(paragemServico.ler())
        }

        get("/paragem/{id}") {
            val id = call.parameters["id"]?.toIntOrNull()
            if (id != null) {
                val paragem = paragemServico.ler(id)
                if (paragem != null) {
                    call.respond(paragem)
                } else {
                    call.respond(HttpStatusCode.BadRequest)
                }
            } else {
                call.respond(HttpStatusCode.BadRequest)
            }
        }

        put("/paragem/{id}") {
            val id = call.parameters["id"]?.toIntOrNull()
            val form = call.receiveParameters()
            val params = call.receiveParameters()
            val nome: String? = params["nome"]
            val estacao: Boolean? = params["estacao"]?.toBooleanStrictOrNull()
            val regiao: String? = params["regiao"]

            if (id != null && regiao != null) {

                if (nome != null && estacao != null && regiao != null) {
                    val paragem = ParagemExposed(id, nome, regiao, estacao)
                    paragemServico.atualizar(id, paragem)
                    call.respond(HttpStatusCode.Created)
                } else {
                    call.respond(HttpStatusCode.BadRequest)
                }
            } else {
                call.respond(HttpStatusCode.BadRequest)
            }
        }

        delete("/paragem/{id}") {
            val id = call.parameters["id"]?.toIntOrNull()
            if (id != null) {
                paragemServico.apagar(id)
                call.respond(HttpStatusCode.OK)
            } else {
                call.respond(HttpStatusCode.BadRequest)
            }
        }
    }
}