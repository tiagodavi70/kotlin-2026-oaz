package pt.transporte.comboio.comboio

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import pt.transporte.comboio.comboio.ComboioServico.Comboio
import pt.transporte.comboio.utils.DButils
import pt.transporte.comboio.viagem.ReservaServico
import pt.transporte.comboio.viagem.ViagemServico
import pt.transporte.comboio.viagem.ViagemServico.Viagem

@Serializable
data class LugarExposed(val id: Int, val assento: String, val comboio: ComboioExposed, val classe: Char = '2')

class LugarServico(database: Database) {

    object Lugar : Table() {
        val id = integer("id").autoIncrement()
        val assento = varchar("nome", 3)
        val classe = char("classe")
        val comboio = reference("id_comboio", Comboio.id)

        override val primaryKey = PrimaryKey(id)
    }

    init {
        transaction(database) {
            SchemaUtils.create(Lugar)
        }
    }

    suspend fun criar(lugar: LugarExposed) {
        DButils.dbQuery {
            Lugar.insert {
                it[assento] = lugar.assento
                it[classe] = lugar.classe
                it[comboio] = lugar.comboio.id
                it[classe] = lugar.classe
            }
        }
    }

    suspend fun ler(): List<LugarExposed> {
        return DButils.dbQuery {
            (Lugar innerJoin Comboio).selectAll()
                .map {
                    LugarExposed(it[Lugar.id],
                        it[Lugar.assento],
                        ComboioExposed(it[Comboio.id], it[Comboio.modelo]),
                        it[Lugar.classe])
                }
        }
    }

    suspend fun ler(id: Int): LugarExposed? {
        return DButils.dbQuery {
            (Lugar innerJoin Comboio).selectAll()
                .where { Lugar.id eq id }
                .map {
                    LugarExposed(it[Lugar.id],
                        it[Lugar.assento],
                        ComboioExposed(it[Comboio.id], it[Comboio.modelo]),
                        it[Lugar.classe])
                }
                .singleOrNull()
        }
    }

    suspend fun lerDesocupados(idViagem: Int): List<LugarExposed> {
        return DButils.dbQuery {
            (Lugar innerJoin Comboio innerJoin ViagemServico.Viagem)
                .leftJoin(ReservaServico.Reserva)
                .selectAll()
                .where { (Viagem.id eq idViagem) and (ReservaServico.Reserva.viagem.isNull()) }
                .map {
                    LugarExposed(it[Lugar.id], it[Lugar.assento],
                        ComboioExposed(it[Comboio.id], it[Comboio.modelo]), it[Lugar.classe])
                }
        }
    }
}