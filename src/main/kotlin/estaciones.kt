package mx.upiita.holakotlin
import java.util.Calendar

fun main(args: Array<String>){

    println("Ingresa el mes de año para saber su estación")
    //var mes = readLine()?.toInt()
    var mes = 4
    var estacion = when(mes){
        in 1 .. 3 -> "Invierno"
        in 4 .. 6 -> "Primavera"
        in 7 .. 9 -> "Verano"
        in 10 .. 12 -> "Otoño"
        else -> 0
    }
    if(estacion == 0){
        println("El numero de mes ingresado no es valido")
    }else {
        println("La estacion del mes ingresado es: $estacion")
    }
    //Any es a kotlin como Object es a java, es la super clase
    //con null se le debe poner el signo de interrogacion
    var duda:Any = "Cadena" // es una variable que puede soportar cualquier tipo
    when(duda){
        is Int -> println("Es un entero")
        is String -> println("Es una cadena")
        is Double -> println("Es un double")
        else -> println("No sé lo que es")
    }

    var contador = 0
    var finish = 5 // si hay ++
    while(contador++ < finish){
        println("Contador = $contador")
    }
    contador = 0
    do{
        println("Contador = $contador")
    }while(contador++ < finish)

    // el for es de tipo for-each, trabaja con un interador
    var palabras:List<String> = "El rapido credimiento de la poblacion".split(" ")
    for(palabra in palabras){ //palabra es el elemento de control
        println(palabra)
    }

    var ceroADiez = 0 .. 10
    for(x in ceroADiez){
        println("x = $x")
    }

    // TAREA: pedir al usuario un numero entre 1 y 12, en la primera columna del 1 al 10, la fila superior el numero ingresado
    // el resto de la tabla, hacer un ciclo repetitivo para volver ejecutar el problema
    // si no esta en ese rango, decirle al usuario que está mal y meta otro dato

}