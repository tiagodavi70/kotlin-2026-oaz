package ex4

class Item(val nome:String, val preco: Double, val imposto: Double = 0.23) {

    init {
        require(preco >= 0 ) { "Preço não pode ser negativo." }
    }

    fun valorComImposto(): Double {
        return preco + preco * imposto
    }

    val valorComImposto: Double
        get() = preco + preco * imposto
}