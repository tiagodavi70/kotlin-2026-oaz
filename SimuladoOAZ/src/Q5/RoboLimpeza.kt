package Q5

class RoboLimpeza(energia: Int = 18): Navegacao {
    val limiteBateria: Int = 18
    var energia: Int = energia

    init {
        require(energia > 0) { "Energia não pode ser negativa" }
    }

    override fun executarTarefa(energiaUtilizada: Int) {
        require(energia - energiaUtilizada > 0) { "Energia insuficiente" }
        energia -= energiaUtilizada
    }

    override fun retornarCarregar() {
        energia = limiteBateria
    }


}