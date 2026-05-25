package pt.transporte.comboio.utils

import kotlinx.coroutines.runBlocking
import kotlinx.datetime.LocalDateTime
import pt.transporte.comboio.comboio.ComboioExposed
import pt.transporte.comboio.comboio.ComboioServico
import pt.transporte.comboio.comboio.LugarExposed
import pt.transporte.comboio.comboio.LugarServico
import pt.transporte.comboio.linha.LinhaExposed
import pt.transporte.comboio.linha.LinhaServico
import pt.transporte.comboio.linha.ParagemExposed
import pt.transporte.comboio.linha.ParagemServico
import pt.transporte.comboio.pessoa.PessoaExposed
import pt.transporte.comboio.pessoa.PessoaServico
import pt.transporte.comboio.viagem.BilheteExposed
import pt.transporte.comboio.viagem.BilheteServico
import pt.transporte.comboio.viagem.ReservaExposed
import pt.transporte.comboio.viagem.ReservaServico
import pt.transporte.comboio.viagem.ViagemExposed
import pt.transporte.comboio.viagem.ViagemServico
import java.io.File


fun main() {

    File("data/comboios.mv.db").delete()

    val paragensAveiro = listOf(
        ParagemExposed(-1, "Porto São Bento", "Porto", true),
        ParagemExposed(-1, "Porto Campanhã", "Porto", true),
        ParagemExposed(-1, "General Torres", "Porto", true),
        ParagemExposed(-1, "Vila Nova de Gaia-Devesas", "Porto", true),
        ParagemExposed(-1, "Coimbrões", "Porto", false),
        ParagemExposed(-1, "Madalena", "Porto", false),
        ParagemExposed(-1, "Valadares", "Porto", false),
        ParagemExposed(-1, "Francelos", "Porto", false),
        ParagemExposed(-1, "Miramar", "Porto", false),
        ParagemExposed(-1, "Aguda", "Porto", false),
        ParagemExposed(-1, "Granja", "Porto", false),

        ParagemExposed(-1, "Espinho", "Aveiro", true),
        ParagemExposed(-1, "Silvalde", "Aveiro", false),
        ParagemExposed(-1, "Paramos", "Aveiro", false),
        ParagemExposed(-1, "Esmoriz", "Aveiro", true),
        ParagemExposed(-1, "Cortegaça", "Aveiro", false),
        ParagemExposed(-1, "Carvalheira-Maceda", "Aveiro", false),
        ParagemExposed(-1, "Ovar", "Aveiro", true),
        ParagemExposed(-1, "Válega", "Aveiro", false),
        ParagemExposed(-1, "Avanca", "Aveiro", false),
        ParagemExposed(-1, "Estarreja", "Aveiro", true),
        ParagemExposed(-1, "Salreu", "Aveiro", false),
        ParagemExposed(-1, "Canelas", "Aveiro", false),
        ParagemExposed(-1, "Cacia", "Aveiro", false),
        ParagemExposed(-1, "Aveiro", "Aveiro", true)
    )
    val utilizadores = listOf(
        PessoaExposed(-1, "Tiago Araújo", "123456789"),
        PessoaExposed(-1, "Maria Ferreira", "234567891"),
        PessoaExposed(-1, "Pedro Costa", "345678912"),
        PessoaExposed(-1, "Ana Martins", "456789123"),
        PessoaExposed(-1, "Ricardo Sousa", "567891234")
    )

    val linhaAveiro = LinhaExposed(-1, "Linha de Aveiro", paragensAveiro)

    val comboio = ComboioExposed(-1, "CP 2240")
    val lugares = (1..120).map { numero ->
        val assento = when {
            numero <= 40 -> "A$numero"
            numero <= 80 -> "B${numero - 40}"
            else -> "C${numero - 80}"
        }
        LugarExposed(-1, assento, comboio)
    }

    val paragemServico = ParagemServico(DButils.database)
    val pessoaServico = PessoaServico(DButils.database)
    val linhaServico = LinhaServico(DButils.database)
    val lugarServico = LugarServico(DButils.database)
    val comboioServico = ComboioServico(DButils.database)
    val viagemServico = ViagemServico(DButils.database)

    val reservaServico = ReservaServico(DButils.database)
    val bilheteServico = BilheteServico(DButils.database)

    runBlocking {
        utilizadores.forEach { pessoaServico.criar(it) }
        paragensAveiro.forEach { paragemServico.criar(it) }
        linhaServico.criar(linhaAveiro)

        val comboioId = comboioServico.criar(comboio)
        val novosLugares = lugares.map { LugarExposed(it.id, it.assento, comboioServico.lerSemLugar(comboioId)!!) }
        novosLugares.forEach { lugarServico.criar(it) }

        val linha = linhaServico.ler(1)!!
        val comboio = comboioServico.ler(comboioId)!!
        val dataHora = LocalDateTime(2026,5,24,19,11,0,0)
        val viagens = listOf(ViagemExposed(-1, linha, comboio, 'I', dataHora))

        viagens.forEach { viagemServico.criar(it) }

        val viagem = viagemServico.ler(1)!!

        val reservas = lugarServico.ler().shuffled().take(5).map {
            ReservaExposed(viagem, it)
        }

        reservas.forEach { reservaServico.criar(it) }

        println(reservaServico.ler())
        val utilizadoresDB = pessoaServico.ler()
        val reservasDB = reservaServico.ler()
        val paragensDB = paragemServico.ler()

        val bilhetes = listOf(
            BilheteExposed(
                utilizadoresDB[0], reservasDB[0],
                paragensDB.first { it.nome == "Porto São Bento" },
                paragensDB.first { it.nome == "Aveiro" }
            ),

            BilheteExposed(
                utilizadoresDB[1], reservasDB[1],
                paragensDB.first { it.nome == "Porto Campanhã" },
                paragensDB.first { it.nome == "Ovar" }
            ),

            BilheteExposed(
                utilizadoresDB[2], reservasDB[2],
                paragensDB.first { it.nome == "Espinho" },
                paragensDB.first { it.nome == "Aveiro" }
            ),

            BilheteExposed(
                utilizadoresDB[3], reservasDB[3],
                paragensDB.first { it.nome == "General Torres" },
                paragensDB.first { it.nome == "Estarreja" }
            ),

            BilheteExposed(
                utilizadoresDB[4], reservasDB[4],
                paragensDB.first { it.nome == "Esmoriz" },
                paragensDB.first { it.nome == "Aveiro" }
            )
        )

        bilhetes.forEach { bilheteServico.criar(it) }
        println(bilhetes)
    }
}