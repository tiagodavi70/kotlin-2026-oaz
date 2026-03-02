package ex2_3

class Carro(val marca: String, val modelo: String, val velocidade: Double, val ano: Int) {

    fun exibir() {
        println("${marca} ${this.modelo} ${this.velocidade} $ano ${this.idade()}")
    }

    fun idade():Int {
        return java.time.LocalDate.now().year - ano
    }

    fun converterMPH(): Double {
        return this.velocidade * 0.62137
    }

    val milhas: Double
        get() {
            return this.velocidade * 0.62137
        }
}