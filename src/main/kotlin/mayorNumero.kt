package mx.upiita.holakotlin
import java.util.Scanner

fun main(args: Array<String>){

    val sn1 = Scanner(System.`in`)
    println("Ingresa 3 numeros enteros diferentes")
    var num1 = sn1.nextInt()
    var num2 = sn1.nextInt()
    var num3 = sn1.nextInt()
    var mayor = if(num1>num2) num1 else num2
    mayor = if(mayor>num3) mayor else num3
    println("El mayor numero es $mayor")


}