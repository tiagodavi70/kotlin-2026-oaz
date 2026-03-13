
fun main() {

    print("Entra com a idade: ")
    val idade = readln().toInt()

    // error("Lançando erro aleatório.")

    require(idade >= 18) { "Idade tem que ser maior que 18" }

    if (idade <= 18) {
        throw IllegalArgumentException("Idade tem que ser maior que 18.")
    }


}