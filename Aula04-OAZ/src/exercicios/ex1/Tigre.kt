package exercicios.ex1

class Tigre(nome: String, peso: Double): Felino(nome, peso) {
    override fun domestico(): Boolean = false
}