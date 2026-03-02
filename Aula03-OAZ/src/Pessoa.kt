class Pessoa(nome: String, nif: String, val telemovel: String) {

    val nome: String
    val nif: String

    init {
        check(nome.isNotBlank()) { "Nome não pode ser vazio." }
        require(nif.length == 9) { "NIF inválido." }

        this.nome = nome
        this.nif = nif
    }
}
