package pt.transporte.comboio.linha

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import pt.transporte.comboio.DButils

@Serializable
data class RegiaoExposed(val id: Int, val nome: String, val abreviatura: String) {
    init {
        require(abreviatura.length < 4 ) { "Abreviatura muito grande" }
    }
}

class RegiaoServico(database: Database) {
    object Regiao: Table() {
        val id = integer("id").autoIncrement()
        val nome = varchar("nome", 50)
        val abreviatura = varchar("abreviatura", 3)

        override val primaryKey = PrimaryKey(id)
    }

    init {
        transaction(database) {
            SchemaUtils.create(Regiao)
        }
    }

    suspend fun criar(regiao: RegiaoExposed) {
        DButils.dbQuery {
            Regiao.insert {
                it[nome] = regiao.nome
                it[abreviatura] = regiao.abreviatura
            }
        }
    }

    suspend fun ler(): List<RegiaoExposed> {
        return DButils.dbQuery {
            Regiao.selectAll()
                .map { RegiaoExposed(it[Regiao.id], it[Regiao.nome], it[Regiao.abreviatura])}
        }
    }

    suspend fun ler(id: Int): RegiaoExposed? {
        return DButils.dbQuery {
            Regiao.selectAll()
                .where { Regiao.id eq id}
                . map { RegiaoExposed(it[Regiao.id], it[Regiao.nome], it[Regiao.abreviatura]) }
                .singleOrNull()
        }
    }
}