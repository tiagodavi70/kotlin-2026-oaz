package exercicios.ex2
import exercicios.ex1.aleatoriezarNome
import kotlin.random.Random

fun main() {
    val automoveis = emptyList<Automovel>().toMutableList()

    for (i in 0..<50) {
        val ltTanque: Double = Random.nextDouble(100.0, 200.0)
        val consumo: Double = Random.nextDouble(0.05, 0.08)
        val automovel: Automovel = if (Random.nextDouble() > 0.5) {
            Automovel(aleatoriezarNome(8), ltTanque, consumo)
        } else {
            Economico(aleatoriezarNome(8), ltTanque, consumo)
        }
        automoveis.add(automovel)
    }

    for (auto in automoveis) {
        val utilizacao:Double = auto.litrosUtilizados(150.0)
        println(utilizacao)
    }
}