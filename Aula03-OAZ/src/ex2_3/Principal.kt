package ex2_3

fun main() {

    val carro1 = Carro("a", "b", 100.0, 2022)
    val carro2 = Carro("c", "d", 120.0, 2023)
    val carro3 = Carro("e", "f", 110.0, 2024)
    val carro4 = Carro("g", "h", 130.0, 2025)

    val listaCarros = listOf<Carro>(carro1, carro2, carro3, carro4)

    for (carro in listaCarros) {
        carro.exibir()
    }
}