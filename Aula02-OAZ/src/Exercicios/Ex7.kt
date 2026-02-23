package Exercicios

fun main() {

    for (i in 1..6) {
        for (j in 1..6) {
            for (k in 1..6) {
                println("$i $j $k")
                if ( i + j + k == 12) {
                    println("Soma 12")
                }
            }
        }
    }
}