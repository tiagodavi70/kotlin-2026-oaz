package Q3_4

class ContaBancaria(val id: String, saldo: Double) {
    private var saldo: Double = saldo

    fun saldo(): Double {
        return saldo
    }

    init {
        require(saldo >= 0) { "Não pode ter valor negativo. "}
    }

    fun retirada(valor: Double) {
        require(saldo - valor >= 0) { "Saldo insuficiente" }
        saldo -= valor
    }

    fun deposito(valor: Double) {
        saldo += valor
    }
}

