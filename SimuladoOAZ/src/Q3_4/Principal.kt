package Q3_4

fun main() {

    val conta1 = ContaBancaria("a", 100.0)
    val conta2 = ContaBancaria("b", 200.0)
    val conta3 = ContaBancaria("c", 300.0)
    val conta4 = ContaBancaria("d", 400.0)
    val conta5 = ContaBancaria("e", 500.0)

    val contas = listOf(conta1, conta2, conta3, conta4, conta5)

    print("Entra com o codigo da conta: ")
    val codigo: String = readln()

    print("Entra com o tipo de operação (d - Depósito | r - Retirada): ")
    val operacao: String = readln()

    print("Indique o valor: ")
    val valor: Double = readln().toDouble()

    for (conta in contas) {
        if (conta.id == codigo) {
            println("Valor: ${conta.saldo()}")
            if (operacao == "d") {
                conta.deposito(valor)
            } else {
                try {
                    conta.retirada(valor)
                } catch (e: IllegalArgumentException) {
                    println("Saldo insuficiente")
                }
            }
            println("Valor: ${conta.saldo()}")
        }
    }
}