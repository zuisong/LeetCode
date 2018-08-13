package cn.mmooo.contest

/**
给定一个二维网格和一个单词，找出该单词是否存在于网格中。

单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。

示例:

board =
[
['A','B','C','E'],
['S','F','C','S'],
['A','D','E','E']
]

给定 word = "ABCCED", 返回 true.
给定 word = "SEE", 返回 true.
给定 word = "ABCB", 返回 false.
 */
fun exist(board: Array<CharArray>, word: String): Boolean {

    var result = arrayListOf<Set<Pair<Int, Int>>>()

    fun dfs(point: Pair<Int, Int>, route: Set<Pair<Int, Int>>) {
        if (board.lastIndex < point.first || point.first < 0) return
        if (board[0].lastIndex < point.second || point.second < 0) return
        if (point in route) return

        if (board[point.first][point.second] == word[route.size]) {

            val newRoute = route + point
            if (newRoute.size >= word.length) {
                result.add(newRoute)
                return
            }
            dfs(point.copy(first = point.first + 1), newRoute)
            dfs(point.copy(first = point.first - 1), newRoute)
            dfs(point.copy(second = point.second + 1), newRoute)
            dfs(point.copy(second = point.second - 1), newRoute)
        }
    }
    board.forEachIndexed { x, chars ->
        chars.forEachIndexed { y, _ ->
            dfs(Pair(x, y), emptySet())
        }
    }

    println(result)

    return result.isNotEmpty()

}

fun main(args: Array<String>) {
    val board = arrayOf(
            charArrayOf('A', 'B', 'C', 'E'),
            charArrayOf('S', 'F', 'C', 'S'),
            charArrayOf('A', 'D', 'E', 'E')
    )

    exist(board, "SEE")
    exist(board, "ABCCED")
}