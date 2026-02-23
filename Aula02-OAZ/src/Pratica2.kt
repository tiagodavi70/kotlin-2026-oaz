
// Criar um algoritmo que armazene cinco nomes em um vetor
// e depois possa ser digitado um nome e, se for encontrado,
// imprimir a posição desse nome no vetor; caso contrário,
// imprimir uma mensagem de não encontrado.

fun main() {
    val nome = Array(5) { "" }
    val nomes = arrayOf("Tiago", "Davi", "", "Alexandre", "Manoel")

    for (i in 0..<nome.size) {
        print("Entra com o nome: ")
        nome[i] = readln()
    }

    print("Entra com o nome para procurar: ")
    val novoNome:String = readln()
    var encontrado:Boolean = false

    for (i in 0..<nome.size) {
        if (novoNome == nome[i]) {
            encontrado = true
            println("O nome está na posição ${i+1}")
        }
    }
    if (!encontrado) {
        println("Nome não encontrado. ")
    }

    for (item in nome) {
        print("$item ")
    }
}