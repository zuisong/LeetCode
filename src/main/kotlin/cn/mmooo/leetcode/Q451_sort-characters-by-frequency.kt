package cn.mmooo.leetcode

fun frequencySort(s: String): String {

    return s.groupingBy { it }
            .eachCount()
            .entries
            .sortedByDescending { it.value }
            .joinToString("")
            { it.key.toString().repeat(it.value) }

}


fun main(args: Array<String>) {
    frequencySort("chenjian")
            .let(::println)
}