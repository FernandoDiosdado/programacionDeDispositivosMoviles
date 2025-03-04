package  mx.upiita.holakotlin
import java.util.Scanner

fun main(){
    val scanner = Scanner(System.`in`)
    do{
        println("Ingrese un numero entre 1 y 12")
        try{
            var columnas = scanner.nextLine().toInt()
            if (columnas is Int && 1 <= columnas && columnas <= 12) {
                print("|")
                for (i in 1..columnas) {
                    print(" $i |")

                }
                println()
                for (j in 1..10) {
                    print("| $j | ")
                    for (k in 1..columnas) {
                        var producto = j * k
                        print(" $producto |")
                    }
                    println()
                }
            } else {
                println("No ingresaste un numero entero entre 1 y 12")
            }
        }catch (e:Exception){
            println("No ingresaste un numero entero entre 1 y 12")
        }
        println("¿Deseas coninuar? si/no")
        var res = scanner.nextLine()
    }while(res == "si")

}