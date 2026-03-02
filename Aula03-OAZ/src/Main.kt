
fun main() {

    val pessoa1= Pessoa("Tiago", "999999999", "999999")
    val pessoa2 = Pessoa("Manoel", "888888888", "888888")

    val automovel1 = Automovel("iuheuiqh", 2025, 15000.0)
    val automovel2 = Automovel("iuheuiqh", 2018, 12000.0)

    val compra1 = Compra(pessoa1, automovel1, "26/02/2026")
    val compra2 = Compra(pessoa2, automovel2, "26/02/2026")

    println("${automovel1.preco} ${automovel1.aplicarImposto()}")
    println("${automovel1.preco} ${automovel1.aplicarImposto(0.30)}")

    val automovel3 = Automovel()
}