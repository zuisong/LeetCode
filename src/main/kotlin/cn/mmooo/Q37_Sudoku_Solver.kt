package cn.mmooo.q37

/**
 * https://leetcode.com/problems/sudoku-solver/

Write a program to solve a Sudoku puzzle by filling the empty cells.

Empty cells are indicated by the character '.'.

You may assume that there will be only one unique solution.
 */
fun main(args: Array<String>) {
    val sudo = arrayOf(
            "530070000",
            "600195000",
            "098000060",
            "800060003",
            "400803001",
            "700020006",
            "060000280",
            "000419005",
            "000080079"
    ).map { it.map { if (it == '0') '.' else it }.toCharArray() }.toTypedArray()

    Solution().solveSudoku(sudo)

    sudo.forEach {
        it.forEach { print("$it ") }
        println()
    }

}

private class Solution {
    fun solveSudoku(board: Array<CharArray>): Unit {
        var result: Array<CharArray>? = null
        fun solve(arr: Array<CharArray>, index: Int = 0) {
            if (index == 81) {
                result = arr
            } else {
                val (x, y) = Pair(index / 9, index % 9)
                if (arr[x][y] != '.') {
                    solve(arr, index + 1)
                } else
                    ('1'..'9').filter { checKValue(arr, x, y, it) }
                            .forEach {
                                val copyedArr = copyArr(arr)
                                copyedArr[x][y] = it
                                solve(copyedArr, index + 1)
                            }
            }
        }
        solve(board, 0)
        result!!.forEachIndexed { x, ints ->
            ints.forEachIndexed { y, v ->
                board[x][y] = v
            }
        }
    }
}


fun copyArr(arr: Array<CharArray>): Array<CharArray> {
    val copy = Array(arr.size, { CharArray(arr.first().size) })
    arr.forEachIndexed { x, ints ->
        ints.forEachIndexed { y, v ->
            copy[x][y] = v
        }
    }
    return copy
}


fun checkRow(arr: Array<CharArray>, x: Int, v: Char): Boolean = v !in arr[x]


fun checkColumn(arr: Array<CharArray>, y: Int, v: Char): Boolean = v !in arr.map { it[y] }


fun checkGrid(arr: Array<CharArray>, x: Int, y: Int, v: Char): Boolean {
    val gridX = x / 3
    val gridY = y / 3
    return !arr
            .copyOfRange(3 * gridX, 3 * (gridX + 1))
            .map { it.copyOfRange(3 * gridY, 3 * (gridY + 1)) }
            .flatMap { it.toList() }
            .contains(v)
}

fun checKValue(arr: Array<CharArray>, x: Int, y: Int, v: Char): Boolean =
        checkColumn(arr, y, v) &&
                checkRow(arr, x, v) &&
                checkGrid(arr, x, y, v)
