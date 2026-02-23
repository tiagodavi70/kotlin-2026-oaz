
fun main() {
    println("Olá mundo!")

    // val nome:String = "Tiago"
    // val idade:Int = 33

    print("Entra com o nome: ")
    val nome:String = readln()
    print("Entra com a idade: ")
    val idade:Int= readln().toInt()
    val altura:Double = readln().toDouble()

    println(nome)
    println("$nome tem $idade anos")
    println("$nome ano que vem vai ter ${idade  + 1} anos")
}