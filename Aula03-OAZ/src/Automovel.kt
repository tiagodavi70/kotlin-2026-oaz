
class Automovel(val modelo: String, val ano: Int = 2026, val preco: Double) {

    init {
        require(ano > 0) { "Ano deve ser positivo." }
        require(preco > 0) { "Preço deve ser positivo" }
    }

    constructor(modelo: String, ano: Int, preco: String): this(modelo, ano, preco.toDouble())
    constructor(modelo: String, ano: String, preco: String): this(modelo, ano.toInt(), preco.toDouble())
    constructor(automovel: Automovel): this(automovel.modelo, automovel.ano, automovel.preco)
    constructor(automovel: Automovel, ano: Int): this(automovel.modelo, ano, automovel.preco)
    constructor(): this("", 0, 0.0)

    fun aplicarImposto(imposto: Double = 0.23): Double {
        return preco + preco * imposto
    }
}