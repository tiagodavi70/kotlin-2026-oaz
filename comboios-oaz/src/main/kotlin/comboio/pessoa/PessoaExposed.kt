package pt.transporte.comboio.pessoa

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import pt.transporte.comboio.utils.DButils.dbQuery

@Serializable
data class PessoaExposed(val nome:String, val nif:String) {
    init {
        require(nif.length == 9 && nif.toIntOrNull() != null) { "NIF tem que ter 9 posições" }
    }
}

class PessoaServico(database: Database) {
    object Pessoa: Table() {
        val id = integer("id").autoIncrement()
        val nome = varchar("nome", 50)
        val nif = varchar("nif", 9)
    }

    init {
        transaction {
            SchemaUtils.create(Pessoa)
        }
    }

    suspend fun criar(utilizador: PessoaExposed) {
        dbQuery {
            Pessoa.insert {
                it[nome] = utilizador.nome
                it[nif] = utilizador.nif
            }
        }
    }

    suspend fun ler(): List<PessoaExposed> {
        return dbQuery {
            Pessoa.selectAll()
                .map { PessoaExposed(it[Pessoa.nome], it[Pessoa.nif]) }
        }
    }
}