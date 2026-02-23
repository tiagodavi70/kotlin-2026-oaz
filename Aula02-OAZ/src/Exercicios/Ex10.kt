package Exercicios
// Escreva um script que receba os dados sobre 15 pessoas: peso e idade.
// Ao final apresente:

// média de peso
// maior idade

// Se houver alguma entrada com idade menor que 12,
// não deve ser considerada.

fun main() {
    val pesos = Array(15) { 0.0 }
    val idades = Array(15) { 0 }

    var contador = 0
    while (contador < 15) {
        println("Entra com os valores para a pessoa ${contador+1}")
        print("Entra com o peso: ")
        val peso = readln().toDouble()
        print("Entra com a idade: ")
        val idade = readln().toInt()
        if (idade >= 12) {
            pesos[contador] = peso
            idades[contador] = idade
            contador++ // contador = contador + 1
        } else {
            println("Idade inválida.")
        }
    }

    val media = pesos.average()
    val maiorIdade = idades.max()

    println("$media $maiorIdade")
}