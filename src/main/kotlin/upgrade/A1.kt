package upgrade

import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    repeat(10) {
        println(measureTimeMillis {
            val repeated = ('a'..'z').joinToString(separator = "").repeat(1000 * 10000)
            println(repeated.groupingBy { it }.eachCount())
        } / 1000.0)

    }
}