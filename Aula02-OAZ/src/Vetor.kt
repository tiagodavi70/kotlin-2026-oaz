
// Criar um algoritmo que armazene números em dois vetores inteiros
// de cinco elementos cada. Gere e imprima um vetor que some ambos.

fun main() {

    val vetor1: Array<Int> = Array(5) { 0 }
    val vetor2: Array<Int> = Array(5) { 0 }
    val soma: Array<Int> = Array(5) { 0 }

    for (i in 0..<vetor1.size) {
        print("Entra com o valor $i do vetor 1: ")
        vetor1[i] = readln().toInt()
    }
    println()
    for (i in 0..<vetor2.size) {
        print("Entra com o valor $i do vetor 2: ")
        vetor2[i] = readln().toInt()
    }
    println()
    for (i in 0..<soma.size) {
        soma[i] = vetor1[i] + vetor2[i]
    }

    for (item in soma) {
        print("$item ")
    }
    println()
    for (item in vetor1) {
        print("$item ")
    }
    println()
    for (item in vetor2) {
        print("$item ")
    }
    println()
    //println(vetor1)
    //println(vetor2)

}