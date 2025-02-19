package mx.upiita.holakotlin // agregar el paqute al que pertenece

fun main(/*args: Array<String>*/){
    //quitar comentario de arriba para bienvenida
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
    println("Push hp hecho")
    println("Push regresado")

    var arregloSimple = arrayOf(1,2,3,4)
    println(arregloSimple[0])
    println(arregloSimple[1])
    println(arregloSimple[2])
    println(arregloSimple[3])
    //println(arregloSimple[4]) esto daria error porque el indice sale del limite
    println(arregloSimple.joinToString()) // une todos los elementos de la cadena y los convierte en string

    var arregloNulos: Array<Int?> = arrayOfNulls(5) // arreglo de enteros que puede contener nulos
    println(arregloNulos.joinToString())

    val datos = Array<Int>(10){i -> i*2} // crea un arreglo de tipo int e indica que para el indice i tiene un arregla de correspondencia

    println(datos.joinToString())
    datos.forEach { println(it) } // buscar diferencia entre este y labmda

    var arregloPalabras = "Estas son varias palabras".split(" ").toTypedArray() // divide las palabras usando espacio, y .toTypedArray las guarda en un arreglo
    arregloPalabras.forEach {elemento -> println(elemento) } // funcion lambda

    val dosDArray = Array(3){Array<Int>(4) { 0 } } // arreglo de 3x4
    dosDArray[1][3] = 8
    println(dosDArray.joinToString()) // esto solo nos imprimiria las direcciones de los arreglos anidados
    println(dosDArray.contentDeepToString())
    println(dosDArray.forEach{element -> println(element.contentDeepToString()) })

    var cadena: String = "CAdena de caracteres"
    var rawString = """ Luis Cordero
        Llegó tarde el día martes
        a la superclase de ktlin
        en donde Mariana se enojó """

    var nombre = "Fulano "
    var email = "fulano@micorreo.com"
    var telefono = "+522222222222"

    var concatenado = String.format("nombre: %s \nemail: %s\ntelefono: %s", nombre,email,telefono)
    println(rawString)
    println(concatenado)

    //preferido en kotlin
    var concatenado2 = "nombre: $nombre \nTeléfono: $telefono\nemain: $email"
    println(concatenado2)

    //imprimir usando argumentos de linea de comandos. Quitar comentarios para activiar
    /*var _nombre = args[0]
    var _apellido = args[1]
    var concatenadoArgs = "Bienvenido de nuevo, $_nombre $_apellido"
    println(concatenadoArgs)*/
    println("Subido a git")
}