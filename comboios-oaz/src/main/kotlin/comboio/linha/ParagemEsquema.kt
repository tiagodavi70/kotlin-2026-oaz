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

import pt.transporte.comboio.DButils
import pt.transporte.comboio.linha.RegiaoServico.Regiao

@Serializable
data class ParagemExposed(val id: Int, val nome: String,
                          val regiao: RegiaoExposed, val estacao: Boolean = true) {
    init {
        require(nome.length >= 3) { "Nome muito pequeno (< 3)" }
    }
}

class ParagemServico(database: Database) {

    object Paragem: Table() {
        val id = integer("id").autoIncrement()
        val nome = varchar("nome", 50)
        val estacao = bool("estacao")
        val regiao = reference("regiaoId", Regiao.id)

        override val primaryKey = PrimaryKey(id)
    }

    init {
        transaction(database) {
            SchemaUtils.create(Paragem)
        }
    }

    // funções de operação - atualizar e apagar
    suspend fun criar(paragem: ParagemExposed) {
        DButils.dbQuery {
            Paragem.insert {
                it[nome] = paragem.nome
                it[estacao] = paragem.estacao
                it[regiao] = paragem.regiao.id
            }
        }
    }

    suspend fun ler(): List<ParagemExposed> {
        return DButils.dbQuery {
            (Paragem innerJoin Regiao).selectAll()
                .map {
                    val regiaoJoin = RegiaoExposed(
                        it[Regiao.id],
                        it[Regiao.nome],
                        it[Regiao.abreviatura]
                    )
                    ParagemExposed(it[Paragem.id], it[Paragem.nome],
                        regiaoJoin,  it[Paragem.estacao] )
                }
        }
    }

    suspend fun ler(id: Int): ParagemExposed? {
        return DButils.dbQuery {
            (Paragem innerJoin Regiao).selectAll()
                .where { Paragem.id eq id } // Paragem.id == id // EQUALS
                .map {
                    val regiao = RegiaoExposed(
                        it[Regiao.id],
                        it[Regiao.nome],
                        it[Regiao.abreviatura]
                    )
                    ParagemExposed(it[Paragem.id], it[Paragem.nome],
                        regiao,  it[Paragem.estacao] ) }
                .singleOrNull()
        }
    }

    suspend fun atualizar(id: Int, paragem: ParagemExposed) {
        DButils.dbQuery {
            Paragem.update({ Paragem.id eq id }) {
                it[Paragem.nome] = paragem.nome
                it[Paragem.estacao] = paragem.estacao
            }
        }
    }

    suspend fun apagar(id: Int) {
        DButils.dbQuery {
            Paragem.deleteWhere { Paragem.id eq id }
        }
    }
}