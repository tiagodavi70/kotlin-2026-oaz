package colaborador

class Colaborador(private val nome:String,
                  cargo: String,
                  precoHora: Double): Pagamento {

    private var _cargo: String
    private var _precoHora: Double
    private val _historicoCargo: MutableList<String> = emptyList<String>().toMutableList()

    var cargo: String
        get() = _cargo
        set(value) {
            println("Trocou de cargo: $_cargo para $value")
            _historicoCargo.add(_cargo)
            _cargo = value
        }

    val historicoCargo: List<String>
        get() = _historicoCargo.toList()

    val precoHora: Double
        get() {
            return _precoHora
        }

    fun trocarPreco(autorizacao: Colaborador, novoPreco: Double) {
        require(precoHora > 5.75) { "Valor abaixo do permitido legalmente." }
        println("Colaborador: ${autorizacao.nome} alterou preço da hora")
        _precoHora = novoPreco
    }

    init {
        require(precoHora > 5.75) { "Valor abaixo do permitido legalmente." }

        this._cargo = cargo
        this._precoHora = precoHora
    }

    override fun pagamentoMensal(): Double {
        return precoHora * 40 * 4
    }

    override fun exibirPagamento(): String {
        return "Colaborador deve receber: ${pagamentoMensal()}"
    }

}