package colaborador

class Freelancer(private val nome: String, precoSemana: Double): Pagamento {

    private var _precoSemana: Double = precoSemana
    private val precoSemana: Double
        get() = _precoSemana

    override fun pagamentoMensal(): Double {
        return precoSemana * 4
    }

    override fun exibirPagamento(): String {
        return "O freelancer deve receber: ${pagamentoMensal()}"
    }
}