
fun main() {

    for (i in 1..10) {
        print("$i ")
    }
    println()

    for (i in 1..10 step 2) {
        print("$i ")
    }
    println()

    for (i in 1..< 10) {
        print("$i ")
    }
    println()

    for (i in 10 downTo 1) {
        print("$i ")
    }
    println()

    var i:Int = 1
    while (i <= 10) {
        print("$i ")
        i = i + 1 // i += 1 // i++
    }

}