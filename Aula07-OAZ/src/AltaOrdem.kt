
class Produto(val preco: Double) {
    override fun toString(): String {
        return "Produto(preco=$preco)"
    }
}

fun main() {
    val lista = listOf(1, 2, 3, 4, 5)
    val dobrado = lista.map { item -> item * 2 }
    println(dobrado)
    println(lista.sum())

    val listaP = listOf(Produto(1.0), Produto(1.0), Produto(2.0))
    val soma = listaP.sumOf { produto -> produto.preco }
    val filtrado = listaP.filter { produto -> produto.preco > 1.0 }
    val produtos = lista.map { item -> Produto(item.toDouble()) } // .filter { p -> p.preco > 2.0 }
    println(filtrado[0].preco)
    println(produtos)
}