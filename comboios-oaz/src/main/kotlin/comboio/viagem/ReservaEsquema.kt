package pt.transporte.comboio.viagem

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import pt.transporte.comboio.utils.DButils.dbQuery
import pt.transporte.comboio.comboio.ComboioExposed
import pt.transporte.comboio.comboio.ComboioServico.Comboio
import pt.transporte.comboio.comboio.LugarExposed
import pt.transporte.comboio.comboio.LugarServico.Lugar
import pt.transporte.comboio.linha.LinhaExposed
import pt.transporte.comboio.linha.LinhaServico.Linha
import pt.transporte.comboio.viagem.ViagemServico.Viagem

@Serializable
data class ReservaExposed(val viagem: ViagemExposed, val lugar: LugarExposed)

class ReservaServico(database: Database) {
    object Reserva: Table() {
        val viagem = reference("id_viagem", Viagem.id)
        val lugar = reference("id_lugar", Lugar.id)

        override val primaryKey = PrimaryKey(viagem, lugar)
    }

    init {
        transaction {
            SchemaUtils.create(Reserva)
        }
    }

    suspend fun criar(reserva: ReservaExposed) {
        dbQuery {
            Reserva.insert {
                it[viagem] = reserva.viagem.id
                it[lugar] = reserva.lugar.id
            }
        }
    }

    suspend fun ler(): List<ReservaExposed> {
        return dbQuery {
            (Reserva innerJoin Viagem innerJoin Lugar).selectAll()
                .map {
                    val comboio = Comboio.selectAll()
                        .where { Comboio.id eq it[Lugar.comboio]}
                        .map { c -> ComboioExposed(c[Comboio.id],
                            c[Comboio.modelo]) }
                        .single()

                    val viagem = (Viagem innerJoin Linha).selectAll()
                        .where { Viagem.id eq it[Reserva.viagem]}
                        .map { v ->
                            val linha = LinhaExposed(v[Linha.id], v[Linha.nome])
                            ViagemExposed(v[Viagem.id],
                            linha, comboio, v[Viagem.direcao], v[Viagem.dataHora]) }
                        .single()

                    val lugar = LugarExposed(it[Lugar.id],
                        it[Lugar.assento],
                        comboio,
                        it[Lugar.classe])
                    ReservaExposed(viagem, lugar)
                }
        }
    }
}