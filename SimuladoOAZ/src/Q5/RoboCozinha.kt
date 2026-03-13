package Q5

class RoboCozinha(energia: Int = 20): Navegacao {
    val limiteBateria: Int = 20
    var energia: Int = energia

    init {
        require(energia > 0) { "Energia negativa." }
    }

    override fun executarTarefa(energiaUtilizada: Int) {
        require(energia - energiaUtilizada > 0) { "Energia insuficiente." }
        energia -= energiaUtilizada
    }

    override fun retornarCarregar() {
        energia = limiteBateria
    }
}