package mx.upiita.holakotlin

fun main(args: Array<String>){
    despliegaMensaje("Hola a todos",3)
    var suma = getSuma(listOf(1,3,5,7,11,13,17))
    println(suma)
    var (x,y) = bigSmall(5, 3)
    println(x)
    println(y)
    conectarToBD("myserver","root")
    conectarToBD()
    conectarToBD(username = "----", password = "root")
}

fun despliegaMensaje(msg: String, count: Int){
    var contador = 1
    while(contador++ <= count){
        println(msg)
    }
}
// unit es para indicar que no retorna valor, es igual si no lo incluyes
// estas funciones son topLevel, porque no están definidas en una clase
fun despliegaMensaje2(msg: String, count: Int): Unit{
    var contador = 1
    while(contador++ <= count){
        println(msg)
    }
}

fun getSuma(valoresLista: List<Int>): Int {
    var total = 0
    for(i in valoresLista) total+=i
    return total
}
// se utiliza Pair porqueno podrías regresar 2 valores
fun bigSmall(a: Int, b:Int): Pair<Int, Int>{
    if (a > b) return Pair(a,b)
    else{
        return Pair(b, a)
    }
}

//funciones inline
fun sumaEnteros(a:Int, b: Int) = a+b
// es lo mismo, pero especificando el tipo de retorno
fun sumaEnteros2(a:Int, b: Int): Int = a+b

//de esta manera podemos usar valores por default en caso de que no lleguen
fun conectarToBD(hostname: String = "localhost",
        username: String = "mysql",
        password: String = "secret"){
    println("$hostname's $username $password")

}




