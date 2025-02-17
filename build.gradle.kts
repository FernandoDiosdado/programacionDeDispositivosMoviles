plugins {
    kotlin("jvm") version "2.1.10"
}

group = "mx.upiita.holakotlin" // el paquete
version = "1.0" // indica la version del proyecto

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(23)
}

tasks.jar.configure{ // configura la tarea que genera el archivo jar
    manifest{
        //se obtiene el nombre del archivo original
        //attributes(mapOf("Main-Class" to "org.example.holakotlin.HolakotlinKt"))
        attributes(mapOf("Main-Class" to "mx.upiita.holakotlin.HolaKotlinKt")) // archivo que tiene el main
    }
    configurations["compileClasspath"].forEach{ // agrega todas las bibliotecas al jar
            file: File -> from(zipTree(file.absoluteFile))
    }

    duplicatesStrategy = DuplicatesStrategy.INCLUDE // agrega archivos duplicados en vez de generar error
}
