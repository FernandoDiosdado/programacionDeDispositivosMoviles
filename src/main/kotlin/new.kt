package mx.upiita.holakotlin

/*
* fun idFunction(...){}
* fun idFunction(idp1:tp1, idp2:tp2)
* varargs
* */

fun muchosParams(vararg parametros: String){
   /*for (p in parametros){
       println("Parametro: $p")
   }*/
    parametros.forEach { print("$it, ") }
    println()
}

// la variable puede ser de cualquier tipo T
fun<T> variosParams(vararg params: T){
    params.forEach { print("$it, ") }
    println()
}

fun main(args: Array<String>){
    muchosParams("uno")
    muchosParams("uno","dos")
    muchosParams("uno","dos","tres")
    muchosParams("uno","dos","tres","cuatro")

    variosParams(1,2,3,4,5)
    variosParams("uno","dos","tres","cuatro")
    variosParams(*"Hola a todos los presentes".split(" ").toTypedArray()) //este arreglo se dispersa (se denota con el asterisco)
    //split los separa, toTypedArray los mete en un arreglo y * los dispersa (o seapara) en varios String
    //se espcifica el tipo o con tipo template

    val yoSoy = "Aragorn Hijo de Aratorn!"
    /*var util = UtilPorras()
    println(util.porra(yoSoy))
    println(util.contraPorra(yoSoy))
    println(util.superPorra(yoSoy))*/
    println(yoSoy.porra())
    println(yoSoy.contraPorra())
    println(yoSoy.superPorra())

    val dominic = Persona("Dominic")
    //metodo normal
    //dominic.dice("Tengo los ojos rojos?")
    // metodo subfix
    dominic dice "Tengo los ojos Rojos?"
}
/*
class UtilPorras {
    fun porra(msg: String) = "$msg -- Ra Ra Ra !!!!"
    fun contraPorra(msg: String) = "$msg -- Bu Bu Bu !!!!"
    fun superPorra(msg: String) = "$msg -- HUELUMMM !!!!"
}*/

// nos permite expandir el funcionamiento de una clase
fun String.porra() = "$this -- Ra Ra Ra !!!!"
fun String.contraPorra() = "$this -- Bu Bu Bu !!!!"
fun String.superPorra() = "$this -- HUELUMMM !!!!"

class Persona(val nombre: String){
    //funcion normal
    //fun dice(mensaje: String) = println("$nombre dice $mensaje")
    //funcion subfija
    infix fun dice(mensaje: String) = println("$nombre dice $mensaje")
}

/*
* Enfoque Kotlin:
* No necesita herencia, ya que esos métodos extienden a una clase ya existente
*
* */


/*expresiones
prefix +ab
postfix ab+
infix a+b
* */

/* PRACTICA 1
* evalua quien comienza: un volado
* se tiene que hacer un modulo de inteligencia artificial: debe querer ganarle
* que el algoritmo trate de ganar, tambien evitar que ganes mediante bloqueos
* jugar hasta que se llene empatando o ganar
* pregunta la letra del jugador: x ó o
* primero tirar a ganar
* segundo evitar que el otro gane
* tercero dominar las esquinas
* cuarto dominar el centro
* quinto dominar los puntos cardinales
* podemos hacer un algoritmo de fuerza bruta,
* podemos hacer el mayor numero de funciones
* */

/*
* Hacer operaciones de analisis en un tablero imaginario,
* FECHA: 8 de Marzo
* */