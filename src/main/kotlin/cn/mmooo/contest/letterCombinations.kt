package cn.mmooo.contest

/**
 * @links https://leetcode-cn.com/explore/interview/card/top-interview-questions-medium/49/backtracking/91/
 */
fun letterCombinations(digits: String): List<String> {

    val digitsToChars = mapOf(
            '2' to "abc".toCharArray(),
            '3' to "def".toCharArray(),
            '4' to "ghi".toCharArray(),
            '5' to "jkl".toCharArray(),
            '6' to "mno".toCharArray(),
            '7' to "pqrs".toCharArray(),
            '8' to "tuv".toCharArray(),
            '9' to "wxyz".toCharArray()
    )


    var result = arrayListOf<String>()

    fun dfs(index: Int, str: String) {
        if (index >= digits.length) {
            result.add(str)
            return
        }

        for (c in digitsToChars[digits[index]]!!) dfs(index + 1, str + c)

    }



    dfs(0, "")

    return result
}

fun main(args: Array<String>) {
    letterCombinations("245").let(::println)
}