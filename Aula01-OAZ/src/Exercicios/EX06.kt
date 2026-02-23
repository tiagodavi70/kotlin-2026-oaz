package Exercicios

fun main() {

    var entrada:String = "0"
    var contador: Int = 0
    while (entrada != "q") {
        print("Entra com um número (q para sair):")
        entrada = readln()
        if (entrada != "q") {
            val numero = entrada.toInt()
            if (numero >= 50 && numero <= 150) {
                contador++
            }
        }
    }
    println("Contador: $contador")
}