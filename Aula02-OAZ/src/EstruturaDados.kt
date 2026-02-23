

fun main() {
    val lista1 = listOf(1, 2, 3)
    val lista2 = mutableListOf(1, 2, 3)
    val lista3 = MutableList<Int>(0) { 0 }
    lista2.add(4)

    println(lista1)
    println(lista2)
    println(lista3)

    for (i in 0..<lista2.size) {
        print("${lista2[i]} ")
    }
    println()

    lista2.remove(2)
    lista2.removeAt(0)

    println(lista2)
    lista2.add(1)
    lista2.add(2)
    lista2.add(3)
    lista2.add(4)
    println(lista2)

    val sublista = lista2.subList(3, 5)
    println(sublista)

    val vetor1 = arrayOf(1,2,3)
    val lista4 = vetor1.toList()
    val lista5 = lista2.toList()
    val lista6 = lista1.toMutableList()

    val lista7 = lista2.slice(0..<lista2.size) // volta referência
    val lista8 = lista2.slice(listOf(0, lista2.size-1)) // volta cópia

    // {"primeiro": 1, "segundo": 2}
    val map1 = mapOf("primeiro" to 1, "segundo" to 2)
    val map2 = mutableMapOf("primeiro" to 1)
    val map3 = mutableMapOf(1 to 2) // aceita qualquer coisa como chave
    map2["segundo"] = 2
    println(map2)
    println(map3)

    val set1 = setOf(1,2,3)
    val set2 = mutableSetOf(1,2,4,4)

    println(set2)
}