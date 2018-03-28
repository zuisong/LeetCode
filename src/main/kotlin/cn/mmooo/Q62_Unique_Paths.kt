package cn.mmooo.q62

/**
机器人位于一个 m x n 网格的左上角, 在下图中标记为“Start” (开始)。

机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角，在下图中标记为“Finish”(结束)。

问有多少条不同的路径？
 */
fun main(args: Array<String>) {
    Solution().uniquePaths(7, 10)
            .let { println(it) }
}

class Solution {
    fun uniquePaths(m: Int, n: Int): Int {
        val mat = Array(m, { IntArray(n) })

        repeat(m) { x ->
            repeat(n) { y ->
                when {
                    x == 0 || y == 0 -> mat[x][y] = 1
                    else -> {
                        mat[x][y] = mat[x - 1][y] + mat[x][y - 1]
                    }
                }
            }
        }
        return mat.last().last()
    }
}


