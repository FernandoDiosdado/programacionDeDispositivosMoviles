package mx.upiita
import mx.upiita.holakotlin.Persona
/*import kotlin.random.Random

fun main(args: Array<String>){
    var posiciones: Array<String?> = arrayOfNulls(9)
    var letraJugador = preguntarLetra()
    var letraMaquina = determinarLetraMaquina(letraJugador)
    var turno = asignarTurno()
    var jugadorPersona = Pair(letraJugador, turno.first)
    var jugadorMaquina = Pair(letraMaquina, turno.second)
    println(imprimirTablero(posiciones))
    jugar(jugadorPersona, jugadorMaquina, posiciones)
}

fun imprimirTablero(posiciones: Array<String?>) {
    for (i in 0 .. 2) {
        for (j in 0 .. 2) {
            when (posiciones[i*3 + j]) {
                "X" -> print(" X ")
                "O" -> print(" O ")
                else -> print("   ")
            }
            if (j < 2) print("|")
        }
        println()
        if (i < 2) println("---+---+---")
    }
}

fun preguntarLetra(): String {
    var eleccion: String?

    do {
        println("¿Cuál letra prefieres? X o O")
        eleccion = readLine()?.uppercase() // Convertimos a mayúsculas para evitar errores
    } while (eleccion != "X" && eleccion != "O") // Repite hasta que sea X o O

    return if (eleccion == "X") "X" else "O"
}

fun determinarLetraMaquina(lJugador: String?): String {
    return if (lJugador == "X") "O" else "X"
}

fun asignarTurno(): Pair<Int,Int> {
    val t = Random.nextInt(0,2)
    return if(t == 1) Pair(t,0) else Pair(t,1)
}

fun jugar(jugadorPersona: Pair<String, Int>,
            jugadorMaquina: Pair<String, Int>,
            posiciones: Array<String?>): Int {

    if (jugadorPersona.second == 0) {

    } else {

    }

}*/