package pt.transporte.comboio.roteamento

import io.ktor.server.application.Application
import io.ktor.server.request.receiveParameters
import io.ktor.server.response.respond
import io.ktor.server.response.respondRedirect
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.routing
import io.ktor.server.thymeleaf.ThymeleafContent
import pt.transporte.comboio.comboio.LugarServico
import pt.transporte.comboio.linha.ParagemServico
import pt.transporte.comboio.pessoa.PessoaServico
import pt.transporte.comboio.utils.DButils
import pt.transporte.comboio.viagem.BilheteServico
import pt.transporte.comboio.viagem.ViagemServico

fun Application.configureBilhete() {

    val viagemServico = ViagemServico(DButils.database)
    val pessoaServico = PessoaServico(DButils.database)
    val lugarServico = LugarServico(DButils.database)
    val paragemServico = ParagemServico(DButils.database)
    val bilheteServico = BilheteServico(DButils.database)

    routing {
        get("/bilhete.html") {
            call.respond(ThymeleafContent("bilhete",
                mapOf("utilizadores" to pessoaServico.ler(), "viagens" to viagemServico.ler())))
        }

        post("/bilhete") {
            val params = call.receiveParameters()
            val idViagem = params["viagem"]!!.toInt()
            val idUtilizador = params["utilizador"]!!.toInt()
            val idLugar = params["lugar"]!!.toInt()
            val idParagemInicial = params["paragemInicial"]!!.toInt()
            val idParagemFinal = params["paragemFinal"]!!.toInt()

            val viagem = viagemServico.ler(idViagem)!!
            val utilizador = pessoaServico.ler(idUtilizador)!!
            val lugar = lugarServico.ler(idLugar)!!
            val paragemInicial = paragemServico.ler(idParagemInicial)!!
            val paragemFinal = paragemServico.ler(idParagemFinal)!!

            bilheteServico.criar(utilizador, viagem, lugar, paragemInicial, paragemFinal)
            call.respondRedirect("./")
        }

        get("/lugaresDesocupados") { // lugaresDesocupados?viagem=X
            val idViagem = call.queryParameters["viagem"]!!.toInt()
            val lugares = lugarServico.lerDesocupados(idViagem)
            println(lugarServico.ler() - lugares)
            call.respond(lugares)
        }
    }
}