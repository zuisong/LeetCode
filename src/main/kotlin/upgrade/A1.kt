package upgrade

fun main(args: Array<String>) {
    val b = localReturn(listOf(0, 1, 2, 3))
    println(b)
}

fun localReturn(list: List<Int>): Boolean {
    contains(0) {
        list.forEach { i ->
            println(i)
            if (i == 0)
                return true
        }
        false
    }
    return false
}

inline fun <reified T> contains(item: T, f: (T) -> Boolean): Boolean {
    println(T::class.java.name)
    return f(item)
}