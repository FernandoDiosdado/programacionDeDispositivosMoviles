package mx.upiita.holakotlin // agregar el paqute al que pertenece

fun main(){
    println("Hola mundo desde Kotlin!")

    val variable1 = 5.0 // tipo double
    var variable2: Int = 12 // tipo entero
    var variable3: Float = 10f // especificar varible en tipo float

    println(variable1::class.java.typeName) // imprime el tipo de dato
    println(variable2) // imprime el dato
    println(variable3::class.java.typeName)

    variable2 = 3 // tipo entero
    //variable1 =14.3f

    println(variable1::class.java.typeName)
    println(variable2)

    println("Hola\nKotlin") // salto de linea
    println("Hola\tKotlin") // tabulacion
    println("Comilla simple: \'")
    println("Comilla doble: \"")
    println("Barra invertida: \\")
    println("Precio: \$10.000")
    println("Unicode \u03A6")
    println("Push hecho")
}