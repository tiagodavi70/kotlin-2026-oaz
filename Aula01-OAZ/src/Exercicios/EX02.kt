package Exercicios

fun main() {
    print("Entra com o salário do colaborador: ")
    val salarioColaborador: Double = readln().toDouble()

    print("Entra com o valor do salário mínimo: ")
    val salarioMinimo: Double = readln().toDouble()

    for (i in 1..10) {

    }

    println("O colaborador recebe ${"%.2f".format(salarioColaborador / salarioMinimo)}")
}