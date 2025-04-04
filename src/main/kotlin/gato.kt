package mx.upiita.holakotlin
import kotlin.random.Random

fun main(args: Array<String>) {

    do {
        val posiciones: Array<String?> = arrayOfNulls(9) // crear posiciones del tablero
        val letraJugador = preguntarLetra() // obtener la letra del jugador persona
        val letraMaquina = determinarLetraMaquina(letraJugador) // obtener letra del jugador maquina
        val turno = asignarTurno() // asignar turno a los jugadores de forma aleatoria
        val jugadorPersona = Pair(letraJugador, turno.first) // variable del jugador persona
        val jugadorMaquina = Pair(letraMaquina, turno.second) // variable del jugador maquina

        println(imprimirTablero(posiciones)) // imprimir tablero vacio
        jugar(jugadorPersona, jugadorMaquina, posiciones) // iniciar el juego

        println("¿Volver a jugar? si/no")
        val respuesta = readLine()?.uppercase()

    }while(respuesta == "SI")
}

fun imprimirTablero(posiciones: Array<String?>) { // imprime tablero
    for (i in 0..2) {
        for (j in 0..2) {
            when (posiciones[i * 3 + j]) { // obtener la letra en la posicion correspondiente
                "X" -> print(" X ")
                "O" -> print(" O ")
                else -> print("   ") // si no hay letra asignada se imprime espacio
            }
            if (j < 2) print("|")
        }
        println()
        if (i < 2) println("---+---+---")
    }
}

fun preguntarLetra(): String { // pregunta la letra del jugador persona
    var eleccion: String?

    do {
        println("¿Cuál letra prefieres? X o O")
        eleccion = readLine()?.uppercase() // leer letra del jugador persona y hacerla simpre mayuscula
    } while (eleccion != "X" && eleccion != "O")

    return eleccion
}

fun determinarLetraMaquina(lJugador: String?): String { // determinar letra del jugador maquina
    return if (lJugador == "X") "O" else "X"
}

fun asignarTurno(): Pair<Int, Int> { // determinar turno de los jugadores de forma aleatoria
    val t = Random.nextInt(0, 2) // genera un numero aleatorio entre 1 y 0
    return if (t == 1) Pair(t, 0) else Pair(t, 1)
}

fun jugar(jugadorPersona: Pair<String, Int>, // funcion recursiva donde se ejecuta principalemente el juego
          jugadorMaquina: Pair<String, Int>, posiciones:
          Array<String?>): Int{
    if(posiciones.contains(null)){
        val primerJugador = if(jugadorPersona.second == 0) jugadorPersona else jugadorMaquina // determina el primer jugadador es escoger
        val segundoJugador = if(jugadorPersona.second == 1) jugadorPersona else jugadorMaquina // determina el segundo jugadador es escoger
        val primeraJugada = if(primerJugador == jugadorPersona) :: jugarPersona else :: jugarMaquina // determina el jugador y el tipo de jugada
        val segundaJugada = if(segundoJugador == jugadorPersona) :: jugarPersona else :: jugarMaquina // determina el jugador y el tipo de jugada

        realizarJugada(primerJugador, posiciones, primeraJugada) // hace la primera jugada
        println(imprimirTablero(posiciones)) // imprime tablero

        // verifica si hay un ganador
        if (verificarGanador(posiciones) != null) {
            println("Ganador: ${verificarGanador(posiciones)}")
            return 0 // termina la funcion
        }

        // si todavia hay casillas vacias
        if (posiciones.contains(null)) {
            realizarJugada(segundoJugador, posiciones, segundaJugada) // hace la segunda jugada
            println(imprimirTablero(posiciones)) // imprime tablero

            // verifica si hay ganador
            if (verificarGanador(posiciones) != null) {
                println("Ganador: ${verificarGanador(posiciones)}")
                return 0 // termina la funcion
            }
        }
        return jugar(jugadorPersona, jugadorMaquina, posiciones) // recursividad, siguiente jugada
    }else{
        println("Empate")
        return 0 // termina la funcion
    }
}

fun realizarJugada(jugador: Pair<String, Int>, // funcion de orden superior para ejecutar jugadas
                   posiciones: Array<String?>,
                   tipoJugada: (Pair<String, Int>, Array<String?>) -> Unit) {
                       tipoJugada(jugador, posiciones) // se manda al tipo de jugada el jugador y las posiciones
                        /*
                        jugador persona y jugador maquina no pueden utilizar la misma funcion para jugar,
                        por eso el recurso de la funcion de orden superior
                        * */

}

fun jugarPersona(jugadorPersona: Pair<String, Int>, // jugador persona elige su jugada
                 posiciones: Array<String?>){
    var casilla: Int?
    do{
        println("Escoge una casilla (1-9)")
        casilla = readLine()?.toIntOrNull() // si se ingresa un valor no permitido lo hace null
    }while(casilla == null || casilla !in 1 .. 9 || posiciones[casilla- 1 ] != null)
    /*
    * si no se cumple algunas de las condiciones anteriores se repite la eleccion
    * */
    posiciones[casilla - 1] = jugadorPersona.first // asgina letra a la posicion en el arrray
}

// movimiento de la maquina
fun jugarMaquina(jugador: Pair<String, Int>, posiciones: Array<String?>) {
    val maquina = jugador.first // letra de la maquina
    val persona = if (maquina == "X") "O" else "X" // letra de la persona

    // revisar si la maquina puede ganar en la siguiente jugada
    val casillaGanadora = buscarCasillaGanadora(maquina, posiciones)
    if (casillaGanadora != -1) { // si el return es diferente de -1 entonces ejecutar la jugada ganadora
        posiciones[casillaGanadora] = maquina
        return // termina la funcion
    }

    // revisar si persona puede ganar y bloquearlo
    val casillaParaBloquear = buscarCasillaGanadora(persona, posiciones)
    if (casillaParaBloquear != -1) { // si el return es diferente de -1 entonces ejecutar la jugada bloquedora
        posiciones[casillaParaBloquear] = maquina
        return // termina la funcion
    }

    // elegir una esquina disponible
    val esquinas = listOf(0, 2, 6, 8).filter { posiciones[it] == null } // busca cuales de las esquinas esta disponible
    if (esquinas.isNotEmpty()) { // si hay alguna disponible
        val casillaSeleccionada = esquinas.random() // escoger alguna de las esquinas de forma aleatoria
        posiciones[casillaSeleccionada] = maquina // ejecutar la jugada
        return // termina la funcion
    }

    // si el centro esta libre tomar esa casilla
    if (posiciones[4] == null) {
        posiciones[4] = maquina
        return // termina la funcion
    }

    // jugar en algun lado
    val cruz = listOf(1, 3, 5, 7).filter { posiciones[it] == null } // busca cuales posiciones laterales están disponibles
    if (cruz.isNotEmpty()) { // si hay alguna disponible
        val casillaSeleccionada = cruz.random() // escoger alguna de forma aleatoria
        posiciones[casillaSeleccionada] = maquina // ejecutar la jugada
        return // termina la funcion
    }
}

fun buscarCasillaGanadora(jugador: String, posiciones: Array<String?>): Int { // busca si hay alguna casilla donde se pueda ganar
    val combinacionesGanadoras = arrayOf(
        intArrayOf(0, 1, 2), intArrayOf(3, 4, 5), intArrayOf(6, 7, 8), // filas
        intArrayOf(0, 3, 6), intArrayOf(1, 4, 7), intArrayOf(2, 5, 8), // columnas
        intArrayOf(0, 4, 8), intArrayOf(2, 4, 6)  // diagonales
    )

    for (combinaciones in combinacionesGanadoras) { // itera sobre las posiciones ganadoras
        val (a, b, c) = combinaciones // combinacion ganadora
        val valores = listOf(posiciones[a], posiciones[b], posiciones[c]) // lista con las posiciones corespondientes del tablero

        // si hay dos letras del jugador maquina y una casilla vacia, devuelve la vacia
        if (valores.count { it == jugador } == 2 && valores.count { it == null } == 1) {
            return combinaciones[valores.indexOf(null)]
        }
    }
    return -1 // no hay jugada ganadora ni bloqueo
}

fun verificarGanador(posiciones: Array<String?>): String? { // verifica si hay algun ganador
    var combinacionesGanadoras = arrayOf( // todas las combinaciones donde un jugador gana
        intArrayOf(0, 1, 2), intArrayOf(3, 4, 5), intArrayOf(6, 7, 8), // filas
        intArrayOf(0, 3, 6), intArrayOf(1, 4, 7), intArrayOf(2, 5, 8), // columnas
        intArrayOf(0, 4, 8), intArrayOf(2, 4, 6)  // diagonales
    )

    for(combinacion in combinacionesGanadoras){ // itera sobre las posiciones para buscar coincidencias con jugadas ganadoras
        val (a, b, c) = combinacion // jugada ganadora
        if (posiciones[a] != null && posiciones[a] == posiciones[b] && posiciones[b] == posiciones[c]) { // determina si la posicion coincide con una jugada ganadora
            return posiciones[a] // si hay jugada ganadora retorna la letra correspondiente
        }
    }
    return null // si no hay, returna null
}