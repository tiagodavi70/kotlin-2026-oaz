package pt.transporte.comboio.viagem

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import pt.transporte.comboio.comboio.ComboioExposed
import pt.transporte.comboio.comboio.ComboioServico.Comboio
import pt.transporte.comboio.linha.LinhaExposed
import pt.transporte.comboio.linha.LinhaServico.Linha
import pt.transporte.comboio.utils.DButils.dbQuery

@Serializable
data class ViagemExposed(val id: Int, val linha: LinhaExposed,
    val comboio: ComboioExposed, val direcao: Char = 'I') // ida, volta, idasemapiadeiro, voltasemapieadiero

class ViagemServico(database: Database) {
    object Viagem: Table() {
        val id = integer("id").autoIncrement()
        val linha = reference("id_linha", Linha.id)
        val comboio = reference("id_comboio", Comboio.id)
        val direcao = char("direcao")

        override val primaryKey = PrimaryKey(id)
    }

    init {
        transaction {
            SchemaUtils.create(Viagem)
        }
    }


    suspend fun criar(viagem: ViagemExposed) {
        dbQuery {
            Viagem.insert {
                it[linha] = viagem.linha.id
                it[direcao] = viagem.direcao
                it[comboio] = viagem.comboio.id
            }
        }
    }

    suspend fun ler(): List<ViagemExposed> {
        return dbQuery {
            (Viagem innerJoin Linha innerJoin Comboio).selectAll()
                .map {
                    val linha = LinhaExposed(it[Viagem.linha],it[Linha.nome])
                    val comboio = ComboioExposed(it[Viagem.comboio], it[Comboio.modelo])

                    ViagemExposed(it[Viagem.id],
                        linha, comboio,
                        it[Viagem.direcao])
                }
        }
    }

    suspend fun ler(id: Int): ViagemExposed? {
        return dbQuery {
            (Viagem innerJoin Linha innerJoin Comboio).selectAll()
                .where { Viagem.id eq id }
                .map {
                    val linha = LinhaExposed(it[Viagem.linha],it[Linha.nome])
                    val comboio = ComboioExposed(it[Viagem.comboio], it[Comboio.modelo])

                    ViagemExposed(it[Viagem.id],
                        linha, comboio,
                        it[Viagem.direcao])
                }.singleOrNull()
        }
    }
}