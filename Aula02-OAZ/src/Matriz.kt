
fun main() {
    val matriz = Array(3) {Array(3) {0} }

    for (i in 0..<matriz.size){
        for (j in 0..<matriz[i].size) {
            print("${matriz[i][j]} ")
        }
        println()
    }
}