
fun main() {

    val a = 15
    val b = 0

//    if (b == 0) {
//        println("Não é possível dividir por 0.")
//    } else {
//        val numero = a/b
//    }
    var numero = 0
    try {
        numero = a/b
    } catch (excecao: ArithmeticException) {
        println(excecao.message)
        print("Valor b não pode ser 0, indique um valor novo: ")
        numero = a / readln().toInt()
    }

    println("Encerrado: $numero")
}