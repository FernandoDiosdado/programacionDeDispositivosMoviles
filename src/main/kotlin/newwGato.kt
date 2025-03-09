//package mx.upiita
import kotlin.random.Random

fun main(args: Array<String>) {
    val posiciones: Array<String?> = arrayOfNulls(9)
    val letraJugador = preguntarLetra()
    val letraMaquina = determinarLetraMaquina(letraJugador)
    val turno = asignarTurno()
    val jugadorPersona = Pair(letraJugador, turno.first)
    val jugadorMaquina = Pair(letraMaquina, turno.second)

    println(imprimirTablero(posiciones))
    jugar(jugadorPersona, jugadorMaquina, posiciones)
}

fun imprimirTablero(posiciones: Array<String?>) {
    for (i in 0..2) {
        for (j in 0..2) {
            when (posiciones[i * 3 + j]) {
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
        eleccion = readLine()?.uppercase()
    } while (eleccion != "X" && eleccion != "O")

    return eleccion
}

fun determinarLetraMaquina(lJugador: String?): String {
    return if (lJugador == "X") "O" else "X"
}

fun asignarTurno(): Pair<Int, Int> {
    val t = Random.nextInt(0, 2)
    return if (t == 1) Pair(t, 0) else Pair(t, 1)
}

// Función de orden superior que ejecuta la jugada correspondiente
fun realizarJugada(
    jugador: Pair<String, Int>,
    posiciones: Array<String?>,
    funcionMovimiento: (Pair<String, Int>, Array<String?>) -> Unit
) {
    funcionMovimiento(jugador, posiciones)
}

// Función para jugar
fun jugar(jugadorPersona: Pair<String, Int>, jugadorMaquina: Pair<String, Int>, posiciones: Array<String?>): Int {
    if (posiciones.contains(null)) {
        val primerJugador = if (jugadorPersona.second == 0) jugadorPersona else jugadorMaquina
        val segundoJugador = if (jugadorPersona.second == 1) jugadorPersona else jugadorMaquina

        val funcionMovimientoPrimerJugador = if (primerJugador == jugadorPersona) ::moverPersona else ::moverMaquina
        val funcionMovimientoSegundoJugador = if (segundoJugador == jugadorPersona) ::moverPersona else ::moverMaquina

        // Juega el primer jugador
        realizarJugada(primerJugador, posiciones, funcionMovimientoPrimerJugador)
        println(imprimirTablero(posiciones))

        // Verificar si el primer jugador ganó
        if (verificarGanador(posiciones) != null) {
            println("¡El jugador ${verificarGanador(posiciones)} ha ganado!")
            return 0
        }

        // Si aún hay espacios disponibles, juega el segundo jugador
        if (posiciones.contains(null)) {
            realizarJugada(segundoJugador, posiciones, funcionMovimientoSegundoJugador)
            println(imprimirTablero(posiciones))

            // Verificar si el segundo jugador ganó
            if (verificarGanador(posiciones) != null) {
                println("¡El jugador ${verificarGanador(posiciones)} ha ganado!")
                return 0
            }
        }

        return jugar(jugadorPersona, jugadorMaquina, posiciones) // Recursión sin alterar los turnos
    } else {
        println("¡Es un empate!")
        return 0
    }
}

// Movimiento del jugador humano
fun moverPersona(jugador: Pair<String, Int>, posiciones: Array<String?>) {
    var casilla: Int?
    do {
        println("Escoge una casilla para jugar (1-9):")
        casilla = readLine()?.toIntOrNull()
    } while (casilla == null || casilla !in 1..9 || posiciones[casilla - 1] != null)

    posiciones[casilla - 1] = jugador.first
}

// Movimiento de la máquina (elige aleatoriamente una casilla vacía)
fun moverMaquina(jugador: Pair<String, Int>, posiciones: Array<String?>) {
    val maquina = jugador.first
    val humano = if (maquina == "X") "O" else "X"

    // 1️⃣ Revisar si la máquina puede ganar en la siguiente jugada
    val casillaGanadora = encontrarCasillaGanadora(maquina, posiciones)
    if (casillaGanadora != -1) {
        posiciones[casillaGanadora] = maquina
        println("La máquina ha elegido la casilla ${casillaGanadora + 1} para ganar.")
        return
    }

    // 2️⃣ Revisar si el oponente puede ganar y bloquearlo
    val casillaParaBloquear = encontrarCasillaGanadora(humano, posiciones)
    if (casillaParaBloquear != -1) {
        posiciones[casillaParaBloquear] = maquina
        println("La máquina ha elegido la casilla ${casillaParaBloquear + 1} para bloquear al oponente.")
        return
    }

    // 3️⃣ Elegir una esquina disponible
    val esquinas = listOf(0, 2, 6, 8).filter { posiciones[it] == null }
    if (esquinas.isNotEmpty()) {
        val casillaSeleccionada = esquinas.random()
        posiciones[casillaSeleccionada] = maquina
        println("La máquina ha elegido la esquina ${casillaSeleccionada + 1}.")
        return
    }

    // 4️⃣ Si el centro está libre, tomarlo
    if (posiciones[4] == null) {
        posiciones[4] = maquina
        println("La máquina ha elegido el centro.")
        return
    }

    // 5️⃣ Jugar en la cruz (posiciones 1, 3, 5, 7)
    val cruz = listOf(1, 3, 5, 7).filter { posiciones[it] == null }
    if (cruz.isNotEmpty()) {
        val casillaSeleccionada = cruz.random()
        posiciones[casillaSeleccionada] = maquina
        println("La máquina ha elegido la cruz en la casilla ${casillaSeleccionada + 1}.")
        return
    }
}

// Función auxiliar para encontrar una casilla que haga ganar o bloquee al oponente
fun encontrarCasillaGanadora(jugador: String, posiciones: Array<String?>): Int {
    val combinacionesGanadoras = arrayOf(
        intArrayOf(0, 1, 2), intArrayOf(3, 4, 5), intArrayOf(6, 7, 8), // Filas
        intArrayOf(0, 3, 6), intArrayOf(1, 4, 7), intArrayOf(2, 5, 8), // Columnas
        intArrayOf(0, 4, 8), intArrayOf(2, 4, 6)  // Diagonales
    )

    for (combo in combinacionesGanadoras) {
        val (a, b, c) = combo
        val valores = listOf(posiciones[a], posiciones[b], posiciones[c])

        // Si hay dos marcas del jugador y una casilla vacía, devolver la vacía
        if (valores.count { it == jugador } == 2 && valores.count { it == null } == 1) {
            return combo[valores.indexOf(null)]
        }
    }
    return -1 // No hay jugada ganadora ni bloqueo necesario
}


// Función para verificar si hay un ganador
fun verificarGanador(posiciones: Array<String?>): String? {
    val combinacionesGanadoras = arrayOf(
        intArrayOf(0, 1, 2), intArrayOf(3, 4, 5), intArrayOf(6, 7, 8), // Filas
        intArrayOf(0, 3, 6), intArrayOf(1, 4, 7), intArrayOf(2, 5, 8), // Columnas
        intArrayOf(0, 4, 8), intArrayOf(2, 4, 6)  // Diagonales
    )

    for (combo in combinacionesGanadoras) {
        val (a, b, c) = combo
        if (posiciones[a] != null && posiciones[a] == posiciones[b] && posiciones[b] == posiciones[c]) {
            return posiciones[a] // Retorna "X" o "O" como ganador
        }
    }
    return null
}