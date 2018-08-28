package cn.mmooo.leetcode

/**
给定一个 n × n 的二维矩阵表示一个图像。
将图像顺时针旋转 90 度。
说明：
你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。
示例 1:
给定 matrix =
[
[1,2,3],
[4,5,6],
[7,8,9]
],
原地旋转输入矩阵，使其变为:
[
[7,4,1],
[8,5,2],
[9,6,3]
]
 */
fun rotate(matrix: Array<IntArray>) {
    /**
     * 顺时针转90度
     *  (i,j) -> (j,n-1-i) -> (n-1-i,n-1-j) -> (n-1-j,i) -> (i,j)
     */
    val n = matrix.size
    var i = 0
    var temp: Int
    while (i < n / 2) {
        for (j in i until n - 1 - i) {
            temp = matrix[i][j]

            matrix[i][j] = matrix[n - 1 - j][i]

            matrix[n - 1 - j][i] = matrix[n - 1 - i][n - 1 - j]

            matrix[n - 1 - i][n - 1 - j] = matrix[j][n - 1 - i]

            matrix[j][n - 1 - i] = temp
        }
        i++
    }

}

fun main(args: Array<String>) {


    val matrix = arrayOf(
            intArrayOf(50, 1, 9, 11),
            intArrayOf(21, 4, 8, 10),
            intArrayOf(13, 3, 6, 72),
            intArrayOf(15, 5, 7, 16)
    )
    rotate(matrix)

    matrix.forEach { ints ->
        ints.forEach {
            print("$it ")
        }
        println()
    }
}