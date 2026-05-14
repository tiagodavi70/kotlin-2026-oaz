package pt.transporte.comboio.linha

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.batchInsert
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import pt.transporte.comboio.utils.DButils
import pt.transporte.comboio.linha.ParagemServico.Paragem

@Serializable
data class LinhaExposed(val id:Int, val nome:String,
                   val paragens: List<ParagemExposed> = emptyList()) {
    val tempoTotal: Int
        get() = paragens.size * 3
}

class LinhaServico(database: Database) {
    object Linha: Table() {
        val id = integer("id").autoIncrement()
        val nome = varchar("nome", 30)

        override val primaryKey = PrimaryKey(id)
    }

    object LinhaParagem: Table() {
        val linha = reference("id_linha", Linha.id)
        val paragem = reference("id_paragem", Paragem.id)

        override val primaryKey = PrimaryKey(linha, paragem)
    }

    init {
        transaction(database) {
            SchemaUtils.create(Linha)
            SchemaUtils.create(LinhaParagem)
        }
    }

    suspend fun criar(linha: LinhaExposed) {
        DButils.dbQuery {
            val linhaId = Linha.insert {
                it[Linha.nome] = linha.nome
            }[Linha.id]

            val paragens = Paragem.selectAll()
                .where { Paragem.nome inList linha.paragens.map{p -> p.nome}}
                .map { ParagemExposed(it[Paragem.id], it[Paragem.nome],
                        it[Paragem.distrito],  it[Paragem.estacao] )}

            // println(paragens)
            LinhaParagem.batchInsert(paragens) { (id) ->
                // println(id)
                this[LinhaParagem.linha] = linhaId
                this[LinhaParagem.paragem] = id
            }
        }
    }

    suspend fun criar(linha: LinhaExposed, paragens: List<ParagemExposed>) {
        DButils.dbQuery {

        }
    }

    suspend fun ler(): List<LinhaExposed> {
        return DButils.dbQuery {
            (Linha innerJoin LinhaParagem innerJoin Paragem)
                .selectAll()
                .groupBy { it[Linha.id] }
                .map { (idLinha, rows) ->
                    LinhaExposed(idLinha, rows.first()[Linha.nome],
                        rows.map { linha ->
                            ParagemExposed(linha[Paragem.id], linha[Paragem.nome],
                                linha[Paragem.distrito], linha[Paragem.estacao])
                        }
                    )
                }
        }
    }

    suspend fun ler(id: Int): List<LinhaExposed> {
        return DButils.dbQuery {
            (Linha innerJoin LinhaParagem innerJoin Paragem)
                .selectAll()
                .where { Linha.id eq id }
                .groupBy { it[Linha.id] }
                .map { (idLinha, linhas) ->
                    LinhaExposed(idLinha, linhas.first()[Linha.nome],
                        linhas.map { linha ->
                            ParagemExposed(linha[Paragem.id], linha[Paragem.nome],
                                linha[Paragem.distrito], linha[Paragem.estacao]
                            )
                        }
                    )
                }
        }
    }
}