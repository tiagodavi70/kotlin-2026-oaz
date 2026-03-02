package ex4

fun main() {

    val pedido = Pedido("Tiago", "26/02/2026")
    val item1 = Item("a", 3.0)
    val item2 = Item("b", 4.0)
    val item3 = Item("c", 5.0)

    pedido.adicionar(item1)
    pedido.adicionar(item2)
    pedido.adicionar(item3)

    val total = pedido.total()
    println(total)
}