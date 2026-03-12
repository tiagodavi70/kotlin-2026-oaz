package exercicios.ex1

open abstract class Felino(val nome: String, val peso: Double) {

    abstract fun domestico(): Boolean // pode ter atributo abstrato

    override fun toString(): String = "Felino: $nome tem $peso quilos"
}