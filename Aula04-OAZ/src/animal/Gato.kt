package animal

class Gato(nome: String, ano: Int): Animal(nome, ano) {

    override fun som(): String {
        return "miau"
    }

    override fun ola(): String {
        return "Olá"
    }
}