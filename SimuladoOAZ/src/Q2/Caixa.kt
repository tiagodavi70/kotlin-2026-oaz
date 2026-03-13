package Q2

class Caixa(val material: String, val largura: Double, val altura: Double) {

    override fun toString(): String {
        return "$material | $largura | $altura"
    }
}