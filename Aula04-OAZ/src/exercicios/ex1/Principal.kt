package exercicios.ex1

import kotlin.random.Random

fun aleatoriezarNome(tamanho: Int): String {
    val alfabeto = "abcdefghijklmnopqrstuvwxyz".toCharArray()
    val nomeAleatorio = CharArray(tamanho) { alfabeto[Random.nextInt(0 ,alfabeto.size-1)] }
    return nomeAleatorio.concatToString()
}

fun main() {
    val felinos = emptyList<Felino>().toMutableList()
    for (i in 0..<40) {
        val nome = aleatoriezarNome(6)
        val peso = Random.nextDouble(100.0, 300.0)

        val felino: Felino = when (Random.nextInt(1,3)) {
            1 -> Gato(nome, peso / 90)
            2 -> Tigre(nome, peso)
            3 -> Leao(nome, peso)
            else -> Gato("aaaaaaaaaaaa", 1000.0) // Não deve ocorrer
        }
//        when (Random.nextInt(1,3)) {
//            1 -> felino = Gato(nome, peso / 90)
//            2 -> felino = Tigre(nome, peso)
//            3 -> felino = Leao(nome, peso)
//            else -> {}
//        }
        felinos.add(felino)
    }
    println(felinos)
}