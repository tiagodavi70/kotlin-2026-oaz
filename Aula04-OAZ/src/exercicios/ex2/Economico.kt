package exercicios.ex2

class Economico(modelo: String, litrosTanque: Double, litrosKm: Double): Automovel(modelo, litrosTanque, litrosKm) {

    override fun consumirTanque(litros: Double) {
        require(litrosTanque - (litros * 0.97) > 0) { "Não tem combustível o suficiente." }
        litrosTanque -= litros * 0.97
    }
}