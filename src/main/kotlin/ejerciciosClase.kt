package mx.upiita.holakotlin
import kotlin.math.ceil

fun main(){
    println(factorial(5))
    fibonacci(n = 10)
    println()
    primos(n = 35)
}

fun factorial(n: Int): Int{
    if(n == 0)
        return 1
    else
        return n*factorial(n-1)
}

fun fibonacci(a: Int = 1, b: Int = 0, n: Int): Int{
    if(n == 0 ){
        return 0
    }else{
        print("$b, ")
        var c = a+b
        return fibonacci(b,c,n-1)
    }
}

fun primos(a: Int = 1, n: Int): Int{
    var o = 0
    if(/*n == 0 ||*/ a > n){
        return 0
    }else{
        for(i in 2 .. ceil(a/2.00).toInt()){
            if (a%i == 0){
                o = 1
                break
            }
        }
        if(o == 0){
            print("$a, ")
        }
        return primos(a+1, n)
    }

}

/*
* 04/03/2025
Ejercicio 1: Crear una función recursiva que calcule el factorial de un numero n
Ejercicio 2: Crear una función que imprima la serie de fibonacci
Ejercicio 3: Crear una función que reciba un entero e imprima los numeros primos entre 0 y el numero
* */

