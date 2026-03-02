//class TestePessoa(val nome:String, val idade: Int)

//class TestePessoa(nome: String, idade: Int) {
//    val nome:String = nome
//    val idade:Int = idade
//}

class TestePessoa(nome: String, idade: Int) {
    val nome: String
    val idade: Int

    init {
        require(!nome.isBlank()) { "Nome não pode ser vazio" }
        require(idade >= 0) { "Idade não pode ser negativa" }

        this.nome = nome
        this.idade = idade
    }
}

fun main() {
    val testePessoa1 = TestePessoa("Tiago", 33)
    val testePessoa2 = TestePessoa("Manoel", 32)
    val testePessoa3 = TestePessoa("Alexandre", 35)

    testePessoa1.nome
    println("${testePessoa1.nome} ${testePessoa1.idade}")
    println("${testePessoa2.nome} ${testePessoa2.idade}")
    println("${testePessoa3.nome} ${testePessoa3.idade}")
}