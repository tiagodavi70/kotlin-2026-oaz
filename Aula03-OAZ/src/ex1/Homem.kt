package ex1

class Homem(val nome: String, val anoNascimento: Int, val nif: String) {

    val idade: Int
        get() {
            return java.time.LocalDate.now().year - anoNascimento
        }

}