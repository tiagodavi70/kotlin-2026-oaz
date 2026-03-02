package ex4

class Pedido(val cliente: String, val data:String ) {

    val itens: MutableList<Item> // é pública quando não devia ser

    init {
        itens = emptyList<Item>().toMutableList()
    }

    fun adicionar(item: Item) {
        itens.add(item)
    }

    fun recuperarLista(): List<Item> {
        return itens.toList()
    }

    fun total(): Double {

        // val soma:Double = itens.sumOf { it -> it.preco }
        var soma: Double = 0.0
        for (item in itens) {
            soma += item.preco
        }
        return soma
    }
}