package pt.transporte.comboio.roteamento

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.*
import io.ktor.server.http.httpDateFormat
import io.ktor.server.request.receiveParameters
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.put
import io.ktor.server.routing.routing
import pt.transporte.comboio.Utilizador

fun Application.configureUtilizador() {

    val utilizadores = mutableListOf<Utilizador>(
        Utilizador("Tiago", "9999999"),
        Utilizador("Davi", "98989898"),
        Utilizador("Izabella", "878787878")
    )

    routing {
        get("/utilizador") {
            //val utilizador = Utilizador("Tiago", "999999999999")
//            call.respondText(utilizador.toString())
            call.respond(utilizadores)
        }

        get("/utilizador/{id}") {
            val parametros = call.parameters
            val id: Int? = parametros["id"]?.toIntOrNull()

            if (id != null && id in 0..<utilizadores.size) {
                call.respond(utilizadores[id])
            } else {
                //call.respond(HttpStatusCode.BadRequest)
                call.respond(HttpStatusCode.NotFound)
            }
        }
        // http://127.0.0.1:8080/utilizador-query?nome=Tiago&nif=121212121
        get("/utilizador-query") {
            var params = call.queryParameters
            val nome: String? = params["nome"]
            val nif: String? = params["nif"]

            if (nome != null && nif != null) {
                call.respond(Utilizador(nome, nif))
            } else {
                call.respond(HttpStatusCode.BadRequest)
            }
        }

        post("/utilizador") {
            val params = call.receiveParameters()
            val nome: String? = params["nome"]
            val nif: String? = params.get("nif")

            if (nome != null && nif != null) {
                val utilizador = Utilizador(nome, nif)
                utilizadores.add(utilizador)
                call.respond(HttpStatusCode.Created)
            }else {
                call.respond(HttpStatusCode.BadRequest)
            }
        }

        put("/utilizador/{id}") {
            val params = call.receiveParameters()
            val nome: String? = params["nome"]
            val nif: String? = params.get("nif")

            val id: Int? = call.parameters["id"]?.toIntOrNull()

            if (nome != null && nif != null &&
                id != null && id in 0..<utilizadores.size) {
                utilizadores[id] = Utilizador(nome, nif)
                call.respond(HttpStatusCode.OK)
            } else {
                call.respond(HttpStatusCode.BadRequest)
            }
        }

        delete("/utilizador/{id}") {
            val id: Int? = call.parameters["id"]?.toIntOrNull()

            if (id != null && id in 0..<utilizadores.size) {
                utilizadores.remove(utilizadores[id])
                call.respond(HttpStatusCode.OK)
            } else {
                call.respond(HttpStatusCode.BadRequest)
            }
        }
    }
}