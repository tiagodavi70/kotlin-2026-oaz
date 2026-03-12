package exercicios.ex1

class Gato(nome: String, peso: Double): Felino(nome, peso) {
    override fun domestico(): Boolean = true

    override fun toString(): String {
        return super.toString() + " | Gato"
    }
}