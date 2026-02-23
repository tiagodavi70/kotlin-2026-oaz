
fun soma(numero1: Int, numero2: Int): Int {
    val resultado = numero1 + numero2
    return resultado
}

fun mostrarNaTela(nome: String) {
    println("Olá $nome")
}

// fun soma(Int, Int): Int

// nome, parametros, retorno, corpo

fun main() {
    val numeroInteiro:Int = soma(1, 2)
    mostrarNaTela("Tiago")
}