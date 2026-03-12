package colaborador

fun main() {
    val colaborador1 = Colaborador("a", "b", 7.0)
    val freelancer1 = Freelancer("c", 300.0)

    println(colaborador1.cargo)
    println(colaborador1.precoHora)

    colaborador1.cargo = "Novo cargo" // estou chamando o set do cargo
    println(colaborador1.historicoCargo)
    colaborador1.trocarPreco(colaborador1, 8.0)

    colaborador1.pagamentoMensal()
    freelancer1.pagamentoMensal()

    val pagamentos = listOf<Pagamento>(colaborador1, freelancer1)

    var total: Double = 0.0
    for (pagamento in pagamentos) {
        total += pagamento.pagamentoMensal()
        println(pagamento.exibirPagamento())
    }
    println("Total: $total")
}