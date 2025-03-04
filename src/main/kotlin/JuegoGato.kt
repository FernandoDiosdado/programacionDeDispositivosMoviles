package mx.upiita.holakotlin

fun main(args: Array<String>){
    var posiciones: Array<Int?> = arrayOf(1, 0, 0 ,1, null, null, 1, 0, null)
    imprimirTablero(posiciones)
}

fun<p> imprimirTablero(vararg posiciones: p){
    for (i in 1 .. 3){
        for (j in 1 .. 3){
            when(posiciones[i*j - 1]){
                0 -> print("0")
                1 -> print("X")
                else -> print(" ")
            }
            print("|")
        }
        println()
    }
}