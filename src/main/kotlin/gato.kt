package mx.upiita.holakotlin
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

fun jugar(jugadorPersona: Pair<String, Int>,
          jugadorMaquina: Pair<String, Int>, posiciones:
          Array<String?>): Int{
    if(posiciones.contains(null)){
        val primerJugador = if(jugadorPersona.second == 0) jugadorPersona else jugadorMaquina
        val segundoJugador = if(jugadorPersona.second == 1) jugadorPersona else jugadorMaquina
        val primeraJugada = if(primerJugador == jugadorPersona) :: jugarPersona else :: jugarMaquina
        val segundaJugada = if(segundoJugador == jugadorPersona) :: jugarPersona else :: jugarMaquina

        realizarJugada(primerJugador, posiciones, primeraJugada)
        println(imprimirTablero(posiciones))

        //verificar si gana con esa jugada
        //si no gana y aun no hay casillas

        if(posiciones.contains(null)){
            realizarJugada(segundoJugador, posiciones, segundaJugada)
            println(imprimirTablero(posiciones))
            // verificar si gana el segundo jugador

        }
        return jugar(jugadorPersona, jugadorMaquina, posiciones)
    }else{
        println("Empate")
        return 0
    }


}

fun realizarJugada(jugador: Pair<String, Int>,
                   posiciones: Array<String?>,
                   tipoJugada: (Pair<String, Int>, Array<String?>){
                       tipoJugada(jugador, posiciones)

}

fun jugarPersona(jugadorPersona: Pair<String, Int>,
                 posiciones: Array<String?>){
    var casilla: Int?
    do{
        println("Escoge una casilla (1-9)")
        casilla = readLine()?.toIntOrNull()
    }while(casilla == null || casilla !in 1 .. 9 || posiciones[casilla- 1 ] != null)
    posiciones[casilla - 1] = jugadorPersona.first
}

fun jugarMaquina(jugadorMaquina: Pair<String, Int>,
                 posiciones: Array<String?>){

}

fun verificarGanador(posiciones: Array<String?>): String? {
    var valCominaciones = arrayOf(
        intArrayOf(0, 1, 2), intArrayOf(3, 4, 5), intArrayOf(6, 7, 8), // Filas
        intArrayOf(0, 3, 6), intArrayOf(1, 4, 7), intArrayOf(2, 5, 8), // Columnas
        intArrayOf(0, 4, 8), intArrayOf(2, 4, 6)  // Diagonales
    )
}