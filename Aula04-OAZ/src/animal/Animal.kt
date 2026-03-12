package animal

import java.time.LocalDate

open abstract class Animal(val nome: String, val ano: Int) {

    constructor(nome: String): this(nome, LocalDate.now().year)

    open fun som(): String {
        return "---"
    }

    fun idade(): Int {
        return LocalDate.now().year - ano
    }

    abstract fun ola(): String

    override fun toString(): String {
        return "Esse animal se chama $nome e tem ${idade()} anos."
    }
}