package pt.transporte.comboio.viagem

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.JoinType
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.Table.Dual.foreignKey
import org.jetbrains.exposed.sql.alias
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import pt.transporte.comboio.comboio.ComboioExposed
import pt.transporte.comboio.comboio.ComboioServico.Comboio
import pt.transporte.comboio.comboio.LugarExposed
import pt.transporte.comboio.comboio.LugarServico.Lugar
import pt.transporte.comboio.linha.LinhaExposed
import pt.transporte.comboio.linha.LinhaServico.Linha
import pt.transporte.comboio.linha.ParagemExposed
import pt.transporte.comboio.linha.ParagemServico.Paragem
import pt.transporte.comboio.pessoa.PessoaExposed
import pt.transporte.comboio.pessoa.PessoaServico.Pessoa
import pt.transporte.comboio.utils.DButils
import pt.transporte.comboio.viagem.BilheteServico.Bilhete.reserva_lugar
import pt.transporte.comboio.viagem.BilheteServico.Bilhete.reserva_viagem
import pt.transporte.comboio.viagem.ReservaServico.Reserva
import pt.transporte.comboio.viagem.ViagemServico.Viagem

@Serializable
data class BilheteExposed(val utilizador: PessoaExposed, val reserva: ReservaExposed,
    val paragemInicial: ParagemExposed, val paragemFinal: ParagemExposed)


class BilheteServico(database: Database) {
    object Bilhete: Table() {
        val utilizador = reference("id_pessoa", Pessoa.id)
        val reserva_viagem = integer("id_viagem")
        val reserva_lugar = integer("id_lugar")
        val paragemInicial = reference("id_paragem_inicial", Paragem.id)
        val paragemFinal = reference("id_paragem_final", Paragem.id)

        override val primaryKey = PrimaryKey(utilizador, reserva_viagem, reserva_lugar)
    }

    init {
        foreignKey(
            reserva_viagem to ReservaServico.Reserva.viagem,
            reserva_lugar to ReservaServico.Reserva.lugar,
        )

        transaction {
            SchemaUtils.create(Bilhete)
        }
    }

    suspend fun criar(bilhete: BilheteExposed) {
        DButils.dbQuery {
            Bilhete.insert {
                it[Bilhete.utilizador] = bilhete.utilizador.id
                it[Bilhete.reserva_viagem] = bilhete.reserva.viagem.id
                it[Bilhete.reserva_lugar] = bilhete.reserva.lugar.id
                it[Bilhete.paragemInicial] = bilhete.paragemInicial.id
                it[Bilhete.paragemFinal] = bilhete.paragemFinal.id
            }
        }
    }

    suspend fun criar(utilizador: PessoaExposed, viagem: ViagemExposed, lugar: LugarExposed,
                      paragemInicial: ParagemExposed, paragemFinal: ParagemExposed) {

        transaction {
            Reserva.insert {
                it[Reserva.viagem] = viagem.id
                it[Reserva.lugar] = lugar.id
            }
        }
        val reserva = ReservaExposed(viagem, lugar)
        val bilhete = BilheteExposed(utilizador, reserva, paragemInicial, paragemFinal)
        criar(bilhete)
    }

    suspend fun ler(): List<BilheteExposed> {
        val paragemInicialAlias = Paragem.alias("paragemInicial")
        val paragemFinalAlias = Paragem.alias("paragemFinal")

        return DButils.dbQuery {
            (Pessoa innerJoin Bilhete innerJoin Reserva innerJoin
                    (Viagem innerJoin Comboio innerJoin Lugar) innerJoin Linha
                .join(
                    paragemInicialAlias,
                    JoinType.INNER, Bilhete.paragemInicial, paragemInicialAlias[Paragem.id]
                ).join(
                    paragemFinalAlias,
                    JoinType.INNER, Bilhete.paragemFinal, paragemFinalAlias[Paragem.id]
                )
            ).selectAll()
                .map {
                    val utilizador = PessoaExposed(it[Pessoa.id], it[Pessoa.nome], it[Pessoa.nif])
                    val comboio = ComboioExposed(it[Comboio.id], it[Comboio.modelo])
                    val lugar = LugarExposed(it[Lugar.id], it[Lugar.assento], comboio)
                    val linha = LinhaExposed(it[Linha.id], it[Linha.nome])
                    val viagem = ViagemExposed(it[Viagem.id], linha, comboio, it[Viagem.direcao], it[Viagem.dataHora])
                    val paragemInicial = ParagemExposed(it[paragemInicialAlias[Paragem.id]],
                        it[paragemInicialAlias[Paragem.nome]], it[paragemInicialAlias[Paragem.distrito]])
                    val paragemFinal = ParagemExposed(it[paragemFinalAlias[Paragem.id]],
                        it[paragemFinalAlias[Paragem.nome]], it[paragemFinalAlias[Paragem.distrito]])
                    val reserva = ReservaExposed(viagem, lugar)

                    BilheteExposed(utilizador, reserva, paragemInicial, paragemFinal)
                }
        }
    }
}