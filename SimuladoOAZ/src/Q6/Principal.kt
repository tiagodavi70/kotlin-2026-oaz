package Q6

fun main() {
    val livro1 = LivroDigital("a", "aa", 30.0, 100)
    val livro2 = LivroDigital("b", "bb", 40.0, 200)
    val livro3 = LivroDigital("c", "cc", 50.0, 300)

    val filme1 = FilmeDigital("a", "aa", 3.0, 120)
    val filme2 = FilmeDigital("b", "bb", 4.0, 90)
    val filme3 = FilmeDigital("c", "cc", 5.0, 100)

    val musica1 = MusicaDigital("a", "aa", 30.0, "aaa")
    val musica2 = MusicaDigital("b", "bb", 40.0, "bbb")
    val musica3 = MusicaDigital("c", "cc", 50.0, "ccc")

    print("Entra com o titulo do livro: ")
    val titulo: String = readln()
    print("Entra com o gênero do livro: ")
    val genero: String = readln()
    print("Entra com o preço do livro: ")
    val preco: Double = readln().toDouble()
    print("Entra com o número de páginas do livro: ")
    val numPaginas: Int = readln().toInt()

    val livro4 = LivroDigital(titulo, genero, preco, numPaginas)

    val livros = listOf(livro1, livro2, livro3, livro4)
    val midias = listOf(livro1, livro2, livro3, livro4, musica1, musica2, musica3, filme1, filme2, filme3)

    println(menorPreco(midias).titulo)
    println(maiorPaginas(livros).titulo)
}

fun menorPreco(listaMidias: List<Midia>): Midia {
    var menorMidia: Midia = listaMidias[0]
    for (midia in listaMidias) {
        if (midia.preco < menorMidia.preco) {
            menorMidia = midia
        }
    }
    var soma: Double = 0.0
    for (midia in listaMidias) {
        soma += midia.preco
    }
    println("Média: ${soma/listaMidias.size}")
    return menorMidia
}

fun maiorPaginas(listaLivros: List<LivroDigital>): LivroDigital {
    var maiorPaginas: LivroDigital = listaLivros[0]
    for (livro in listaLivros) {
        if (livro.numPaginas > maiorPaginas.numPaginas) {
            maiorPaginas = livro
        }
    }
    return maiorPaginas
}