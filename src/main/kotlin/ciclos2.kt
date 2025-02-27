package mx.upiita.holakotlin

fun main (args: Array<String>){
    //ejemplo de break etiquetado
    var num1: Int = 4
    externo@ while (num1 > 0){
        var num2: Int = 4
        interno@ while(num2 > 0){
            if( num1 == 2) break@externo
            println("num1 = $num1, num2 = $num2")
            num2--
        }
        num1--
    }
    // sirve de la misma manera el continue etiquetado

    // ejemplo de continue etiquetado
    println("Continue etiquetado")
    externoC@ for (num1 in 4 downTo 1){
        interno@ for (num2 in 4 downTo 1){
            if( num1 <= 3)
                continue@externoC // en este caso el externo se sigue etiquetando
            println("num1 = $num1, num2 = $num2")
        }
    }


}