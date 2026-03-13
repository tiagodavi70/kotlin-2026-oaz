package Q2

fun main() {
    val canhao1 = Canhao(10, "a", 5.0)
    val canhao2 = Canhao(8, "b", 6.0)

    val garrafa1 = Garrafa("a", "vidro", true)
    val garrafa2 = Garrafa("b", "plástico", true)

    val ponte1 = Ponte("a", 10000.0, 10)
    val ponte2 = Ponte("b", 20000.0, 12)

    val caixa1 = Caixa("madeira", 1.0, 1.0)
    val caixa2 = Caixa("madeira", 0.7, 0.5)

    println("$canhao1 / $canhao2 / $garrafa1 / $garrafa2 / $ponte1 / $ponte2 / $caixa1 / $caixa2")

}