package pt.transporte.comboio.roteamento

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.http.httpDateFormat
import io.ktor.server.request.receiveParameters
import io.ktor.server.response.respond
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.patch
import io.ktor.server.routing.post
import io.ktor.server.routing.put
import io.ktor.server.routing.routing
import kotlinx.coroutines.async
import pt.transporte.comboio.DButils
import pt.transporte.comboio.linha.ParagemExposed
import pt.transporte.comboio.linha.ParagemServico
import pt.transporte.comboio.linha.RegiaoExposed
import pt.transporte.comboio.linha.RegiaoServico

fun Application.configureLinha() {

    val paragemServico = ParagemServico(DButils.database)
    val regiaoServico = RegiaoServico(DButils.database)

    routing {
        post("/paragem") {
            val params = call.receiveParameters()
            val nome: String? = params["nome"]
            val estacao: Boolean? = params["estacao"]?.toBooleanStrictOrNull()
            val regiaoId: Int? = params["regiao"]?.toIntOrNull()
            if (regiaoId != null) {
                val regiao: RegiaoExposed? = regiaoServico.ler(regiaoId)

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
            val regiaoId: Int? = params["regiao"]?.toIntOrNull()

            if (id != null && regiaoId != null) {
                val regiao: RegiaoExposed? = regiaoServico.ler(regiaoId)

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

        post("/regiao") {
            val params = call.receiveParameters()
            val nome: String? = params["nome"]
            val abreviatura: String? = params["abreviatura"]
            if (nome != null && abreviatura != null) {
                val regiao = RegiaoExposed(-1, nome, abreviatura)
                regiaoServico.criar(regiao)
                call.respond(HttpStatusCode.Created)
            } else {
                call.respond(HttpStatusCode.BadRequest)
            }
        }
        get("/regiao") {
            call.respond(regiaoServico.ler())
        }
        get("/regiao/{id}") {
            val id = call.parameters["id"]?.toIntOrNull()
            if (id != null) {
                val regiao = regiaoServico.ler(id)
                if (regiao != null) {
                    call.respond(regiao)
                } else {
                    call.respond(HttpStatusCode.BadRequest)
                }
            } else {
                call.respond(HttpStatusCode.BadRequest)
            }
        }
    }
}