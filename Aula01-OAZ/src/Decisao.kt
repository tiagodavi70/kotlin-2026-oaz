
fun main() {
    val idade: Int = 16

    if (idade >= 18) {
        println("Pode votar.")
    } else {
        println("Não pode votar.")
    }

    val cidade: String = "São João da Madeira"
    when (cidade) {
        "Oliveira de Azeméis" -> println("Oliveirense")
        "Aveiro" -> println("Aveirense")
        "Porto" -> {
            println("Portista")
            println("Linha em branco")
        }
        else -> println("Sem cidade cadastrada.")
    }
    val nome:String = "Tiago"
    when {
        4 % 2 == 0 -> println("par")
        nome == "Tiago" -> println("É Tiago")
        else -> println("Qualquer coisa")
    }
}