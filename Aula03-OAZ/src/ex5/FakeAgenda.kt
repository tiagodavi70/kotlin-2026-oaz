package ex5

fun main() {
    val listaContactos = emptyList<Contacto>().toMutableList()

    val contacto1 = Contacto("A", "a@a", "9999")
    val contacto2 = Contacto("B", "b@b", "888")
    val contacto3 = Contacto("C", "c@c", "777")

    adicionar(listaContactos, contacto1)
    adicionar(listaContactos, contacto2)
    adicionar(listaContactos, contacto3)

    remover(listaContactos, contacto3)

    listar(listaContactos)

    val encontrado = procurar(listaContactos, "B")

    if (encontrado != null) {
        println("Encontrado")
    } else {
        println("Não encontrado")
    }
}

fun adicionar(lista: MutableList<Contacto>, contacto: Contacto) {
    lista.add(contacto)
}

fun remover(lista: MutableList<Contacto>, contacto: Contacto) {
    lista.remove(contacto)
}

fun listar(lista: MutableList<Contacto>) {
    for (item in lista) {
        println("${item.nome} ${item.telefone} ${item.email}")
    }
}

fun procurar(lista: MutableList<Contacto>, termo: String): Contacto? {
    var contacto: Contacto? = null

    for (item in lista) {
        if (termo == item.nome || termo == item.email || termo == item.telefone) {
            contacto = item
        }
    }

    return contacto
}