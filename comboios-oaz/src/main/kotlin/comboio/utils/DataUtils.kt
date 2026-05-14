package pt.transporte.comboio.utils

import kotlinx.coroutines.runBlocking
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


fun main() {
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
        PessoaExposed("Tiago Araújo", "123456789"),
        PessoaExposed("Maria Ferreira", "234567891"),
        PessoaExposed("Pedro Costa", "345678912"),
        PessoaExposed("Ana Martins", "456789123"),
        PessoaExposed("Ricardo Sousa", "567891234")
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

    runBlocking {
        utilizadores.forEach { pessoaServico.criar(it) }
        paragensAveiro.forEach { paragemServico.criar(it) }
        linhaServico.criar(linhaAveiro)

        val comboio = comboioServico.criar(comboio)
        val novosLugares = lugares.map { LugarExposed(it.id, it.assento, comboioServico.lerSemLugar(comboio)!!) }
        novosLugares.forEach { lugarServico.criar(it) }
    }
}