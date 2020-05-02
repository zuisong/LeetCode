package cn.mmooo.leetcode

import java.util.*
import kotlin.collections.HashMap

/**
On a 2x3 board, there are 5 tiles represented by the integers 1 through 5, and an empty square represented by 0.

A move consists of choosing 0 and a 4-directionally adjacent number and swapping it.

The state of the board is solved if and only if the board is [[1,2,3],[4,5,0]].

Given a puzzle board, return the least number of moves required so that the state of the board is solved. If it is impossible for the state of the board to be solved, return -1.
 */
/**
 * 数字华容道万能解法
 */
fun main(args: Array<String>) {
    val board = arrayOf(
            intArrayOf(2, 3, 4, 8),
            intArrayOf(1, 5, 7, 12),
            intArrayOf(6, 11, 15, 0),
            intArrayOf(9, 13, 10, 14)
    )
    slidingPuzzle(board, target = arrayOf(
            intArrayOf(1, 2, 3, 4),
            intArrayOf(5, 6, 7, 8),
            intArrayOf(9, 10, 11, 12),
            intArrayOf(13, 14, 15, 0)
    )).let {
        println("变换路径: ${it.second}")
        println("变换方向: ${it.third}")
        println("最少步数是 ${it.first}")
    }
}


/**
 * 在数组 [board] 中寻找0的位置，找不到就抛出异常[RuntimeException]
 * @throws RuntimeException
 */
fun positionOfZero(board: Array<IntArray>): Pair<Int, Int> {
    board.forEachIndexed { indexX, ints ->
        ints.forEachIndexed { indexY, i ->
            if (i == 0) {
                return Pair(indexX, indexY)
            }
        }
    }
    throw RuntimeException("找不到0的位置")
}

/**
 * 将[arr] 拷贝一份
 */
fun copy(arr: Array<IntArray>): Array<IntArray> {
    val res: Array<IntArray> = Array(arr.size, { IntArray(arr[0].size) })
    arr.forEachIndexed { indexX, ints ->
        ints.forEachIndexed { indexY, i ->
            res[indexX][indexY] = i
        }
    }
    return res
}

/**
 *  hash [arr]
 */
fun hash(arr: Array<IntArray>): String {
    val buffer = StringBuffer()
    arr.forEach { ints ->
        ints.forEach {
            buffer.append(it).append(' ')
        }
    }
    return buffer.toString()
}

fun slidingPuzzle(board: Array<IntArray>, target: Array<IntArray>): Triple<Int, String, String> {
    val targetHash = hash(target)

    val xRange = 0..board.lastIndex
    val yRange = 0..board.first().lastIndex

    val directions = listOf(Pair(-1, 0), Pair(1, 0), Pair(0, -1), Pair(0, 1))

    val queue = LinkedList<Array<IntArray>>()
    val hashMap = HashMap<String, Pair<String, Pair<Int, Int>>>()

    queue.add(board)
    while (queue.isNotEmpty()) {
        val arr = queue.poll()
        val s = hash(arr)
        val positionOfZero = positionOfZero(arr)
        if (s == targetHash) {
            val stack = LinkedList<String>()
            val stack2 = LinkedList<String>()
            stack.addFirst(targetHash)
            var r = targetHash
            while (r != hash(board)) {
                val pair = hashMap[r]!!
                r = pair.first
                stack.addFirst(pair.first)
                stack2.addFirst(pair.second.let {
                    when (it) {
                        Pair(-1, 0) -> "上"
                        Pair(1, 0) -> "下"
                        Pair(0, -1) -> "左"
                        Pair(0, 1) -> "右"
                        else -> "不可能进这里的啦！！！"
                    }
                })
            }

            return Triple(stack.lastIndex, stack.joinToString(separator = " -> "), stack2.joinToString(separator = " -> "))
        }
        directions.map { Pair(it.first + positionOfZero.first, it.second + positionOfZero.second) }
                .filter { it.first in xRange && it.second in yRange }
                .forEach {
                    val copyedArr = copy(arr)
                    val temp = copyedArr[positionOfZero.first][positionOfZero.second]
                    copyedArr[positionOfZero.first][positionOfZero.second] = copyedArr[it.first][it.second]
                    copyedArr[it.first][it.second] = temp
                    val s1 = hash(copyedArr)
                    if (s1 !in hashMap) {
                        hashMap[s1] = Pair(s, Pair(it.first - positionOfZero.first, it.second - positionOfZero.second))
                        queue.add(copyedArr)
                    }
                }
    }

    return Triple(-1, "无解", "无解")
}
