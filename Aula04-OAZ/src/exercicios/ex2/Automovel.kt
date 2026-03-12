package exercicios.ex2

open class Automovel(
    val modelo: String,
    var litrosTanque: Double, // litros que ainda estão no tanque
    var litrosKm: Double // consumo de litros por km
) {
    // Dado uma distância em km, calcula os litros utilizados
    fun litrosUtilizados(km: Double): Double {
        val litros = km * this.litrosKm
        consumirTanque(litros)
        return litros
    }

    // Consome a gasolina do tanque
    open fun consumirTanque(litros: Double) {
        require(litrosTanque - litros > 0) { "Não tem combustível o suficiente." }
        litrosTanque -= litros
    }
}