package animal

class Cachorro(nome: String): Animal(nome) {

    override fun som(): String {
        return "au au"
    }

    override fun ola(): String {
        return "Olá"
    }
}