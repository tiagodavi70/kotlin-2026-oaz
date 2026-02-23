
fun main() {

    // nome = "Tiago" - Python
    val nome: String = "Tiago"
    val idade: Int = 33
    val altura:Double = 1.78
    var aluno: Boolean = true

    // nome = "Davi" // Erro - val é imutável

    aluno = false

//    val altura:Float = 1.78f
    println(nome)
    println(idade)
    println(altura)
    println(aluno)

    // + - / * %
    println(1 + 2)
    println(1 - 2)
    println(1 / 2.0) // 0
    println(1 * 2)
    println(1 % 2)

    println("Olá" + " mundo")

    val a:Boolean = true
    val b = false

    println(a && b) // and
    println(a || b) // or
    println(!a) // not
}