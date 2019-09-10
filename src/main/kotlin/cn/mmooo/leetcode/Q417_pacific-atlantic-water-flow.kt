package cn.mmooo.leetcode

import java.util.*

fun pacificAtlantic(matrix: Array<IntArray>): List<IntArray> {

    val stack = Stack<Triple<Int, Int, Int>>()
    stack.push(Triple(0, matrix.first().lastIndex, matrix.first().last()))
    stack.push(Triple(matrix.lastIndex, 0, matrix.last().first()))

    val result = mutableListOf<IntArray>()

    fun getAvailable(point: Triple<Int, Int, Int>): List<Triple<Int, Int, Int>> {
        return listOf(
                Pair(point.first, point.second + 1),
                Pair(point.first, point.second - 1),
                Pair(point.first - 1, point.second),
                Pair(point.first + 1, point.second)
        ).filter { 0 <= it.first && it.first <= matrix.lastIndex }
                .filter { 0 <= it.second && it.second <= matrix.first().lastIndex }
                .map { Triple(it.first, it.second, matrix[it.first][it.second]) }
                .filter { it.third >= point.third }
    }

    while (stack.isNotEmpty()) {
        val point = stack.pop()
        result.add(intArrayOf(point.first, point.second))
        val list = getAvailable(point)
        list.forEach { stack.push(it) }
    }

    return result

}

fun main(args: Array<String>) {
    val matrix = arrayOf(
            intArrayOf(1, 2, 2, 3, 5),
            intArrayOf(3, 2, 3, 4, 4),
            intArrayOf(2, 4, 5, 3, 1),
            intArrayOf(6, 7, 1, 4, 5),
            intArrayOf(5, 1, 1, 2, 4)
    )
    pacificAtlantic(matrix).map { it.toList() }.let(::println)
}