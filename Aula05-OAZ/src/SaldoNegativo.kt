class Conta(private var _saldo: Double) {
    init {
        if (_saldo < 0) {
            throw SaldoNegativoException()
        }
    }
    var saldo: Double
        get() = _saldo
        set(valor) {
            if (valor < 0) throw SaldoNegativoException()
            _saldo = valor
        }
}

class SaldoNegativoException(mensagem: String): Exception(mensagem) {
    constructor(): this("Saldo negativo")
}

fun main() {

    val conta1 = Conta(100.0)
    try {
        print("Entra com o novo valor da conta: ")
        conta1.saldo = readln().toDouble()
    } catch(e: SaldoNegativoException) {
        println("Saldo negativo. Valor não atualizado.")
    } catch(e: NumberFormatException) {
        println("Número inválido")
    } catch(e: Exception) {
        println("Erro desconhecido")
    } finally {
        println("Saldo atual: ${conta1.saldo}")
    }
}