package Exercicios

fun main() {
    print("Entra com o nome da equipa 1: ")
    val equipa1: String = readln()
    print("Entra com o número de gols: ")
    val gols1: Int = readln().toInt()

    print("Entra com o nome da equipa 2: ")
    val equipa2: String = readln()
    print("Entra com o número de gols: ")
    val gols2: Int = readln().toInt()

    if (gols1 > gols2) {
        println("Equipa $equipa1 venceu")
    } else if (gols2 > gols1) {
        println("Equipa $equipa2 venceu")
    } else {
        println("Empate")
    }
}