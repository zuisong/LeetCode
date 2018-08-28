package cn.mmooo.leetcode

/**
给定一个 m x n 的矩阵，如果一个元素为 0，则将其所在行和列的所有元素都设为 0。请使用原地算法。
进阶:

一个直接的解决方案是使用  O(mn) 的额外空间，但这并不是一个好的解决方案。
一个简单的改进方案是使用 O(m + n) 的额外空间，但这仍然不是最好的解决方案。
你能想出一个常数空间的解决方案吗？
 */
fun setZeroes(matrix: Array<IntArray>): Unit {

    var isZeroX = matrix.first().any { it == 0 }
    var isZeroY = matrix.map { it.first() }.any { it == 0 }

    matrix.forEachIndexed { x, ints ->
        ints.forEachIndexed { y, v ->
            if (v == 0) {
                matrix[x][0] = 0
                matrix[0][y] = 0
            }
        }
    }

    (1 until matrix.size).forEach { x ->
        if (matrix[x][0] == 0) {
            for (y in 0..matrix[0].size) {
                matrix[x][y] = 0
            }
        }
    }

    (1 until matrix.first().size).forEach { y ->
        if (matrix.first()[y] == 0) {
            for (x in 0..matrix.size) {
                matrix[x][y] = 0
            }
        }
    }
    if (isZeroX) {
        for (x in 0..matrix.size) {
            matrix[x][0] = 0
        }
    }
    if (isZeroY) {
        for (y in 0..matrix[0].size) {
            matrix[0][y] = 0
        }
    }

}