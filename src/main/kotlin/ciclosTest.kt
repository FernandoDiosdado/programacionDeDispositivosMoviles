package mx.upiita.holakotlin
import java.util.Calendar

fun main(args: Array<String>){
    // no existe switch en kotlin
    /* no hay brake, numerico, cadenas, rangos
    * when(variable){
    *   v1 -> sentencia
    *   v2 -> sentencia
    *   v3 -> ...
    *
    *   else -> sentencia
    * }
    * */
    var calendario = Calendar.getInstance()
    var dia = calendario.get(Calendar.DAY_OF_WEEK)
    println(dia) //imprime el numero del dia
    when(dia){ //ejecutar como sentencia
        1 -> println("Domingo")
        2 -> println("Lunes")
        3 -> println("Martes")
        4 -> println("Miércoles")
        5 -> println("Jueves")
        else -> println("Ya es fin de semana")
    }

    var diaSemana = when(dia){ // ejecutar como asignacion
        1 -> "Domingo"
        2 -> "Lunes"
        3 -> "Martes"
        4 -> "Miércoles"
        5 -> "Jueves"
        else -> "Ya es fin de semana"
    }
    println("Hoy es $diaSemana")

    println("Cual es la respuesta a la vida?")
    //por defecto, en kotlin no se pueden guardar nulos
    var respuesta:Int? = readLine()?.toInt()// al poner ? podemos guardar en este caso el Int o un valor null
    val mensaje = when(respuesta){
        42 -> "Ya te la sabes"
        43,44,45 -> "Cualquiera entre 43, 44, 45"
        in 46  ..  100 -> "entre cuarenta y seis y Cien"
        else -> "No es lo que estoy buscando"
    }
    println("La respuesta de la vida es : $mensaje")
    //.in lleva comillas porque en kotlin in es un operador, las comillas indican que es una clase
    //is otro operador
    //pedir al usuario el mes que quiera, responder la estación, temperada de lluvia
    //los rangos no pueden ser descendientes 10 .. 0
}