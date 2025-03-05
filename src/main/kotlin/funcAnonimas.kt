package mx.upiita.holakotlin

fun main(){
    println(func(6))
    println(incremento(7))
    println(incrementu(8))
}

// lamda sin argumentos
val funcero: () -> Unit = { println("Sin argumentos") }
val funcuno = { println("Sin argumentos 2") }
val fundos: (Int, Int) -> Int = {x, y -> x + y}
val funtres = {x: Int, y:Int -> x + y}

// lo mas sencillo de usar es una funcion lamda, pero no hay multiples returs
// la función anonima sí permite multiples returns, para cosas mas complejas es mejor
// siempre y cuando me interese hacerle cambios en la funcion
val func = fun(i: Int): Int{return i+1}

// son como funciones inline, para hacer lo más sencillo y con un solo resultado
val incremento = {x: Int -> x+1}
val incrementu: (Int) -> Int = {x -> x+1}

/*
* Para funciones anonimas es obligatorio indicar el valor de retorno
* Si no hay valor de retorno: Unit
* fun (parametros: tipo ...): tipo_retorno{
*   return ...
* }
*
* */

/*
* Expresion Lambda
* {(parametros,lista) -> expresion}
* */