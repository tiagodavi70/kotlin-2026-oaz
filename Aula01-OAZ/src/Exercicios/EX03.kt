package Exercicios

fun main() {

    print("Entra com um número de três digítos: ")
    val cdu: Int = readln().toInt() // 321

    val c: Int = cdu / 100 // 3
    val d: Int = (cdu - (c*100) ) / 10 // 2 (cdu / 10) % 10
    val u: Int = cdu - (c * 100 + d * 10) // 1 (cdu % 10)

    val udc = u * 100 + d * 10 + c
    print("$udc")
}