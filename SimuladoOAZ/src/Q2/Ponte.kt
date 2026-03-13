package Q2

class Ponte(val nome: String, val custo: Double, val manutencao: Int) {

    override fun toString(): String {
        return "$nome | $custo | $manutencao"
    }
}