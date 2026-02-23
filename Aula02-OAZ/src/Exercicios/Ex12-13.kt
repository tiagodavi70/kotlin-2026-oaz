package Exercicios

import kotlin.random.Random

// Escreva uma função que dado um vetor calcule sua somatório e média.

// Escreva uma função que dado um valor n crie um vetor com n posições aleatórias.

fun criarAleatorio(tamanho:Int): Array<Int> {
    val vetor = Array<Int>(tamanho){ Random.nextInt(0, 100) }
    return vetor
}

fun somaMedia(vetor: Array<Int>): Map<String, Double> {
    val novoMap = mapOf("somatorio" to vetor.sum().toDouble(),
                    "media" to vetor.average())
    return novoMap
}

fun main() {
    val aleatorio = criarAleatorio(20)
    println(aleatorio.toList())
    println(somaMedia(aleatorio))
}