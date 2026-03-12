package animal

class Passaro(nome:String, ano: Int): Animal(nome, ano) {

    override fun som(): String {
        return "cucko cucko"
    }

    override fun ola(): String {
        return "Olá"
    }
}