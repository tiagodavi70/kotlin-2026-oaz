
// Criar um algoritmo que armazene nome e duas notas de
// 5 alunos e imprima uma listagem contendo nome, as duas notas
// e a média de cada aluno.

fun main() {
    val nome = Array(5) { "" }
    val nota1: Array<Double> = Array(5) { 0.0 }
    val nota2 = Array(5) { 0.0 }

    for (i in 0..<nome.size) {
        print("Entra com o nome: ")
        nome[i] = readln()
        print("Entra com a nota 1: ")
        nota1[i] = readln().toDouble()
        print("Entra com o nota 2: ")
        nota2[i] = readln().toDouble()
    }

    for (i in 0..<nome.size) {
        println("${nome[i]}: ${(nota1[i] + nota2[i]) / 2}")
    }
}