package Q5

import kotlin.random.Random

fun main() {

    val robosLimpeza = Array<RoboLimpeza>(30) { RoboLimpeza(Random.nextInt(1, 18)) }
    val robosCozinha = Array<RoboCozinha>(30) { RoboCozinha(Random.nextInt(1, 18)) }

    for (robo in robosLimpeza) {
        print("${robo.energia} ")
    }
    println()
    // println(robosCozinha.toList().map { it.energia })

    val robos = emptyList<Navegacao>().toMutableList()
    robos.addAll(robosLimpeza)
    robos.addAll(robosCozinha)

    var cont: Int = 0
    for (robo in robos) {
        println("Robo nº: ${cont++}")
        for (tarefa in 0..<5) {
            try {
                robo.executarTarefa(Random.nextInt(1, 5))
            } catch (e: Exception) {
                println("Energia Insuficiente, voltando pra carregar")
                robo.retornarCarregar()
            }
        }
        robo.retornarCarregar()
    }
}