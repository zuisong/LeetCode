package cn.mmooo.contest

/**
 *
给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。

例如，给出 n = 3，生成结果为：

[
"((()))",
"(()())",
"(())()",
"()(())",
"()()()"
]
 */
fun generateParenthesis(n: Int): List<String> {
    val result = arrayListOf<String>()
    fun dfs(lcount: Int, rcount: Int, str: StringBuilder) {
        if (lcount >= n && rcount >= n) {
            result.add(str.toString())
            return
        }
        if (lcount > rcount && rcount < n) {
            dfs(lcount, rcount + 1, str.append(')'))
            str.deleteCharAt(str.lastIndex)
        }

        if (lcount < n) {
            dfs(lcount + 1, rcount, str.append('('))
            str.deleteCharAt(str.lastIndex)
        }

    }
    dfs(0, 0, StringBuilder())
    return result
}

fun main() {
    generateParenthesis(10).forEach(::println)
}