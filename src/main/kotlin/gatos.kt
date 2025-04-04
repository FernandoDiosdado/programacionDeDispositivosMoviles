import java.util.Scanner

fun main() {
    val tablero = Array(3) { CharArray(3) { ' ' } }
    var jugadaAct:Char
    val lector = Scanner(System.`in`)
    var opc:String
    do { // La rejugabilidad
        val rnds = (0..1).random()
        jugadaAct = if(rnds==0) 'X' else 'O' // Quien comienza
        limpiarTab(tablero)
        while (true) { // Las jugadas turno por turno
            mostrarTab(tablero)
            if (jugadaAct == 'X') {
                jugadorMov(tablero,jugadaAct)
            } else {
                IAMov(tablero, jugadaAct)
            }

            if (tabGanado(tablero, jugadaAct)) { // ¿El juego ha acabado?: Alguien ya ganó
                mostrarTab(tablero)
                println("¡El jugador $jugadaAct ha ganado!")
                break
            }

            if (tabLleno(tablero)) { // ¿El juego ha acabado?: Tablero lleno
                mostrarTab(tablero)
                println("¡Es un empate!")
                break
            }

            jugadaAct = if (jugadaAct == 'X') 'O' else 'X' // Siguiente turno
        }
        println("Desea continuar? (s/n)")
        opc=lector.nextLine()
    }while(opc == "s")
}

// Muestra el tablero
fun mostrarTab(tablero: Array<CharArray>) {
    println("  0 1 2")
    for (i in tablero.indices) {
        print("$i ")
        println(tablero[i].joinToString("|"))
        if (i < tablero.size - 1) println("  -----")
    }
    println()
}

// Realiza la tirada del jugador
fun jugadorMov(tablero: Array<CharArray>, tiro:Char) {
    while (true) {
        println("Tu turno ($tiro). Ingresa fila y columna (0-2) separadas por espacio:")
        // Tiro del jugador, formato: Int Int
        val ent = readlnOrNull()?.split(" ")?.mapNotNull { it.toIntOrNull() }
        if (ent != null && ent.size == 2) {
            val (row, col) = ent
            if (row in 0..2 && col in 0..2 && tablero[row][col] == ' ') {
                tablero[row][col] = tiro
                break
            }
        }
        println("Movimiento inválido. Inténtalo de nuevo.")
    }
}

// Devuelve el tiro opuesto al enviado ("X" o "O")
fun tiroOp(tiro:Char): Char{
    if (tiro == 'X') {
        return 'O'
    }
    else return 'X'
}

// Realiza la jugada de la IA
fun IAMov(tablero: Array<CharArray>, tiro:Char) {
    println("Turno de la computadora ($tiro)...")

    // 1. Intentar ganar en el siguiente movimiento
    val movGanador = mejorJugada(tablero, tiro)
    if (movGanador != null) {
        tablero[movGanador.first][movGanador.second] = tiro
        return
    }

    // 2. Evita que el jugador gane (bloquea la linea)
    val jugadaB = mejorJugada(tablero, tiroOp(tiro))
    if (jugadaB != null) {
        tablero[jugadaB.first][jugadaB.second] = tiro
        return
    }

    // 3. Si no hay jugadas ganadoras o de bloqueo, juega en el centro si está disponible
    if (tablero[1][1] == ' ') {
        tablero[1][1] = tiro
        return
    }

    // 4. Juega en una esquina si está libre
    val esquinas = listOf(Pair(0, 0), Pair(0, 2), Pair(2, 0), Pair(2, 2))
    val esquinaDisp = esquinas.filter { tablero[it.first][it.second] == ' ' }
    if (esquinaDisp.isNotEmpty()) {
        val (row, col) = esquinaDisp.random()
        tablero[row][col] = tiro
        return
    }

    // 5. Juega en un espacio aleatorio si no hay mejores opciones
    val celdaVacia = mutableListOf<Pair<Int, Int>>()
    for (i in tablero.indices) {
        for (j in tablero[i].indices) {
            if (tablero[i][j] == ' ') celdaVacia.add(Pair(i, j)) // Guarda todos los lugares vacíos
        }
    }
    if (celdaVacia.isNotEmpty()) {
        val (row, col) = celdaVacia.random()
        tablero[row][col] = tiro // Tira en uno de los lugares vac+ios
    }
}

// Busca si hay un movimiento ganador
fun mejorJugada(tablero: Array<CharArray>, tiro: Char): Pair<Int, Int>? {
    for (i in tablero.indices) {
        for (j in tablero[i].indices) {
            if (tablero[i][j] == ' ') {
                tablero[i][j] = tiro
                if (tabGanado(tablero, tiro)) {
                    tablero[i][j] = ' ' // Revertir el tiro
                    return Pair(i, j) //  Regresa la jugada
                }
                tablero[i][j] = ' ' // Revertir el tiro
            }
        }
    }
    return null
}

// Verifica si alguien ya ganó
fun tabGanado(tablero: Array<CharArray>, tiro: Char): Boolean {
    // Revisa las filas y columnas
    for (i in tablero.indices) {
        if (tablero[i].all { it == tiro } || tablero.all { it[i] == tiro }) return true
    }
    // Revisa las diagonales
    if ((tablero[0][0] == tiro && tablero[1][1] == tiro && tablero[2][2] == tiro) ||
        (tablero[0][2] == tiro && tablero[1][1] == tiro && tablero[2][0] == tiro)) return true
    return false
}

// Llena el tablero de espacios (lo limpia)
fun limpiarTab(tablero: Array<CharArray>) {
    for (i in tablero.indices) {
        for (j in tablero[i].indices) {
            tablero[i][j]=' '
        }
    }

}

// Verifica si el tablero ya está lleno
fun tabLleno(tablero: Array<CharArray>): Boolean {
    return tablero.all { row -> row.all { it != ' ' } }
}