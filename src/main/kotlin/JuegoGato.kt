package mx.upiita.holakotlin
import kotlin.random.Random

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

/*fun jugar (jugadorPersona: Pair<String,Int>,
                 jugadorMaquina: Pair<String,Int>,
                 posiciones: Array<String?>): Int{

    if(posiciones.contains(null)){
        if (jugadorPersona.second == 0){
            moverPieza(jugadorPersona, posiciones, )
            //moverPieza(jugadorMaquina, posiciones)
            println(imprimirTablero(posiciones))
            return jugar(jugadorPersona, jugadorMaquina, posiciones)
        }else{
            //moverPieza(jugadorMaquina, posiciones)
            moverPieza(jugadorPersona, posiciones)
            println(imprimirTablero(posiciones))
            return jugar(jugadorPersona, jugadorMaquina, posiciones)
        }

    }else{
        return 0
    }

}

fun moverPieza(jugador: Pair<String,Int>,posiciones: Array<String?>): Array<String?> {

    return posiciones
}

fun moverPersona(jugadorPersona: Pair<String, Int>, posiciones: Array<String?>){
    println("Escoge una casilla para jugar. Puedes escoger de 1 a 9.")
    val casilla = readLine()?.toIntOrNull()

    if (casilla == null || casilla !in 1..9) {
        println("Por favor, ingresa un número válido entre 1 y 9.")
    } else {
        posiciones[casilla - 1] = jugadorPersona.first
    }
}*/

fun jugar(jugadorPersona: Pair<String, Int>,
          jugadorMaquina: Pair<String, Int>,
          posiciones: Array<String?>): Int {

    if (posiciones.contains(null)) {
        // Usamos una función de orden superior que recibe una función para mover la pieza
        if (jugadorPersona.second == 0) {
            moverPieza(jugadorPersona, posiciones) { jugador, posiciones -> moverPersona(jugador, posiciones) }
            // Aquí puedes hacer lo mismo para la máquina si deseas, por ejemplo:
            // moverPieza(jugadorMaquina, posiciones) { jugador, posiciones -> moverMaquina(jugador, posiciones) }
            println(imprimirTablero(posiciones))
            if (verificarGanador(posiciones) != null){
                return 0
            }
            return jugar(jugadorPersona, jugadorMaquina, posiciones)  // Recursión
        } else {
            moverPieza(jugadorPersona, posiciones) { jugador, posiciones -> moverPersona(jugador, posiciones) }
            println(imprimirTablero(posiciones))
            if (verificarGanador(posiciones) != null){
                return 0
            }
            return jugar(jugadorPersona, jugadorMaquina, posiciones)  // Recursión
        }
    } else {
        return 0  // El juego terminó porque no hay más casillas vacías
    }
}

/*fun jugar(jugadorPersona: Pair<String, Int>,
          jugadorMaquina: Pair<String, Int>,
          posiciones: Array<String?>): Int {

    // Usamos una función que maneja el movimiento y la recursión
    return realizarMovimiento(jugadorPersona, posiciones) ?: realizarMovimiento(jugadorMaquina, posiciones) ?: 0
}*/

fun realizarMovimiento(jugador: Pair<String, Int>, posiciones: Array<String?>): Int? {
    moverPieza(jugador, posiciones) { jugador, posiciones -> moverPersona(jugador, posiciones) }
    println(imprimirTablero(posiciones))

    // Verificamos si hay un ganador después de mover
    if (verificarGanador(posiciones) != null) {
        return 0  // El juego terminó porque hay un ganador
    }

    // Continuamos el juego de forma recursiva
    return if (posiciones.contains(null)) jugar(jugador, jugador, posiciones) else null
}


// Función de orden superior que toma una función de movimiento como parámetro
fun moverPieza(jugador: Pair<String, Int>, posiciones: Array<String?>, mover: (Pair<String, Int>, Array<String?>) -> Unit): Array<String?> {
    mover(jugador, posiciones)  // Llamada a la función de movimiento proporcionada como parámetro
    return posiciones
}

// Función para mover a la persona
fun moverPersona(jugadorPersona: Pair<String, Int>, posiciones: Array<String?>) {
    println("Escoge una casilla para jugar. Puedes escoger de 1 a 9.")
    val casilla = readLine()?.toIntOrNull()

    if (casilla == null || casilla !in 1..9) {
        println("Por favor, ingresa un número válido entre 1 y 9.")
    } else {
        posiciones[casilla - 1] = jugadorPersona.first
        println(posiciones[casilla - 1])
    }
}

fun verificarGanador(tablero: Array<String?>): String? {
    // Comprobamos las filas
    for (i in 0..2) {
        if (tablero[i * 3] != null && tablero[i * 3] == tablero[i * 3 + 1] && tablero[i * 3] == tablero[i * 3 + 2]) {
            return tablero[i * 3]  // Si las tres casillas son iguales, retornamos el valor
        }
    }

    // Comprobamos las columnas
    for (i in 0..2) {
        if (tablero[i] != null && tablero[i] == tablero[i + 3] && tablero[i] == tablero[i + 6]) {
            return tablero[i]  // Si las tres casillas son iguales, retornamos el valor
        }
    }

    // Comprobamos las diagonales
    if (tablero[0] != null && tablero[0] == tablero[4] && tablero[0] == tablero[8]) {
        return tablero[0]  // Diagonal principal
    }

    if (tablero[2] != null && tablero[2] == tablero[4] && tablero[2] == tablero[6]) {
        return tablero[2]  // Diagonal secundaria
    }

    // Si no hay ganador, retornamos null
    return null
}
// Aquí podrías agregar un comportamiento similar para la máquina si es necesario
// fun moverMaquina(jugadorMaquina: Pair<String, Int>, posiciones: Array<String?>) { ... }

