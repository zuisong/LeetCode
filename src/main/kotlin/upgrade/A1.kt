package upgrade

import kotlin.system.*

fun main(args: Array<String>) {
    val repeated = ('a'..'z').joinToString(separator = "").repeat(1000 * 10000)
    repeat(10) {
        println(measureTimeMillis {
            repeated.groupingBy { it }.eachCount()
        } / 1000.0)

    }
}