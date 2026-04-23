package pt.transporte.comboio.linha

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.transactions.transaction

@Serializable
data class ParagemExposed(val id: Int, val nome: String,
                          val estacao: Boolean = true) {
    init {
        require (nome.length >= 3) { "Nome muito pequeno (< 3)" }
    }
}

class ParagemServico(database: Database) {

    object Paragem: Table() {
        val id = integer("id").autoIncrement()
        val nome = varchar("nome", 50)
        val estacao = bool("estacao")

        override val primaryKey = PrimaryKey(id)
    }

    init {
        transaction(database) {
            SchemaUtils.create(Paragem)
        }
    }

    // funções de operação - criar, ler, atualizar e apagar
}