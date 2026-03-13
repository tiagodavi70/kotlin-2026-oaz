package Q2

class Garrafa(val id: String, val material: String, val reciclavel: Boolean) {

    override fun toString(): String {
        return "$id | $material | $reciclavel"
    }
}