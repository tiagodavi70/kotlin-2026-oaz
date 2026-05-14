package pt.transporte.comboio.comboio

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import pt.transporte.comboio.utils.DButils
import pt.transporte.comboio.comboio.LugarServico.Lugar

@Serializable
data class ComboioExposed(val id: Int, val modelo: String, val lugares: List<LugarExposed> = emptyList())

class ComboioServico(database: Database) {
    object Comboio: Table() {
        val id = integer("id").autoIncrement()
        val modelo = varchar("nome", 20)

        override val primaryKey = PrimaryKey(id)
    }

    init {
        transaction(database) {
            SchemaUtils.create(Comboio)
        }
    }

    suspend fun criar(comboio: ComboioExposed): Int {
        return DButils.dbQuery {
            Comboio.insert {
                it[modelo] = comboio.modelo
            }
        }[Comboio.id]
    }

    suspend fun ler(): List<ComboioExposed> {
        return DButils.dbQuery {

            val resultado = (Comboio innerJoin Lugar)
                .selectAll()
                .groupBy { it[Comboio.id] }

            resultado.map { (_, linhas) ->

                val primeira = linhas.first()

                val lugares = linhas.map { linha ->
                    LugarExposed(linha[Lugar.id], linha[Lugar.assento],
                        ComboioExposed(linha[Comboio.id], linha[Comboio.modelo]))
                }
                ComboioExposed(primeira[Comboio.id], primeira[Comboio.modelo], lugares)
            }
        }
    }
    suspend fun lerSemLugar(id: Int): ComboioExposed? {
        return DButils.dbQuery {
            Comboio
                .selectAll()
                .where { Comboio.id eq id }
                .map { ComboioExposed(it[Comboio.id], it[Comboio.modelo]) }
                .singleOrNull()
        }
    }

    suspend fun ler(id: Int): ComboioExposed? {
        return DButils.dbQuery {

            val resultado = (Comboio innerJoin Lugar)
                .selectAll()
                .groupBy { id }

            resultado.map { (_, linhas) ->

                val primeira = linhas.first()

                val lugares = linhas.map { linha ->
                    LugarExposed(linha[Lugar.id], linha[Lugar.assento],
                        ComboioExposed(linha[Comboio.id], linha[Comboio.modelo]))
                }
                ComboioExposed(primeira[Comboio.id], primeira[Comboio.modelo], lugares)
            }.singleOrNull()
        }
    }
}