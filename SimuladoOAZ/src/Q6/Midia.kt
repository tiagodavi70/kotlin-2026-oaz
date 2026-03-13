package Q6

abstract class Midia(val titulo: String, val genero: String, val preco: Double) {
    init { require(preco > 0) { "Preço não pode ser menor que 0" } }
}