package pt.transporte.comboio.linha

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update

import pt.transporte.comboio.utils.DButils

@Serializable
data class ParagemExposed(val id: Int, val nome: String,
                          val distrito: String, val estacao: Boolean = true) {
    init {
        require(nome.length >= 3) { "Nome muito pequeno (< 3)" }
    }
}

class ParagemServico(database: Database) {

    object Paragem: Table() {
        val id = integer("id").autoIncrement()
        val nome = varchar("nome", 50)
        val estacao = bool("estacao")
        val distrito = varchar("distrito", 15)

        override val primaryKey = PrimaryKey(id)
    }

    init {
        transaction(database) {
            SchemaUtils.create(Paragem)
        }
    }

    suspend fun criar(paragem: ParagemExposed) {
        DButils.dbQuery {
            Paragem.insert {
                it[nome] = paragem.nome
                it[estacao] = paragem.estacao
                it[distrito] = paragem.distrito
            }
        }
    }

    suspend fun ler(): List<ParagemExposed> {
        return DButils.dbQuery {
            Paragem.selectAll()
                .map {
                    ParagemExposed(it[Paragem.id], it[Paragem.nome],
                        it[Paragem.distrito],  it[Paragem.estacao] )
                }
        }
    }

    suspend fun ler(id: Int): ParagemExposed? {
        return DButils.dbQuery {
            Paragem.selectAll()
                .where { Paragem.id eq id } // Paragem.id == id // EQUALS
                .map {
                    ParagemExposed(it[Paragem.id], it[Paragem.nome],
                        it[Paragem.distrito],  it[Paragem.estacao] ) }
                .singleOrNull()
        }
    }

    suspend fun atualizar(id: Int, paragem: ParagemExposed) {
        DButils.dbQuery {
            Paragem.update({ Paragem.id eq id }) {
                it[Paragem.nome] = paragem.nome
                it[Paragem.estacao] = paragem.estacao
                it[Paragem.distrito] = paragem.distrito
            }
        }
    }

    suspend fun apagar(id: Int) {
        DButils.dbQuery {
            Paragem.deleteWhere { Paragem.id eq id }
        }
    }
}