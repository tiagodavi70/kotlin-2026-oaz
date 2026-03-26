import java.io.File

fun main() {

    val ficheiro1 = File("ficheiro.txt")
    ficheiro1.writeText("Olá mundo!")
    ficheiro1.appendText("\nOlá mundo!")
    val textoLido = ficheiro1.readText()
    val linhas: List<String> = ficheiro1.readLines()

    println(linhas)
    val ficheiro2 = File("ficheiro2.txt")
    if (ficheiro2.exists())
        ficheiro2.readLines()
    else {
        println("Não existe.")
    }
}